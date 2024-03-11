/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Post;

/**
 *
 * @author FPTSHOP
 */
public class ChangePostStatusByAdmin extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account admin = (Account) session.getAttribute("user");

        if (admin == null || admin.getRole_id() != 1) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String postIdStr = request.getParameter("postId");
        String statusIdStr = request.getParameter("statusId");

        if (postIdStr == null || postIdStr.isEmpty() || statusIdStr == null || statusIdStr.isEmpty()) {
            request.setAttribute("alert", "Post ID and status are required.");
            request.getRequestDispatcher("/post/list").forward(request, response);
            return;
        }

        try {
            int postId = Integer.parseInt(postIdStr);
            int statusId = Integer.parseInt(statusIdStr);
            
            if(statusId != 2 && statusId != 3){
                throw new Exception("this status is not valid.");
            }
            
            PostDAO postDao = new PostDAO();
            Post post = postDao.getPost(postId);

            if (post == null) {
                throw new Exception("Post not found.");
            }

            if (post.getPost_status().getStatus_id() != 1) {
                throw new Exception("This post cannot change status.");
            }

            postDao.changePostStatusByAdmin(postId, statusId, admin.getUser_id());
            request.setAttribute("success", "Updated successfully at Post ID: " + post.getPost_id());
        } catch (NumberFormatException e) {
            request.setAttribute("alert", "Invalid Post ID or Status ID format.");
        } catch (Exception e) {
            request.setAttribute("alert", e.getMessage());
        }

        request.getRequestDispatcher("/post/list").forward(request, response);
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
