/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Order;

import dao.BookingDAO;
import dao.MeetingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author FPTSHOP
 */
public class RejectOrder extends HttpServlet {

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
        Account acc = (Account) session.getAttribute("user");

        if (acc == null || acc.getRole_id() != 2) {
            request.getRequestDispatcher("/logIn.jsp").forward(request, response);
            return;
        }
        try {
            String meetingIdStr = request.getParameter("meeting_id");
            String message = request.getParameter("message");

            if (meetingIdStr == null) {
                throw new Exception("this order is unavailable.");
            }

            int meetingId = Integer.parseInt(meetingIdStr);

            if (message != null) {
                message = message.trim();
                if (message.length() > 100) {
                    throw new Exception("Your message must less than 100 characters");
                }
            }
            if (meetingId != 0) {
                MeetingDAO meetingDao = new MeetingDAO();
                meetingDao.ChangeCancelStatus(meetingId, message);
            } else {
                int bookingId = Integer.parseInt(request.getParameter("booking_id"));
                BookingDAO bookingDao = new BookingDAO();
                bookingDao.changeCancelStatus(bookingId, message);
            }

            request.setAttribute("success", "cancelling order successfully.");
            request.getRequestDispatcher("/request/view").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("alert", e.getMessage());
            request.getRequestDispatcher("/request/view").forward(request, response);
        }
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
