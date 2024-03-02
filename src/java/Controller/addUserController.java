/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import model.Account;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name = "addUserController", urlPatterns = {"/addUserController"})
public class addUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        try {
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            String msg = "";
            //Get data from page
            String email = request.getParameter("email");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            String cfpass = request.getParameter("cfpass");
            String fullname = request.getParameter("fullname");
            String DoB = request.getParameter("dob");
            SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            //Validate pass
            if (!pass.equals(cfpass)) {
                msg = "Confirm pass not match !!!";
            } //Validate phone 
            else if (dao.phoneIsExist(phone)) {
                msg = "This username has already existed!!!";                
            } //validate email
            else if (dao.emailIsExist(email)) {
                msg = "This email has already existed!!!";
            } else {
                msg="Account created successfullly";
                java.util.Date date = availDate.parse(DoB);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());              
                dao.insertUser(fullname, sqlDate, address, phone, email);           
                User u = dao.getUserByEmail(email);
                int uid = u.getUser_id();
                dao.insertAccount(uid, uname, pass, 2,true);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("logIn.jsp").forward(request, response);
                return;
            }            
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
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
