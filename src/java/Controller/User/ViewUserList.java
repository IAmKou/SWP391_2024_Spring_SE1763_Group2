/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.*;

/**
 *
 * @author Acer
 */
public class ViewUserList extends HttpServlet {

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
        try {
            Account accLogin = (Account) request.getSession().getAttribute("user");
            if (accLogin != null) {
                if (accLogin.getRole_id() == 1) {

                    UserDAO uDAO = new UserDAO();
                    List<User> uList = uDAO.allUserList();

                    int currentPage = 1;
                    int maxAcc = 4;

                    if (request.getParameter("page") != null) {
                        currentPage = Integer.parseInt(request.getParameter("page"));

                        // Kiểm tra nếu requestedPage nhỏ hơn 1, đặt currentPage là 1
                        if (currentPage < 1) {
                            currentPage = 1;
                        }
                    }
                    int totalAcc = uList.size();
                    int totalPages = (int) Math.ceil((double) totalAcc / maxAcc);

                    if (currentPage > totalPages && totalPages > 0) {
                        currentPage = totalPages;
                    }

                    int start = (currentPage - 1) * maxAcc;
                    int end = Math.min(start + maxAcc, totalAcc);

                    List<User> subUList = uList.subList(start, end);

                    //  ArrayList<User> uList = new ArrayList<>();
                    if (uList.isEmpty()) {
                        request.setAttribute("msg", "List is Empty.");

                    } else {
                        HashMap<User, Account> uMap = new HashMap<>();

                        for (User user : subUList) {
                            Account acc = uDAO.getAccount(user.getUser_id());
                            if (acc != null) {
                                uMap.put(user, acc);
                            }

                        }
                        request.setAttribute("userList", uMap);
                    }
                    //request.setAttribute("ownerPost", subUList);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", currentPage);
                    request.getRequestDispatcher("views/ViewUserList.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("error/Forbidden_403.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("error/Account_not_Found.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Xử lý ngoại lệ ở đây
            e.printStackTrace(); // In ra lỗi trong console cho mục đích gỡ lỗi
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi lấy danh sách người dùng.");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
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
