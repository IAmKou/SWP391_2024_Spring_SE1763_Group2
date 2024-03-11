/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.AccountDAO;
import dao.PostDAO;
import dao.StatusDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Account;
import model.Post;
import model.Status;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class ViewListPostByAdmin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String posterIdStr = request.getParameter("poster");
        String statusIdStr = request.getParameter("status");
        String location = request.getParameter("location");

        int currentPage = 1;
        int recordsPerPage = 10;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));

            if (currentPage < 1) {
                currentPage = 1;
            }
        }
        PostDAO postDao = new PostDAO();
        List<Post> Allposts = postDao.allPost();
        
        AccountDAO accountDao = new AccountDAO();
        List<Account> accounts = new ArrayList<>();
        
        for (Post Allpost : Allposts) {
            Account user = accountDao.getAccountByUserId(Allpost.getHouse().getHouse_owner().getUser_id());
            boolean isUserExist = false;
            for (Account existingUser : accounts) {
                if (existingUser.getUser_id() == user.getUser_id()) {
                    isUserExist = true;
                    break;
                }
            }
            if (!isUserExist) {
                accounts.add(user);
            }
        }
        if (posterIdStr != null && !posterIdStr.isEmpty()) {
            int posterId = Integer.parseInt(posterIdStr);
            Allposts = filterByPoster(Allposts, posterId);
        }

        if (statusIdStr != null && !statusIdStr.isEmpty()) {
            int statusId = Integer.parseInt(statusIdStr);
            Allposts = filterByStatus(Allposts, statusId);
        }

        if (location != null && !location.isEmpty()) {
            location = location.toLowerCase();
            Allposts = filterByLocation(Allposts, location);
        }

        int totalPosts = Allposts.size();
        int totalPages = (int) Math.ceil((double) totalPosts / recordsPerPage);

        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(currentPage * recordsPerPage, totalPosts);

        List<Post> posts = Allposts.subList(start, end);
        
        StatusDAO statusDao = new StatusDAO();
        List<Status> statuses = statusDao.getStatus();
        
        request.setAttribute("accounts", accounts);
        request.setAttribute("statuses", statuses);
        request.setAttribute("posts", posts);
        request.setAttribute("totalPost", Allposts.size());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/views/admin/postList.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public List<Post> filterByPoster(List<Post> posts, int user_id) {
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : posts) {
            if (post.getHouse().getHouse_owner().getUser_id() == user_id) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    public List<Post> filterByStatus(List<Post> posts, int statusId) {
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : posts) {
            if (post.getPost_status().getStatus_id() == statusId) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }

    public List<Post> filterByLocation(List<Post> posts, String location) {
        List<Post> filteredPosts = new ArrayList<>();
        System.out.println(location);
        for (Post post : posts) {
            String postLocation = post.getHouse().getLocation().toLowerCase();
            if (postLocation.contains(location) || postLocation.contentEquals(location)) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }
}
