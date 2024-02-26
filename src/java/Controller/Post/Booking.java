/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.BookingDAO;
import dao.HouseDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class Booking extends HttpServlet {

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

        // Kiểm tra user null và điều hướng về trang đăng nhập nếu cần
        if (user == null) {
            response.sendRedirect("../logIn.jsp"); 
            return; // Dừng xử lý tiếp theo
        }

        String house_id_str = request.getParameter("house_id");
        int house_id = Integer.parseInt(house_id_str);

        int customer_id = user.getUser_id();
        
        BookingDAO bookingDAO = new BookingDAO();
        
        //check duplicate 
        if(bookingDAO.checkDuplicateBooking(house_id, customer_id)){
            request.setAttribute("notification", "You already booked this house.");
            request.getRequestDispatcher("../views/post.jsp").forward(request, response);
        }

        // Lấy ngày và giờ hiện tại
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted_date_time = currentDateTime.format(formatter);

        HouseDAO houseDAO = new HouseDAO();
        int house_owner_id = houseDAO.getOwnerId(house_id);
        
        // Kiểm tra nếu người dùng là chủ nhà
        if (customer_id == house_owner_id) {
            request.setAttribute("alert", "You cannot book your own house.");
            request.getRequestDispatcher("../views/post.jsp").forward(request, response);
        } else {
            // Thực hiện đặt 
            
            bookingDAO.addBooking(customer_id, house_id, formatted_date_time, 1);
            request.setAttribute("success", "Booking successfully.");
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
