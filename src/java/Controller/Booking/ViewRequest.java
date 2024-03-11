/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Booking;

import dao.OrderDAO;
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
import model.User;
import model.Order;
import model.Status;

/**
 *
 * @author FPTSHOP
 */
public class ViewRequest extends HttpServlet {

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
        String customerNameParam = request.getParameter("customer_name");
        String statusStr = request.getParameter("status");

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
        List<Order> allOrders = orderDao.getOrderListByOwnerHouse(user.getUser_id());

        if (dateParam != null && !dateParam.isEmpty()) {
            LocalDate date = LocalDate.parse(dateParam);
            allOrders = filterByDate(allOrders, date);
        }

        if (customerNameParam != null && !customerNameParam.isEmpty()) {
            customerNameParam = customerNameParam.trim();
            allOrders = filterByCustomerName(allOrders, customerNameParam);
        }

        if (statusStr != null && !statusStr.isEmpty()) {
            int status = Integer.parseInt(statusStr);
            allOrders = filterByStatus(allOrders, status);
        }

        int totalBookings = allOrders.size();
        int totalPages = (int) Math.ceil((double) totalBookings / recordsPerPage);

        // Kiểm tra nếu currentPage vượt quá totalPages, đặt lại currentPage là totalPages
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(currentPage * recordsPerPage, totalBookings);

        List<Order> orders = allOrders.subList(start, end);

        StatusDAO statusDAO = new StatusDAO();
        List<Status> statuses = statusDAO.getStatus();

        request.setAttribute("statuses", statuses);
        request.setAttribute("requests", orders);
        request.setAttribute("totalRequest", allOrders.size());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("../views/user/customerRequest.jsp").forward(request, response);
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

    public List<Order> filterByStatus(List<Order> orders, int status) {
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getBooking() != null && order.getBooking().getStatus() != null
                    && order.getBooking().getStatus().getStatus_id() == status) {
                filteredOrders.add(order);
            } else if (order.getMeeting() != null && order.getMeeting().getMeetingStatus() != null
                    && order.getMeeting().getMeetingStatus().getStatus_id() == status) {
                filteredOrders.add(order);
            }
        }

        return filteredOrders;

    }

    public List<Order> filterByCustomerName(List<Order> orders, String customerName) {
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getBooking() != null && order.getBooking().getUser() != null
                    && order.getBooking().getUser().getFull_name().trim().equalsIgnoreCase(customerName)) {
                filteredOrders.add(order);
            } else if (order.getMeeting() != null && order.getMeeting().getCustomer() != null
                    && order.getMeeting().getCustomer().getFull_name().trim().equalsIgnoreCase(customerName)) {
                filteredOrders.add(order);
            }
        }

        return filteredOrders;
    }
}
