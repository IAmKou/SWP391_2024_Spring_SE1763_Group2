/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import dao.AccountDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;
import model.Account;
import model.User;

/**
 *
 * @author Acer
 */
public class changeUserStatus extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changeUserStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changeUserStatus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set exception Link
        String exLink = "error/Forbidden_403.jsp";

        // Check admin
        HttpSession ss = request.getSession();
        Account ac = (Account) ss.getAttribute("user");
        // Get user id
        int userId = 0;
        try {
            String userIdParam = request.getParameter("uid");
            if (userIdParam != null) {
                userId = Integer.parseInt(userIdParam);
            } else {
                // Handle case when "uid" parameter is null
                request.setAttribute("h1", "User not Found");
                request.setAttribute("p", "Not found this user in database");
                exLink = "error/Not_Found_404.jsp";
                ss.setAttribute("oldLink", "/viewUserList");
                request.getRequestDispatcher(exLink).forward(request, response);
                return; // End execution to prevent further processing
            }
        } catch (NumberFormatException e) {
            // Handle case when "uid" parameter is not a valid integer
            System.out.println(e);
            exLink = "error/Not_Found_404.jsp";
            request.setAttribute("h1", "User not Found");
            request.setAttribute("p", "Not found this user in database");
            ss.setAttribute("oldLink", "/viewUserList");
            response.sendRedirect(exLink);
            return; // End execution to prevent further processing
        }

        if (ac != null && ac.getRole_id() == 1) {

            // Get and check user
            UserDAO uDAO = new UserDAO();
            AccountDAO aDAO = new AccountDAO();
            User u = uDAO.getUserByID(userId);
            Account a = uDAO.getAccount(userId);

            //Ngan khong cho admin nay deactive admin khac hoac tu deactive ban than
            if (u != null && a != null && a.getRole_id() != 1) {
                if (a.getUser_id() != ac.getUser_id()) {
                    // Send user data
                    aDAO.changeStatus(a.getUser_id());
                    request.setAttribute("user", u);
                    request.setAttribute("acc", uDAO.getAccount(u.getUser_id()));

                    // Redirect to userProfile
                    response.sendRedirect("userProfile?id=" + userId);

                    return; // End execution to prevent further redirection
                } else {
                    request.setAttribute("actMsg", "Can not deactive another admin or your account");
                }

            } else {
                exLink = "error/Not_Found_404.jsp";
                response.sendRedirect(exLink);
            }

        } else {
            // Default redirection to home.jsp
            response.sendRedirect(exLink);
        }
        response.sendRedirect("userProfile?id=" + userId);
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
        // Set exception Link
        String exLink = "error/Forbidden_403.jsp";

        // Get user id
        int userId = Integer.parseInt(request.getParameter("uid"));

        // Check admin
        HttpSession ss = request.getSession();
        Account ac = (Account) ss.getAttribute("user");
        if (ac != null && ac.getRole_id() == 1) {

            // Get and check user
            UserDAO uDAO = new UserDAO();
            AccountDAO aDAO = new AccountDAO();
            User u = uDAO.getUserByID(userId);
            Account a = uDAO.getAccount(userId);

            //Ngan khong cho admin nay deactive admin khac hoac tu deactive ban than
            if (u != null && a != null && a.getRole_id() != 1) {
                if (a.getUser_id() != ac.getUser_id()) {
                    // Send user data
                    aDAO.changeStatus(a.getUser_id());
                    request.setAttribute("user", u);
                    request.setAttribute("acc", uDAO.getAccount(u.getUser_id()));

                    // Redirect to userProfile
                    response.sendRedirect("userProfile?id=" + userId);

                    return; // End execution to prevent further redirection
                } else {
                    request.setAttribute("actMsg", "Can not deactive another admin or your account");
                }

            } else {
                exLink = "error/Not_Found_404.jsp";
                response.sendRedirect(exLink);
            }

        } else {
            // Default redirection to home.jsp
            response.sendRedirect(exLink);
        }
        response.sendRedirect("userProfile?id=" + userId);
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
