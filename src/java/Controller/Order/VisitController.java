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
            int postId = getPostId(request);
            checkVisiting(customer.getUser_id(), postId);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime meetingDateTime = getMeetingDateTime(request, currentDateTime);
            String note = getNote(request);
            Meeting meeting = createMeeting(customer, currentDateTime, meetingDateTime, note, postId);
            addMeetingToDataBase(meeting);
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

    private int getPostId(HttpServletRequest request) throws Exception {
        String postIdStr = request.getParameter("post_id");
        if (postIdStr == null || postIdStr.isEmpty()) {
            throw new Exception("this house is unavailable.");
        }
        return Integer.parseInt(postIdStr);
    }

    private void checkVisiting(int user_id, int postId) throws Exception {
        PostDAO postDao = new PostDAO();
        int owner_house = postDao.getOwnerId(postId);
        if (user_id == owner_house) {
            throw new Exception("You're unable to visit your own property.");
        }

        MeetingDAO meetingDao = new MeetingDAO();
        if (meetingDao.checkDuplicate(user_id, postId)) {
            throw new Exception("Your order is being processed. Visiting is unavailable at the moment.");
        }
    }

    private LocalDateTime getMeetingDateTime(HttpServletRequest request, LocalDateTime currentDateTime) throws Exception {
        String meetingDateStr = request.getParameter("meeting_date");
        String meetingTimeStr = request.getParameter("meeting_time");
        LocalDateTime meetingDateTime = LocalDateTime.parse(meetingDateStr + "T" + meetingTimeStr);

        if (meetingDateTime.isBefore(currentDateTime) || meetingDateTime.equals(currentDateTime)) {
            throw new Exception("Your meeting date is not valid.");
        } else {
            Duration duration = Duration.between(currentDateTime, meetingDateTime);
            long hour = duration.toHours();
            if (hour < 24) {
                throw new Exception("The visiting time must be at least 1 day from the booking time.");
            }
        }

        return meetingDateTime;
    }

    private String getNote(HttpServletRequest request) throws Exception {
        String note = request.getParameter("note");

        if (note == null) {
            return null;
        }

        note = note.trim();
        if (note.length() > 100) {
            throw new Exception("Your note must less than 100 characters.");
        }

        return note;
    }

    private Meeting createMeeting(User customer, LocalDateTime currentDateTime, LocalDateTime meetingDateTime, String note, int postId) {
        Meeting meeting = new Meeting();
        meeting.setCustomer(customer);
        meeting.setBookingDate(currentDateTime);
        meeting.setNote(note);
        meeting.setPostId(postId);
        meeting.setMeetingDate(meetingDateTime);

        Status meetingStatus = new Status();
        meetingStatus.setStatus_id(1);
        meeting.setMeetingStatus(meetingStatus);

        return meeting;
    }

    private void addMeetingToDataBase(Meeting meeting) throws Exception {
        MeetingDAO meetingDao = new MeetingDAO();
        try {
            meetingDao.addMeeting(meeting);
        } catch (Exception e) {
            throw new Exception("An error occurred while processing the visiting.");
        }
    }

}
