/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Booking;

import dao.BookingDAO;
import dao.HouseDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import model.Booking;
import model.House;
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

            // Kiểm tra user null và điều hướng về trang đăng nhập nếu cần
            if (user == null) {
                response.sendRedirect("../logIn.jsp");
                return;
            }
            String date = request.getParameter("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành java.util.Date
            java.util.Date utilDate = dateFormat.parse(date);

            // Chuyển đổi java.util.Date thành java.sql.Date
            java.sql.Date meeting_date = new java.sql.Date(utilDate.getTime());
            
            String message = request.getParameter("message");
            message = message.trim();
            String house_id_str = request.getParameter("house_id");

            int house_id = Integer.parseInt(house_id_str);
            int user_id = user.getUser_id();

            if (message.length() > 150) {
                throw new Exception("Your mesage too long.");
            }

            BookingDAO bookingDAO = new BookingDAO();

            // Kiểm tra trùng lặp đặt phòng
            if (bookingDAO.checkDuplicateBooking(house_id, user_id)) {
                throw new Exception("You already booked this house.");
            }

            // Lấy ngày và giờ hiện tại
            LocalDateTime currentDateTime = LocalDateTime.now();

            HouseDAO houseDAO = new HouseDAO();
            int house_owner_id = houseDAO.getOwnerId(house_id);

            // Kiểm tra nếu người dùng là chủ nhà
            if (user_id == house_owner_id) {
                throw new Exception("You cannot book your own house.");
            }
            //set properties for booking order
            Booking order = new Booking();
            House house = new House();
            house.setHouse_id(house_id);
            
            order.setHouse(house);
            order.setUser(user);
            order.setStatus_id(1);
            order.setBooking_date(currentDateTime);
            order.setMessage(message);
            order.setMeeting_date(meeting_date);
            
            bookingDAO.addBooking(order);   
            
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
