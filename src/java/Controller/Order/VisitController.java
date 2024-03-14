/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Order;

import dao.HouseDAO;
import dao.MeetingDAO;
import dao.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import model.Meeting;
import model.Status;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class VisitController extends HttpServlet {

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
        User customer = (User) session.getAttribute("account");
        try {
            MeetingDAO meetingDao = new MeetingDAO();

            String postIdStr = request.getParameter("post_id");
            int postId = Integer.parseInt(postIdStr);
            
            PostDAO postDao = new PostDAO();
            int owner_house = postDao.getOwnerId(postId);
            
            if(customer.getUser_id() == owner_house){
                throw new Exception("You cannot book your own house.");
            }
            
            if (meetingDao.checkDuplicate(customer.getUser_id(), postId)) {
                 throw new Exception("You already book this house.");
            }
            
            LocalDateTime currentDateTime = LocalDateTime.now();

            String meetingDateStr = request.getParameter("meeting_date");
            String meetingTimeStr = request.getParameter("meeting_time");
            String meetingCombine = meetingDateStr + "T" + meetingTimeStr;
            LocalDateTime meetingDateTime = LocalDateTime.parse(meetingCombine);

            if (meetingDateTime.isBefore(currentDateTime) || meetingDateTime.equals(currentDateTime)) {
                throw new Exception("Your meeting date is not valid.");
            } else {
                Duration duration = Duration.between(currentDateTime, meetingDateTime);
                long hour = duration.toHours();
                if (hour < 24) {
                    throw new Exception("Meeting time must be 24 hour greater than the time of booking.");
                }
            }

            Meeting meeting = new Meeting();
            meeting.setCustomer(customer);
            meeting.setBookingDate(currentDateTime);

            if (request.getParameter("note") != null) {
                meeting.setNote(request.getParameter("note").trim());
            } else {
                meeting.setNote("");
            }

            meeting.setPostId(postId);
            meeting.setMeetingDate(meetingDateTime);

            Status meetingStatus = new Status();
            meetingStatus.setStatus_id(1);
            meeting.setMeetingStatus(meetingStatus);

            meetingDao.addMeeting(meeting);

            request.setAttribute("success", "Book successfully.");
            request.getRequestDispatcher("../views/post.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("alert", e.getMessage());
            request.getRequestDispatcher("../views/post.jsp").forward(request, response);
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
