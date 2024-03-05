/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.NotificationDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Account;
import model.GNotification;
import model.Notification;

/**
 *
 * @author ACER
 */
@WebServlet(name = "logInController", urlPatterns = {"/logInController"})
public class logInController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req
     * @param resp
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        UserDAO dao = new UserDAO();
        NotificationDAO dun = new NotificationDAO();
        Account account = dao.LogIn(user, pass);
        if (account == null) {
            req.setAttribute("username", user);
            req.setAttribute("message", "Login Failed.");
            req.getRequestDispatcher("logIn.jsp").forward(req, resp);
        } else {
            if (account.isActive()) {
                int uid = account.getUser_id();
                User userInfo = dao.getUserInformation(uid);
                ArrayList<Notification> noti = dun.getAllNotificationByUserId(uid);
                ArrayList<GNotification> gnoti = dun.getAllGlobalNotification();
                HttpSession session = req.getSession();

                req.setAttribute("message", "Login succesful");
                List<Object> allNotifications = new ArrayList<>();
                allNotifications.addAll(noti);
                allNotifications.addAll(gnoti);
                session.setAttribute("notifications", allNotifications);
                session.setAttribute("user", account);
                session.setAttribute("account", userInfo);
                System.err.println(account.isActive());
                req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Your Account is inactive or banned!!!");
                req.getRequestDispatcher("logIn.jsp").forward(req, resp);
            }
        }
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
