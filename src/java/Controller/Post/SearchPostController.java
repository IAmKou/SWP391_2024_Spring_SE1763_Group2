/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.SearchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Post;

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
        String searchContent2 = request.getParameter("content2");
        ArrayList<Post> list = new ArrayList<>();
        switch (searchBy) {
            case "purpose":
                list = getPostByPurpose(searchContent);
                break;
            case "address":
                list = getPostByAddress(searchContent);
                break;
            case "type":
                list = getPostByTypeOfHouse(searchContent);
                break;
            case "user":
                list = getPostByUser(searchContent);
                break;
            case "number":
                list = getPostByNumberOfRoom(searchContent);
                break;
            case "price":
                list = getPostByPrice(searchContent, searchContent2);
                break;
            case "area":
                list = getPostByArea(searchContent, searchContent2);
                break;
        }
        if(list.isEmpty()){
            request.setAttribute("msg", "No Post Found");
            request.getRequestDispatcher("/views/post.jsp").forward(request, response);
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/post.jsp").forward(request, response);
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

    private ArrayList<Post> getPostByPurpose(String purpose_id) {
        SearchDAO dao = new SearchDAO();
        int pid = Integer.parseInt(purpose_id);
        return dao.getAllPostByPurPose(pid);
    }

    private ArrayList<Post> getPostByAddress(String address) {
        SearchDAO dao = new SearchDAO();
        return dao.getAllPosyByLocation(address);
    }
    private ArrayList<Post> getPostByTypeOfHouse(String type){
        SearchDAO dao = new SearchDAO();
        return dao.getAllPostByTypeOfHouse(type);
    }
    private ArrayList<Post> getPostByUser(String name){
        SearchDAO dao = new SearchDAO();
        return dao.getAllPostByUser(name);
    }
    private ArrayList<Post> getPostByNumberOfRoom(String num){
        SearchDAO dao = new SearchDAO();
        int numroom = Integer.parseInt(num);
        return dao.getAllPosyByNumberOfRoom(numroom);
    }
    private ArrayList<Post> getPostByPrice(String min, String max){
        SearchDAO dao = new SearchDAO();
        int minPrice = Integer.parseInt(min);
        int maxPrice = Integer.parseInt(max);
        return dao.getAllPostByPrice(minPrice, maxPrice);
    }
    private ArrayList<Post> getPostByArea(String min, String max){
        SearchDAO dao = new SearchDAO();
        int minArea = Integer.parseInt(min);
        int maxArea = Integer.parseInt(max);
        return dao.getAllPostByArea(minArea, maxArea);
    }
}
