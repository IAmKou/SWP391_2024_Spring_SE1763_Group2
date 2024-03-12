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
import model.Post;
import java.util.logging.*;
import model.House;

/**
 *
 * @author luong
 */
@WebServlet(name = "SearchPostController", urlPatterns = {"/SearchPostController"})
public class SearchPostController extends HttpServlet {

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
        ArrayList<Post> list = new ArrayList<>();
        int id=0;
        if (searchContent != null && !searchContent.isEmpty()) {
        try {
            id = Integer.parseInt(searchContent);
        } catch (NumberFormatException e) {
              Logger.getLogger(SearchPostController.class.getName()).log(Level.SEVERE, "Failed to parse integer", e);
        }
    }
        switch (searchBy) {
            case "purpose":
                list = getPostByPurpose(id);
                break;
            case "address":
                list = getPostByAddress(searchContent);
                break;
            case "user":
                list = getPostByUser(searchContent);
                break;
            case "number":
                list = getPostByNumberOfRoom(searchContent);
                break;
            case "price":
                list = getPostByPrice(searchContent);
                break;
            case "area":
                list = getPostByArea(searchContent);
                break;
            default:
               String msg = "unba bunga";
                
        }
        if (list.isEmpty()) {
            request.setAttribute("msg", "no");
        }
            request.setAttribute("type", searchBy);
            request.setAttribute("current", searchContent);
            request.setAttribute("num", list.size());
            request.setAttribute("cardList", list);
        
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

    private ArrayList<Post> getPostByPurpose(int purpose_id) {
        SearchDAO dao = new SearchDAO();
        return dao.getAllPostByPurPose(purpose_id);
    }

    private ArrayList<Post> getPostByAddress(String address) {
        SearchDAO dao = new SearchDAO();
        return dao.getAllPosyByLocation(address);
    }

    private ArrayList<Post> getPostByUser(String name) {
        SearchDAO dao = new SearchDAO();
        return dao.getAllPostByUser(name);
    }

    private ArrayList<Post> getPostByNumberOfRoom(String num) {
        SearchDAO dao = new SearchDAO();
        int numroom = 0;
        if (!num.isEmpty() && num != null){
        numroom = Integer.parseInt(num);
        }
        return dao.getAllPostByNumberOfRoom(numroom);
    }
    private ArrayList<Post> getPostByPrice(String price) {
        SearchDAO dao = new SearchDAO();
        int pri = 0;
        if (!price.isEmpty() && price != null){
        pri = Integer.parseInt(price);
        }
        return dao.getAllPostByPrice(pri);
    }
        private ArrayList<Post> getPostByArea(String price) {
        SearchDAO dao = new SearchDAO();
        int pri = 0;
        if (!price.isEmpty() && price != null){
        pri = Integer.parseInt(price);
        }
        return dao.getAllPostByArea(pri);
    }
    
}
