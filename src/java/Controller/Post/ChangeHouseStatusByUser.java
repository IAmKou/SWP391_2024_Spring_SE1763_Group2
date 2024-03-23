/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.HouseDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author FPTSHOP
 */
public class ChangeHouseStatusByUser extends HttpServlet {

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
        Account acc = (Account) session.getAttribute("user");

        if (acc == null || acc.getRole_id() != 2) {
            request.getRequestDispatcher("/logIn.jsp").forward(request, response);
            return;
        }
        try {
            int postId = validatePostId(request);
            int statusId = validatestatusIdStr(request);
            addNewStatusToDataBase(postId, statusId);
            request.setAttribute("success", "Update house status at Post ID: " + postId + " successfully.");
        } catch (Exception e) {
            request.setAttribute("alert", e.getMessage());
        }
        request.getRequestDispatcher("/post/view").forward(request, response);
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

    private int validatePostId(HttpServletRequest request) throws Exception {
        String postIdStr = request.getParameter("post_id");

        if (postIdStr == null || postIdStr.isEmpty()) {
            throw new Exception("This post is unavailable.");
        }

        return Integer.parseInt(postIdStr);
    }

    private int validatestatusIdStr(HttpServletRequest request) throws Exception {
        String statusIdStr = request.getParameter("current_status");

        if (statusIdStr == null || statusIdStr.isEmpty()) {
            throw new Exception("This status is unavailable.");
        }

        return Integer.parseInt(statusIdStr);
    }

    private void addNewStatusToDataBase(int postId, int statusId) throws Exception {
        try {
            HouseDAO houseDao = new HouseDAO();
            int newStatusId = (statusId == 4) ? 5 : 4;
            houseDao.updateHouseStatus(postId, newStatusId);
        } catch (Exception e) {
            throw new Exception("An error occurred while processing.");
        }
    }

}
