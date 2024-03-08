/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Booking;

import dao.BookingDAO;
import dao.PostDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
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

        try {
            BookingDAO bookingDao = new BookingDAO();

            String postIdStr = request.getParameter("post_id");
            int postId = Integer.parseInt(postIdStr);

            PostDAO postDao = new PostDAO();
            int owner_house = postDao.getOwnerPost(postId);

            if (user.getUser_id() == owner_house) {
                throw new Exception("You cannot book your own house.");
            }

            if (bookingDao.checkDuplicateBooking(user.getUser_id(), postId)) {
                throw new Exception("You already book this house.");
            }

            String quantityOfPeople = request.getParameter("peoples");
            int people = Integer.parseInt(quantityOfPeople);

            if (people > 20 || people < 0) {
                throw new Exception("Your quantity of people must less than 20 and greater than 0.");
            }

            String paymentMethodStr = request.getParameter("payment_method");
            int paymentMethod = Integer.parseInt(paymentMethodStr);

            LocalDateTime currentDate = LocalDateTime.now();

            String checkinDateStr = request.getParameter("check_in_date");
            String checkinTimeStr = request.getParameter("check_in_time");

            String checkoutDateStr = request.getParameter("check_out_date");
            String checkoutTimeStr = request.getParameter("check_out_time");

            String checkIntCombine = checkinDateStr + "T" + checkinTimeStr;
            String checkOutStringCombine = checkoutDateStr + "T" + checkoutTimeStr;

            LocalDateTime checkInDate = LocalDateTime.parse(checkIntCombine);
            LocalDateTime checkOutDate = LocalDateTime.parse(checkOutStringCombine);

            if (checkInDate.equals(currentDate)) {
                throw new Exception("Your check-out date is not valid.");
            } else {
                Duration duration = Duration.between(currentDate, checkInDate);
                long hour = duration.toHours();
                if (hour < 12) {
                    throw new Exception("Check-in time must be 12 hour greater than the time of booking");
                }
            }

            if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
                throw new Exception("Your check-out date is not valid.");
            } else {
                Duration duration = Duration.between(checkInDate, checkOutDate);
                long hour = duration.toHours();
                if (hour < 24) {
                    throw new Exception("The minimum number of rental days is 1 day.");
                }
            }

            Booking booking = new Booking();

            Status bookingStatus = new Status();
            bookingStatus.setStatus_id(1);

            booking.setStatus(bookingStatus);
            booking.setBooking_date(currentDate);
            booking.setPost_id(postId);
            booking.setCheck_in_date(checkInDate);
            booking.setCheck_out_date(checkOutDate);
            PaymentMethod payment = new PaymentMethod();
            payment.setMethod_id(paymentMethod);
            booking.setPayment_method(payment);
            booking.setUser(user);

            if (request.getParameter("note") != null) {
                if (request.getParameter("note").length() < 100) {
                    String note = request.getParameter("note").trim();
                    booking.setNote(note);
                } else {
                    throw new Exception("Your note must less than 200 characters.");
                }
            } else {
                String note = "";
                booking.setNote(note);
            }

            bookingDao.addBooking(booking);

            request.setAttribute("success", "Booking successfully.");
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
