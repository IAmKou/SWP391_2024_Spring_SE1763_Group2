/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.AccountDAO;
import dao.BookingDAO;
import dao.FeedbackDAO;
import dao.ImageDAO;
import dao.PaymentMethodDAO;
import dao.PostDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Account;
import model.Booking;
import model.Image;
import model.PaymentMethod;
import model.Post;
import model.User;
import model.feedback;

/**
 *
 * @author FPTSHOP
 */
public class ViewPost extends HttpServlet {

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
            out.println("<title>Servlet ViewPost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewPost at " + request.getContextPath() + "</h1>");
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
        PostDAO Pdao = new PostDAO();
        Post post = null;
        BookingDAO dao = new BookingDAO();
        
        String msg = request.getParameter("msg");
        User user = (User) request.getSession().getAttribute("account");
        String post_id_str = request.getParameter("post_id");
        int pid = Integer.parseInt(post_id_str);
        
        if (post_id_str != null && !post_id_str.isEmpty()) {
            int post_id_int = Integer.parseInt(post_id_str);
            post = Pdao.getPost(post_id_int);

        }

        if (post != null) {
            ImageDAO imageDAO = new ImageDAO();
            List<Image> images = imageDAO.getImages(post.getHouse().getHouse_id());

            for (Image image : images) {
                String imageDataBase64 = Base64.getEncoder().encodeToString(image.getImageData());
                image.setImageDataAsBase64(imageDataBase64);
            }
            int uid = user.getUser_id();
            Booking b = dao.getBookingByPost(pid, uid);

            PaymentMethodDAO methodDao = new PaymentMethodDAO();
            List<PaymentMethod> methods = methodDao.getPaymentMethods();

            FeedbackDAO fdao = new FeedbackDAO();
            ArrayList<feedback> f = fdao.getAllFeedbackInAPost(pid);
            
            HttpSession session = request.getSession();
            request.setAttribute("msg", msg);
            session.setAttribute("feedback", f);
            request.setAttribute("bob", b);
            session.setAttribute("methods", methods);
            session.setAttribute("images", images);
            session.setAttribute("post", post);
        }

        request.getRequestDispatcher("/views/post.jsp").forward(request, response);
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
