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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Post;

/**
 *
 * @author FPTSHOP
 */
public class DeactivePost extends HttpServlet {

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
        Account admin = (Account) session.getAttribute("user");

        if (admin == null || admin.getRole_id() != 1) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String postIdStr = request.getParameter("postId");

        try {
            if (postIdStr == null || postIdStr.isEmpty()) {
                throw new Exception("Post ID is required.");
            }

            int postId = Integer.parseInt(postIdStr);
            PostDAO postDao = new PostDAO();
            Post post = postDao.getPost(postId);

            boolean isActive = post.isActive();
            boolean newStatus = !isActive;

            postDao.DeactivePost(newStatus, postId);
            String successMessage = (newStatus) ? "Activated" : "Deactivated";
            request.setAttribute("success", successMessage + " successfully at post ID " + post.getPost_id());

        } catch (NumberFormatException e) {
            request.setAttribute("alert", "Invalid Post ID format.");
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
