/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Feedback;

import dao.FeedbackDAO;
import dao.NotificationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.GNotification;
import model.Notification;
import model.User;

/**
 *
 * @author luong
 */
@WebServlet(name="DeleteFeedbackAdmin", urlPatterns={"/DeleteFeedbackAdmin"})
public class DeleteFeedbackAdmin extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("fid"));
        int fuid = Integer.parseInt(request.getParameter("fuid"));
        int rid = Integer.parseInt(request.getParameter("rid"));
        LocalDateTime now = LocalDateTime.now();
        String message = "Your feedback have been deleted because it have violated the community rule";
        User user = (User) request.getSession().getAttribute("account");
        int uid = user.getUser_id();
             
        FeedbackDAO dao = new FeedbackDAO();
        NotificationDAO ddao = new NotificationDAO();
        dao.deleteReport(rid);
        dao.deleteFeedback(fid);
        ddao.insertNotification(fuid, message, now);
        ArrayList<Notification> noti = ddao.getAllNotificationByUserId(uid);
        ArrayList<GNotification> gnoti = ddao.getAllGlobalNotification();
        List<Object> allNotifications = new ArrayList<>();
        allNotifications.addAll(noti);
        allNotifications.addAll(gnoti);
        HttpSession session = request.getSession();
        session.removeAttribute("notifications");
        session.setAttribute("notifications", allNotifications);
        request.setAttribute("msg", "Deleted succesfully");
        request.getRequestDispatcher("ViewAllReport").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
