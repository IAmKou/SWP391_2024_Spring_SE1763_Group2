/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.HouseDAO;
import dao.PostDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import model.House;
import model.Post;

/**
 *
 * @author Acer
 */
public class viewAllPost_For_User_View extends HttpServlet {

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
            out.println("<title>Servlet viewAllPost_For_User_View</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewAllPost_For_User_View at " + request.getContextPath() + "</h1>");
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

        PostDAO pDao = new PostDAO();
        HouseDAO hDao = new HouseDAO();
        if (request.getParameter("type") != null) {
            String pam = (String) request.getParameter("type");

            int purpose = Integer.parseInt(pam);

            HashMap<Post, House> cardList = new HashMap<>();
            ArrayList<Post> pList = pDao.allPost();

            if (purpose == 1) {
                request.setAttribute("title", "For Sale");
            } else if (purpose == 2) {
                request.setAttribute("title", "For Rent");
            } else if (purpose == 0) {
                request.setAttribute("title", "View All Post");
            } else {
                String oldLink = "/views/home.jsp";
                request.setAttribute("oldLink", oldLink);
                request.setAttribute("h1", "Not Found");
                request.setAttribute("p", "The resource is not exists.");
                request.getRequestDispatcher("error/Not_Found_404.jsp").forward(request, response);
            }
//            request.setAttribute("num", pDao.getPostCard(purpose).size());
            for (Post p : pList) {
//                if ( p.getPurpose().getPurpose_id() == purpose) {
                    House h = hDao.getHouseByPostID(p.getPost_id());
                    if (h != null) {
                        cardList.put(p, hDao.getHouseByPostID(p.getPost_id()));
//                    }
                }
            }

            request.setAttribute("numcheck", pList.size());
            request.setAttribute("num", cardList.size());
            request.setAttribute("hnum", hDao.numberOfHouse());
            request.setAttribute("cardList", cardList);

            request.getRequestDispatcher("/views/generalPostList.jsp").forward(request, response);

        } else {

            String oldLink = "/views/home.jsp";
            request.setAttribute("oldLink", oldLink);
            request.setAttribute("h1", "Not Found");
            request.setAttribute("p", "The resource is not exists.");
            request.getRequestDispatcher("error/Not_Found_404.jsp").forward(request, response);
        }

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

}
