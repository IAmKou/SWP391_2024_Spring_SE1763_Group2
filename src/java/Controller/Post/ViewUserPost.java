/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.ImageDAO;
import dao.PostDAO;
import dao.PurposeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.House;
import model.Image;
import model.Post;
import model.Purpose;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class ViewUserPost extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        String priceParam = request.getParameter("price");
        String purposeParam = request.getParameter("purpose");
        String dateParam = request.getParameter("date");

        int currentPage = 1;
        int recordsPerPage = 3;

        // Xác định trang hiện tại từ tham số "page" của request
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));

            // Kiểm tra nếu requestedPage nhỏ hơn 1, đặt currentPage là 1
            if (currentPage < 1) {
                currentPage = 1;
            }
        }
        PostDAO postDAO = new PostDAO();
        List<Post> allPosts = postDAO.getUserPost(user.getUser_id());

        if (priceParam != null && !priceParam.isEmpty()) {
            // Xử lý tiêu chí filter theo giá
            // Ví dụ: Filter bài đăng có giá dưới mức giá được chỉ định
            double price = Double.parseDouble(priceParam);
            allPosts = filterByPrice(allPosts, price);
        }

        if (purposeParam != null && !purposeParam.isEmpty()) {
            // Xử lý tiêu chí filter theo mục đích
            // Ví dụ: Filter bài đăng theo mục đích (bán, thuê, cho thuê)
            int purposeId = Integer.parseInt(purposeParam);
            allPosts = filterByPurpose(allPosts, purposeId);
        }

        if (dateParam != null && !dateParam.isEmpty()) {
            // Xử lý tiêu chí filter theo ngày
            // Ví dụ: Filter bài đăng theo ngày đăng
            LocalDate date = LocalDate.parse(dateParam);
            allPosts = filterByDate(allPosts, date);
        }

        int totalPosts = allPosts.size();
        int totalPages = (int) Math.ceil((double) totalPosts / recordsPerPage);

        // Kiểm tra nếu currentPage vượt quá totalPages, đặt lại currentPage là totalPages
        if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(currentPage * recordsPerPage, totalPosts);

        List<Post> posts = allPosts.subList(start, end);
        ImageDAO imageDAO = new ImageDAO();
        for (Post post : posts) {
            List<Image> images = imageDAO.getImages(post.getHouse().getHouse_id());

            for (Image image : images) {
                String imageDataBase64 = Base64.getEncoder().encodeToString(image.getImageData());
                image.setImageDataAsBase64(imageDataBase64);
            }
            post.getHouse().setImage(images);
            
        }

        PurposeDAO purposeDAO = new PurposeDAO();
        List<Purpose> purposes = purposeDAO.getPurpose();

        request.setAttribute("purposes", purposes);
        request.setAttribute("ownerPost", posts);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("../views/profile.jsp").forward(request, response);
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

    // Hàm lọc bài đăng theo giá
    private List<Post> filterByPrice(List<Post> posts, double price) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getPrice() <= price) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }

    // Hàm lọc bài đăng theo mục đích
    private List<Post> filterByPurpose(List<Post> posts, int purpose_id) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getPurpose().getPurpose_id() == purpose_id) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }

    // Hàm lọc bài đăng theo ngày
    private List<Post> filterByDate(List<Post> posts, LocalDate date) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getCreate_time().toLocalDate().equals(date)) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }
}
