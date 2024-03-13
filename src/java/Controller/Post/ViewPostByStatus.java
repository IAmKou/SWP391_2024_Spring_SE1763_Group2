/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.ImageDAO;
import dao.PostDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;
import model.Image;
import model.Post;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class ViewPostByStatus extends HttpServlet {

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
        HttpSession session = request.getSession();
        User acc =  (User) session.getAttribute("account");
        
        String status_str = request.getParameter("status");
        int status_id = Integer.parseInt(status_str);
        
        int currentPage = 1; // Trang hiện tại, mặc định là 1
        int recordsPerPage = 3; // Số lượng booking trên mỗi trang
        
        // Xác định trang hiện tại từ tham số "page" của request
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));

            // Kiểm tra nếu requestedPage nhỏ hơn 1, đặt currentPage là 1
            if (currentPage < 1) {
                currentPage = 1;
            }
        }
        
        PostDAO post_DAO = new PostDAO();
        List<Post> allPosts = post_DAO.getPostByStatus(acc.getUser_id(), status_id);
        
        int totalPosts = allPosts.size();
        int totalPages = (int) Math.ceil((double) totalPosts / recordsPerPage);

        // Kiểm tra nếu currentPage vượt quá totalPages, đặt lại currentPage là totalPages
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }
        
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(currentPage * recordsPerPage, totalPosts);

        List<Post> posts = allPosts.subList(start, end);
        ImageDAO imageDAO = new ImageDAO();
        for (Post post : posts) {
            List<Image> images = imageDAO.getImages(post.getHouse().getHouse_id());

            for (Image image : images) {
                String imageDataBase64 = Base64.getEncoder().encodeToString(image.getImageData());
                image.setImageDataAsBase64(imageDataBase64);
            }
            post.getHouse().setImage(images);
            System.out.println("post message" + post.getAdmin_message());
        }
        
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
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

}
