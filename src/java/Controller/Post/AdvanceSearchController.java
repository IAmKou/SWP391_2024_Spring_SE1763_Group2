/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.HouseDAO;
import dao.SearchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.House;
import model.Post;

/**
 *
 * @author luong
 */
@WebServlet(name = "AdvanceSearchController", urlPatterns = {"/AdvanceSearchController"})
public class AdvanceSearchController extends HttpServlet {

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
        String searchBy = request.getParameter("type");
        String searchContent = request.getParameter("content");
        String address = request.getParameter("by");
        String type = request.getParameter("house");
        if (type != null && type.isEmpty()) {
            type = null;
        }
        String room = request.getParameter("room");
        if (address != null && address.isEmpty()) {
            address = null;
        }
        int price = Integer.parseInt(request.getParameter("price"));
        int area = Integer.parseInt(request.getParameter("area"));
        String purpose = request.getParameter("purpose");
        ArrayList<Post> list = new ArrayList<>();
        HouseDAO hDao = new HouseDAO();
        HashMap<Post, House> cardList = new HashMap<>();
        int id = 0;
        if (searchContent != null && !searchContent.isEmpty()) {
            try {
                id = Integer.parseInt(searchContent);
            } catch (NumberFormatException e) {
                Logger.getLogger(SearchPostController.class.getName()).log(Level.SEVERE, "Failed to parse integer", e);
            }
        }
        switch (searchBy) {
            case "purpose":
                list = getPostByPurpose(id, address, type, price, area, room);
                break;
            case "address":
                list = getPostByAddress(purpose, searchContent, type, price, area, room);
                break;
            case "user":
                list = getPostByUser(searchContent, purpose, address, type, price, area, room);
                break;
            case "number":
                list = getPostByNumberOfRoom(purpose, address, type, price, area, id);
                break;
            default:
                String msg = "unba bunga";

        }
        if (list.isEmpty()) {
            request.setAttribute("msg", "no");
        } else {
            for (Post p : list) {
                House h = hDao.getHouseByPostID(p.getPost_id());
                if (h != null) {
                    cardList.put(p, h);
                }
            }
            request.setAttribute("type", searchBy);
            request.setAttribute("current", searchContent);
            request.setAttribute("num", cardList.size());
            request.setAttribute("cardList", cardList);
        }
        request.getRequestDispatcher("/views/generalPostList.jsp").forward(request, response);
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

    private ArrayList<Post> getPostByPurpose(int purpose, String address, String type, int price, int area, String num) {
        SearchDAO dao = new SearchDAO();
        Integer room = null; // Declare room variable outside the if block
        if (num != null && !num.isEmpty()) { // Check if num is not null before checking if it is empty
            room = Integer.parseInt(num); // Parse num to an integer if it's not empty
        }
        // Call the method to retrieve posts with the specified parameters
        return dao.advanceGetPostByAddressNumTypePriceArea(address, room, type, price, area, purpose);
    }

    private ArrayList<Post> getPostByAddress(String purpose, String address, String type, int price, int area, String num) {
        SearchDAO dao = new SearchDAO();
        Integer pur = null;
        Integer room = null;
        if (num != null && !num.isEmpty() && purpose != null && !purpose.isEmpty()) {
            room = Integer.parseInt(num);
            pur = Integer.parseInt(purpose);
        }
        return dao.advanceGetPostByPurposeNumTypePriceArea(pur, room, address, type, price, area);
    }

    private ArrayList<Post> getPostByUser(String name, String purpose, String address, String type, int price, int area, String num) {
        SearchDAO dao = new SearchDAO();
        Integer pur = null;
        Integer room = null;
        if (num != null && !num.isEmpty() && purpose != null && !purpose.isEmpty()) {
            room = Integer.parseInt(num);
            pur = Integer.parseInt(purpose);
        }
        return dao.advanceGetPostByPurposeAddressTypePriceAreaNum(pur, address, type, price, area, room, name);
    }

    private ArrayList<Post> getPostByNumberOfRoom(String purpose, String address, String type, int price, int area, int num) {
        SearchDAO dao = new SearchDAO();
        Integer pur = null;
        if (purpose != null && !purpose.isEmpty()) {
            pur = Integer.parseInt(purpose);
        }
        return dao.advanceGetPostByPurposeAddressTypePriceArea(pur, address, type, price, area, num);
    }
}
