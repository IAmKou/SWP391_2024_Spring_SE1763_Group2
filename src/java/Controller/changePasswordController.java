/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name = "changePasswordController", urlPatterns = {"/changePasswordController"})
public class changePasswordController extends HttpServlet {

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
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String newCfPass = request.getParameter("cfpass");
        UserDAO dao = new UserDAO();
        User user = (User) request.getSession().getAttribute("account");
        int uid = user.getUser_id();
        Account account = dao.getAccount(uid);
        if (!oldPass.equals(account.getPass_word())) {
            request.setAttribute("mess1", "Old password not match");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else {
            if (newPass.equals(newCfPass)) {
                dao.ChangePassword(user.getUser_id(), newPass);
                String msg = "Change password successfully";
                String redirectURL = request.getContextPath() + "/viewProfile";
                redirectURL += "?message=" + msg;
                response.sendRedirect(redirectURL);
            } else {
                request.setAttribute("mess1", "New pass and confirm not match");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
