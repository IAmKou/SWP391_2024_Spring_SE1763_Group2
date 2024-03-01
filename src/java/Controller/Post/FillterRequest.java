/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Post;

import dao.BookingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class FillterRequest extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        
        String dateParam = request.getParameter("date");
        String statusParam = request.getParameter("status");
        
        int currentPage = 1; // Trang hiện tại, mặc định là 1
        int recordsPerPage = 6; // Số lượng booking trên mỗi trang

        // Xác định trang hiện tại từ tham số "page" của request
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));

            // Kiểm tra nếu requestedPage nhỏ hơn 1, đặt currentPage là 1
            if (currentPage < 1) {
                currentPage = 1;
            }
        }

        BookingDAO bookingDAO = new BookingDAO();
        List<Booking> allBookings = bookingDAO.getListBookingInformation(user.getUser_id());
        
        if (dateParam != null && !dateParam.isEmpty()) {
            LocalDate date = LocalDate.parse(dateParam);
            allBookings = filterByDate(allBookings, date);
        }
        
        if (statusParam != null && !statusParam.isEmpty()) {
            int status_id = Integer.parseInt(statusParam);
            allBookings = filterByStataus(allBookings, status_id);
        }
        
        int totalBookings = allBookings.size();
        int totalPages = (int) Math.ceil((double) totalBookings / recordsPerPage);

        // Kiểm tra nếu currentPage vượt quá totalPages, đặt lại currentPage là totalPages
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(currentPage * recordsPerPage, totalBookings);

        List<Booking> bookings = allBookings.subList(start, end);

        request.setAttribute("requests", bookings);
        request.setAttribute("fillterTotalRequest", allBookings.size());
        request.setAttribute("fillterTotalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/views/user/customerRequest.jsp").forward(request, response);
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

    private List<Booking> filterByDate(List<Booking> bookings, LocalDate date) {
        List<Booking> filteredBooking = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getBooking_date().toLocalDate().equals(date)) {
                filteredBooking.add(booking);
            }
        }
        return filteredBooking;
    }

    private List<Booking> filterByStataus(List<Booking> bookings, int status_id) {
        List<Booking> filteredBooking = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getStatus_id() == status_id) {
                filteredBooking.add(booking);
            }
        }
        return filteredBooking;
    }
}
