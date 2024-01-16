/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.House;

import dao.HouseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.House.House;
import model.House.TypeOfHouse;
import model.User.User;

/**
 *
 * @author FPTSHOP
 */
public class addHouse extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addHouse</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addHouse at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String picture = request.getParameter("picture");
        String type = request.getParameter("type");
        boolean status = request.getParameter("status")!= null;
        
        int price_int = Integer.parseInt(price);
        int type_int = Integer.parseInt(type);

        House house = new House();
        
        TypeOfHouse tOfHouse = new TypeOfHouse();
        tOfHouse.setTypeOfHouseId(type_int);
        
        house.setLocation(location);
        house.setDescription(description);
        house.setHouseOwnerId(user);
        house.setPicture(picture);
        house.setPrice(price_int);
        house.setType(tOfHouse);
        house.setStatus(status);
        
        HouseDAO houseDAO = new HouseDAO();
        houseDAO.addHouse(house);
        request.setAttribute("alert", "add thanh cong!");
        request.getRequestDispatcher("../houseView/addHouse.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
