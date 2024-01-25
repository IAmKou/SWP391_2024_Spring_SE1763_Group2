/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dao.userDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import model.user;

/**
 *
 * @author ACER
 */
@WebServlet(name="addUserController", urlPatterns={"/addUserController"})
public class addUserController extends HttpServlet {
   
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
            HttpSession session = request.getSession();
            userDAO dao = new userDAO();
            String msg = "";
            //Get data from page
            String fullname = request.getParameter("fullname");
            String DoB = request.getParameter("dob");
            SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
            String address = request.getParameter("address");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String email = request.getParameter("email");          
            //Validate phone 
            if (dao.phoneIsExist(phone)) {
                msg = "This username has already existed!!!";
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
            //validate email
            else if (dao.emailIsExist(email)) {
                msg = "This email has already existed!!!";
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
//            //Add user to DB
            else{
                 try {        
                    java.util.Date date = availDate.parse(DoB);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    dao.insertUser(fullname,sqlDate,address,phone,email);
                     request.setAttribute("email", email);
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
        }
            }                   
        } 
        catch (Exception e) {
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
