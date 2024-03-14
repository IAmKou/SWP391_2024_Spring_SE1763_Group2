/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Order;

import dao.OrderDAO;
import dao.PostDAO;
import dao.StatusDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Post;
import model.Status;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class ViewUserOrder extends HttpServlet {

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
        
        String dateParam = request.getParameter("date");
        String statusParam = request.getParameter("status");
        int currentPage = 1;
        int recordsPerPage = 10;

        // Xác định trang hiện tại từ tham số "page" của request
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));

            // Kiểm tra nếu requestedPage nhỏ hơn 1, đặt currentPage là 1
            if (currentPage < 1) {
                currentPage = 1;
            }
        }

        OrderDAO orderDao = new OrderDAO();
        List<Order> allOrders = orderDao.getOrderListByCustomer(user.getUser_id());
        
        if (dateParam != null && !dateParam.isEmpty()) {
            LocalDate date = LocalDate.parse(dateParam);
            allOrders = filterByDate(allOrders, date);
        }
        if (statusParam != null && !statusParam.isEmpty()) {
            int status_id = Integer.parseInt(statusParam);
            allOrders = filterByStatus(allOrders, status_id);
        }
        
        int totalOrders = allOrders.size();
        int totalPages = (int) Math.ceil((double) totalOrders / recordsPerPage);

        // Kiểm tra nếu currentPage vượt quá totalPages, đặt lại currentPage là totalPages
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(currentPage * recordsPerPage, totalOrders);

        List<Order> orders = allOrders.subList(start, end);

        PostDAO postDao = new PostDAO();
        List<Post> posts = new ArrayList<>(); 
      
        
        for (Order allOrder : allOrders) {
            Post post = new Post();
            if (allOrder.getBooking().getPost_id() != 0) {
                post = postDao.getPost(allOrder.getBooking().getPost_id());
            } else {
                post = postDao.getPost(allOrder.getMeeting().getPostId());
            }
            posts.add(post);
        }

        StatusDAO statusDao = new StatusDAO();
        List<Status> statuses = statusDao.getStatus();
        
        request.setAttribute("statuses", statuses);
        request.setAttribute("posts", posts);
        request.setAttribute("bookings", orders);
        request.setAttribute("totalBooking", allOrders.size());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("../views/user/userRequest.jsp").forward(request, response);
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

    public List<Order> filterByDate(List<Order> orders, LocalDate date) {
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getBooking() != null && order.getBooking().getBooking_date() != null && order.getBooking().getBooking_date().toLocalDate().equals(date)) {
                filteredOrders.add(order);
            } else if (order.getMeeting() != null && order.getMeeting().getBookingDate() != null && order.getMeeting().getBookingDate().toLocalDate().equals(date)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
    
    private List<Order> filterByStatus(List<Order> orders, int status_id) {
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getBooking() != null && order.getBooking().getStatus() != null && order.getBooking().getStatus().getStatus_id() == status_id) {
                filteredOrders.add(order);
            }else if(order.getMeeting() != null && order.getMeeting().getMeetingStatus()!= null && order.getMeeting().getMeetingStatus().getStatus_id() == status_id){
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
