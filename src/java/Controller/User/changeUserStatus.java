/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Acer
 */
public class ChangeUserStatus extends HttpServlet {

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

        String userIdStr = request.getParameter("user_id");
        if (userIdStr == null || userIdStr.isEmpty()) {
            request.setAttribute("alert", "User ID is required.");
            request.getRequestDispatcher("/viewUserList").forward(request, response);
            return;
        }

        String adminPassStr = request.getParameter("admin_password");
        if (!adminPassStr.equals(admin.getPass_word())) {
            request.setAttribute("alert", "Your password is not correct.");
            request.getRequestDispatcher("/viewUserList").forward(request, response);
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);
            AccountDAO accountDao = new AccountDAO();
            Account acc = accountDao.getAccountByUserId(userId);
            
            if (acc == null || acc.getRole_id() ==1) {
                throw new Exception("User with ID " + userId + " not found.");
            }

            int newStatus = (acc.isActive()) ? 0 : 1;
            accountDao.changeStatus(userId, newStatus);
            String statusMessage = (newStatus == 0) ? "Deactivated" : "Activated";
            request.setAttribute("success", statusMessage + " successfully at User ID: " + acc.getUser_id());
        } catch (NumberFormatException e) {
            request.setAttribute("alert", "Invalid User ID format.");
        } catch (Exception e) {
            request.setAttribute("alert", e.getMessage());
        }

        request.getRequestDispatcher("/viewUserList").forward(request, response);
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
