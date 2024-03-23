/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.PostDAO;
import java.io.IOException;
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
            request.getRequestDispatcher("/logIn.jsp").forward(request, response);
            return;
        }

        try {
            int postId = validatePostId(request);
            int statusId = validatestatusIdStr(request);
            String message = getMessage(request);
            PostDAO postDao = new PostDAO();
            Post post = postDao.getPost(postId);

            if (post == null) {
                throw new Exception("Post not found.");
            }

            if (post.getPost_status().getStatus_id() != 1) {
                throw new Exception("This post cannot change status.");
            }

            postDao.changePostStatusByAdmin(postId, statusId, admin.getUser_id(), message);
            request.setAttribute("success", "Updated successfully at Post ID: " + post.getPost_id());
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

    private int validatePostId(HttpServletRequest request) throws Exception {
        String postIdStr = request.getParameter("postId");

        if (postIdStr == null || postIdStr.isEmpty()) {
            throw new Exception("This post is unavailable.");
        }

        return Integer.parseInt(postIdStr);
    }

    private int validatestatusIdStr(HttpServletRequest request) throws Exception {
        String statusIdStr = request.getParameter("statusId");

        if (statusIdStr == null || statusIdStr.isEmpty()) {
            throw new Exception("This status is unavailable.");
        }
        
        int statusId = Integer.parseInt(statusIdStr);
        if (statusId != 2 && statusId != 3) {
            throw new Exception("this status is not valid.");
        }
        return statusId;
    }

    private String getMessage(HttpServletRequest request) throws Exception {
        String message = request.getParameter("message");

        if (message == null || message.isEmpty()) {
            return null;
        }

        message = message.trim();
        if (message.length() > 100) {
            throw new Exception("Your note must less than 100 characters.");
        }

        return message;
    }
}
