package Controller.User;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class updateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            String fullName = request.getParameter("fullName");
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");
            String location = request.getParameter("location");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            
  
            User user = new User();
            user.setFullName(fullName);
            user.setUserName(userName);
            user.setPassWord(passWord);
            user.setLocation(location);
            user.setPhone(phone);
            user.setEmail(email);
            
            // Update the user in the database
            UserDAO userDAO = new UserDAO();
            userDAO.updateUser(user);
            
            // Redirect to a success page
            response.sendRedirect("userProfile.jsp");
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