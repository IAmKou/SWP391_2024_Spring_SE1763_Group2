/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servlet;

import dao.userDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.user;

/**
 *
 * @author ACER
 */
@WebServlet(name="registerController", urlPatterns={"/registerController"})
public class registerController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        try {
            userDAO dao = new userDAO();
            String msg = "";
            boolean flag = true;
            //Get data from page
            String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            String cfPassWord = request.getParameter("cfpassword");
            String fullName = request.getParameter("fullname");
            
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String location = request.getParameter("location");
            int roleid = Integer.parseInt(request.getParameter("roleid"));
            
             if (dao.userNameIsExist(userName)) {
                msg = "This username has already existed!!!";
                flag = false;
            }
            // validate password
            if (!passWord.equals(cfPassWord)) {
                msg = "Repeat password is not correct";
                flag = false;
            }
            if (dao.emailIsExist(email)) {
                msg = "This email has already existed!!!";
                flag = false;
            }
            //Add user to DB
            if (flag = true) {
                //If flag = true, add User to db
                user u = new user();
                u.setUserName(userName);
                u.setPassWord(passWord);
                u.setFullName(fullName);
                u.setEmail(email);
                u.setPhone(phone);
                u.setLocation(location);
                u.setRoleID(roleid);
                dao.insertUser(u);
                //msg = "Add user successfully!!!"; 
               request.getRequestDispatcher("mainPage.jsp").forward(request, response);
            }
            else {
                //If flag = false, send msg to page
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
        }     catch (Exception e) {
            System.out.println(e.getMessage());
                           }
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
