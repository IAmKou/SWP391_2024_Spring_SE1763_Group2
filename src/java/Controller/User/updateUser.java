package Controller.User;

import dao.userDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import model.user;

public class updateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    String full_name = request.getParameter("full_name");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date date_of_birth = (Date) dateFormat.parse(request.getParameter("date_of_birth"));
    String address = request.getParameter("address");
    int phone_number = Integer.parseInt(request.getParameter("phone_number"));
    String email = request.getParameter("email");

    
    user user = new user();
    user.setUser_id(user_id);
    user.setFull_name(full_name);
    user.setDate_of_birth(date_of_birth);
    user.setAddress(address);
    user.setPhone_number(phone_number);
    user.setEmail(email);

            // Update the user in the database
            userDAO userDAO = new userDAO();
            userDAO.updateUser(user);

            // Redirect to a success page
            response.sendRedirect("userProfileUpdate.jsp");
        } catch (Exception e) {
            // Handle any exceptions
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}