/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Order;

import dao.BookingDAO;
import dao.PostDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import model.Account;
import model.Booking;
import model.PaymentMethod;
import model.Status;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class BookingController extends HttpServlet {

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
        User user = (User) session.getAttribute("account");
        Account acc = (Account) session.getAttribute("user");

        if (acc == null || acc.getRole_id() != 2) {
            request.getRequestDispatcher("/logIn.jsp").forward(request, response);
            return;
        }
        try {
            int postId = getPostId(request);
            validateBooking(postId, user);
            int people = getPeopleCount(request);
            int paymentMethod = getPaymentMethod(request);
            String note = getNote(request);
            LocalDateTime checkInDate = getCheckInDateTime(request);
            LocalDateTime checkOutDate = getCheckOutDateTime(request);
            LocalDateTime currentDate = LocalDateTime.now();
            validateDateTime(checkInDate, checkOutDate, currentDate);
            Booking booking = createBooking(user, postId, people, paymentMethod, checkInDate, checkOutDate, note, currentDate);
            addBookingToDatabase(booking);
            request.setAttribute("success", "Booking successfully.");
        } catch (Exception e) {
            request.setAttribute("alert", e.getMessage());
        }
        request.getRequestDispatcher("../views/post.jsp").forward(request, response);
    }

    private int getPostId(HttpServletRequest request) throws Exception {
        String postIdStr = request.getParameter("post_id");
        if (postIdStr == null || postIdStr.isEmpty()) {
            throw new Exception("this house is unavailable.");
        }
        return Integer.parseInt(postIdStr);
    }

    private void validateBooking(int postId, User user) throws Exception {
        PostDAO postDao = new PostDAO();
        int ownerHouse = postDao.getOwnerId(postId);
        if (user.getUser_id() == ownerHouse) {
            throw new Exception("You're unable to book your own property.");
        }

        BookingDAO bookingDao = new BookingDAO();
        if (bookingDao.checkDuplicateBooking(user.getUser_id(), postId)) {
            throw new Exception("Your order is being processed. Booking is unavailable at the moment.");
        }
    }

    private int getPeopleCount(HttpServletRequest request) throws Exception {
        String quantityOfPeople = request.getParameter("peoples");
        if (quantityOfPeople == null || quantityOfPeople.isEmpty()) {
            throw new Exception("Your quantity of people is empty.");
        }
        int people = Integer.parseInt(quantityOfPeople);
        if (people > 20 || people < 0) {
            throw new Exception("Number of people must be between 1 and 20.");
        }
        return people;
    }

    private int getPaymentMethod(HttpServletRequest request) throws Exception {
        String paymentMethodStr = request.getParameter("payment_method");
        if (paymentMethodStr == null || paymentMethodStr.isEmpty()) {
            throw new Exception("Payment method is empty.");
        }
        return Integer.parseInt(paymentMethodStr);
    }

    private LocalDateTime getCheckInDateTime(HttpServletRequest request) throws Exception {
        String checkinDateStr = request.getParameter("check_in_date");
        String checkinTimeStr = request.getParameter("check_in_time");

        if (checkinDateStr == null || checkinTimeStr == null) {
            throw new Exception("Check-in date or time is empty.");
        }

        LocalDateTime checkInDateTime = LocalDateTime.parse(checkinDateStr + "T" + checkinTimeStr);

        return checkInDateTime;
    }

    private LocalDateTime getCheckOutDateTime(HttpServletRequest request) throws Exception {
        String checkoutDateStr = request.getParameter("check_out_date");
        String checkoutTimeStr = request.getParameter("check_out_time");

        if (checkoutDateStr == null || checkoutTimeStr == null) {
            throw new Exception("Check-out date or time is empty.");
        }

        LocalDateTime checkOutDateTime = LocalDateTime.parse(checkoutDateStr + "T" + checkoutTimeStr);

        return checkOutDateTime;
    }

    private void validateDateTime(LocalDateTime checkInDate, LocalDateTime checkOutDate, LocalDateTime currentDate) throws Exception {

        validateCheckOutDate(checkInDate, checkOutDate);
        validateCheckInTime(checkInDate, currentDate);
    }

    private Booking createBooking(User user, int postId, int people, int paymentMethod, LocalDateTime checkInDate, LocalDateTime checkOutDate, String note, LocalDateTime currentDate) {
        Booking booking = new Booking();

        Status bookingStatus = new Status();
        bookingStatus.setStatus_id(1);

        booking.setStatus(bookingStatus);
        booking.setBooking_date(currentDate);
        booking.setPost_id(postId);
        booking.setQuantityOfpeople(people);
        booking.setNote(note);
        booking.setCheck_in_date(checkInDate);
        booking.setCheck_out_date(checkOutDate);
        PaymentMethod payment = new PaymentMethod();
        payment.setMethod_id(paymentMethod);
        booking.setPayment_method(payment);
        booking.setUser(user);

        return booking;
    }

    private void addBookingToDatabase(Booking booking) throws Exception {
        BookingDAO bookingDao = new BookingDAO();
        try {
            bookingDao.addBooking(booking);
        } catch (SQLException e) {
            throw new Exception("An error occurred while processing the booking.");
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

    private void validateCheckOutDate(LocalDateTime checkInDate, LocalDateTime checkOutDate) throws Exception {
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new Exception("Your check-out date is not valid.");
        }

        Duration duration = Duration.between(checkInDate, checkOutDate);
        long hour = duration.toHours();
        if (hour < 24) {
            throw new Exception("The minimum number of rental days is 1 day.");
        }
    }

    private void validateCheckInTime(LocalDateTime checkInDate, LocalDateTime currentDate) throws Exception {
        if (checkInDate.equals(currentDate)) {
            throw new Exception("Your check-in date is not valid.");
        }

        Duration duration = Duration.between(currentDate, checkInDate);
        long hour = duration.toHours();
        if (hour < 12) {
            throw new Exception("Check-in time must be 12 hour greater than the time of booking");
        }
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

}
