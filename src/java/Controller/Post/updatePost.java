/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import dao.HouseDAO;
import dao.ImageDAO;
import dao.PostDAO;
import dao.PurposeDAO;
import dao.TypeOfHouseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import model.Account;
import model.House;
import model.Image;
import model.Post;
import model.Purpose;
import model.Status;
import model.TypeOfHouse;

/**
 *
 * @author FPTSHOP
 */
@MultipartConfig
public class UpdatePost extends HttpServlet {

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
            out.println("<title>Servlet updateHouse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateHouse at " + request.getContextPath() + "</h1>");
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
        Account acc = (Account) session.getAttribute("user");

        if (acc == null || acc.getRole_id() != 2) {
            request.getRequestDispatcher("/logIn.jsp").forward(request, response);
            return;
        }
        
        String post_id_str = request.getParameter("post_id");
        int post_id = Integer.parseInt(post_id_str);
        PostDAO post_DAO = new PostDAO();
        Post post = post_DAO.getPost(post_id);

        TypeOfHouseDAO type_of_house_DAO = new TypeOfHouseDAO();
        List<TypeOfHouse> types = type_of_house_DAO.getType();

        PurposeDAO purpose_DAO = new PurposeDAO();
        List<Purpose> purposes = purpose_DAO.getPurpose();

        ImageDAO imageDAO = new ImageDAO();
        List<Image> images = imageDAO.getImages(post.getHouse().getHouse_id());

        for (Image image : images) {
            String imageDataBase64 = Base64.getEncoder().encodeToString(image.getImageData());
            image.setImageDataAsBase64(imageDataBase64);
        }
        post.getHouse().setImage(images);

        session.setAttribute("purposes", purposes);
        session.setAttribute("types", types);
        session.setAttribute("post", post);
        request.getRequestDispatcher("../views/editPost.jsp").forward(request, response);
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

        String location = request.getParameter("location");
        String regex = "^[\\p{L}0-9.,\\/\\s]+$";

        try {
            if (!location.matches(regex)) {
                throw new Exception("Your location contains invalid characters.");
            }
            String description = request.getParameter("description");
            description = description.trim();

            String house_id_str = request.getParameter("house_id");
            String post_id_str = request.getParameter("post_id");
            int house_id = Integer.parseInt(house_id_str);
            int post_id = Integer.parseInt(post_id_str);

            location = location.trim();

            String purpose_str = request.getParameter("purpose");
            int purpose_id = Integer.parseInt(purpose_str);

            String price_str = request.getParameter("price");
            price_str = price_str.trim();
            int price = Integer.parseInt(price_str);

            String area_str = request.getParameter("area");
            area_str = area_str.trim();
            int area = Integer.parseInt(area_str);

            String type_of_house_str = request.getParameter("type");
            int type_of_house = Integer.parseInt(type_of_house_str);

            String number_of_room_str = request.getParameter("number_of_room");

            if (location.length() >= 150 || description.length() >= 2000) {
                throw new Exception("Location or description is too long.");
            }

            if (price <= 0 || area <= 0) {
                throw new Exception("Price and area must be positive numeric values.");
            }

            //đặt giá trị cho các thuộc tính của đối tượng
            TypeOfHouse typeOfHouse = new TypeOfHouse();
            typeOfHouse.setType_of_house_id(type_of_house);

            Status post_status = new Status();
            post_status.setStatus_id(1);

            Purpose purpose = new Purpose();
            purpose.setPurpose_id(purpose_id);

            Post post = new Post();
            post.setPost_id(post_id);
            post.setPurpose(purpose);
            post.setPrice(price);
            post.setPost_status(post_status);

            House house = new House();
            house.setHouse_id(house_id);
            house.setLocation(location);
            house.setType_of_house(typeOfHouse);
            house.setDescription(description);
            house.setArea(area);

            if (!number_of_room_str.isEmpty()) {
                number_of_room_str = number_of_room_str.trim();
                int number_of_room = Integer.parseInt(number_of_room_str);
                if (number_of_room <= 0) {
                    throw new Exception("Number of rooms must be a positive numeric value.");
                }
                house.setNumber_of_room(number_of_room);
            }

            HouseDAO houseDAO = new HouseDAO();
            houseDAO.updateHouse(house);

            PostDAO postDAO = new PostDAO();
            postDAO.updatePost(post);

            //xử lí phần hình ảnh
            try {

                ImageDAO imageDAO = new ImageDAO();
                Collection<Part> parts = request.getParts();
                boolean hasImage = false;

                for (Part part : parts) {
                    if (part.getContentType() != null && part.getContentType().startsWith("image")) {
                        hasImage = true;
                        break;
                    }
                }
                if (hasImage) {

                    imageDAO.deleteImages(house_id);

                    for (Part part : parts) {
                        if (part.getContentType() != null && part.getContentType().startsWith("image")) {
                            InputStream fileContent = part.getInputStream();
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = fileContent.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                            byte[] imageData = outputStream.toByteArray();

                            imageDAO.addImages(house_id, imageData);

                            outputStream.close();
                            fileContent.close();
                        }

                    }
                }

            } catch (IOException exception) {
                request.setAttribute("alert", "An error occur when upload image.");
                request.getRequestDispatcher("../views/editPost.jsp").forward(request, response);
            }

            request.setAttribute("success", "Update successfully.");
            request.getRequestDispatcher("../views/editPost.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            String alert = "Invalid data format. Please enter valid numeric values.";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("../views/editPost.jsp").forward(request, response);
        } catch (Exception ex) {
            String alert = ex.getMessage();
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("../views/editPost.jsp").forward(request, response);
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
