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
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import static jdk.nashorn.internal.objects.NativeError.getFileName;
import model.House;
import model.Post;
import model.TypeOfHouse;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class addNewPost extends HttpServlet {

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
            out.println("<title>Servlet addHouse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addHouse at " + request.getContextPath() + "</h1>");
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
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("account");
    String location = request.getParameter("location");
    System.out.println("Purpose value: " + location);
        String purpose_str = request.getParameter("purpose");
        System.out.println("Purpose value: " + purpose_str);
        int purpose = Integer.parseInt(purpose_str);
        String price_str = request.getParameter("price");
        int price = Integer.parseInt(price_str);
        //int poster = user.getUser_id();
     
        String poster_str = request.getParameter("user");
        int poster = Integer.parseInt(poster_str);
        User user = new User();
        user.setUser_id(4);
        
        
        String type_str = request.getParameter("type");
        int type = Integer.parseInt(type_str);
        
        String description = request.getParameter("description");
        String area_str = request.getParameter("area");
        int area = Integer.parseInt(area_str);
        String number_of_room_str = request.getParameter("number_of_room");
        int number_of_room = Integer.parseInt(number_of_room_str);
        // Lấy danh sách các phần (ảnh) từ yêu cầu
//        Collection<Part> imageParts = request.getParts();
//        List<String> imageUrls = new ArrayList<>();
//
//// Thư mục lưu trữ ảnh trên máy chủ
//        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
//
//// Tạo thư mục nếu nó chưa tồn tại
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//
//// Lặp qua từng phần ảnh và lưu trữ nó trên máy chủ
//        for (Part part : imageParts) {
//            String fileName = UUID.randomUUID().toString() + "_" + getFileName(part);
//            String imageUrl = "uploads" + File.separator + fileName;
//
//            try ( InputStream is = part.getInputStream();  OutputStream os = new FileOutputStream(uploadPath + File.separator + fileName)) {
//                // Lưu trữ ảnh trên máy chủ
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = is.read(buffer)) != -1) {
//                    os.write(buffer, 0, bytesRead);
//                }
//
//                // Thêm đường dẫn ảnh vào danh sách
//                imageUrls.add(imageUrl);
//            }
//        }
        House house = new House();

        Post post = new Post();
        post.setHouse_status(4);
        post.setPrice(price);
        post.setPurpose_id(purpose);
        post.setPoster_id(poster);

        TypeOfHouse tOfHouse = new TypeOfHouse();
        tOfHouse.setType_of_house_id(type);

        // Đặt giá trị cho đối tượng House
        house.setLocation(location);
        house.setDescription(description);
        house.setHouse_owner(user);
        house.setArea(area);
        house.setType_of_house(tOfHouse);
        house.setNumber_of_room(number_of_room);
//        house.setImage_URL(imageUrls);
        // Thêm house và post vào cơ sở dữ liệu
        HouseDAO houseDAO = new HouseDAO();
        houseDAO.addHouse(house, post);
        
        request.setAttribute("alert", "Add successfully!");

        // Chuyển hướng người dùng sau khi thêm ngôi nhà
        request.getRequestDispatcher("../houseView/addHouse.jsp").forward(request, response);
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
