/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Post;

import dao.HouseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.House;
import model.Post;
import model.Purpose;
import model.Status;
import model.TypeOfHouse;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class updatePost extends HttpServlet {
   
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
            out.println("<title>Servlet updateHouse</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateHouse at " + request.getContextPath () + "</h1>");
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
//        processRequest(request, response);
        
        
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
//        HttpSession session = request.getSession();
//        Post post = (Post) session.getAttribute("post");
//        User user = (User) session.getAttribute("account");
        String user_str = request.getParameter("user");
        int user_int = Integer.parseInt(user_str);
        User user = new User();
        user.setUser_id(user_int);

        String purpose_str = request.getParameter("purpose");
        String price_str = request.getParameter("price");
        String location = request.getParameter("location");
        String type_of_house_str = request.getParameter("type");
        String description = request.getParameter("description");
        String area_str = request.getParameter("area");
        String number_of_room_str = request.getParameter("number_of_room");
        
        int purpose_id = Integer.parseInt(purpose_str);
        int price = Integer.parseInt(price_str);
        int type_of_house = Integer.parseInt(type_of_house_str);
        int area = Integer.parseInt(area_str);
        int number_of_room = Integer.parseInt(number_of_room_str);
        TypeOfHouse typeOfHouse = new  TypeOfHouse();
        typeOfHouse.setType_of_house_id(type_of_house);
        
        House house = new House();
        house.setHouse_owner(user);
        house.setLocation(location);
        house.setType_of_house(typeOfHouse);
        house.setDescription(description);
        house.setArea(area);
        house.setNumber_of_room(number_of_room);
        house.setHouse_id(21);
        
        Purpose purpose = new Purpose();
        purpose.setPurpose_id(purpose_id);
        
        Status post_status = new Status();
        post_status.setStatus_id(2);
        
        Post post = new Post();
        post.setPurpose(purpose);
        post.setPrice(price);
        post.setAdmin_id(1);
        post.setPost_status(post_status);
        
        HouseDAO houseDAO = new HouseDAO();
        houseDAO.updateHouse(house,post);
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
