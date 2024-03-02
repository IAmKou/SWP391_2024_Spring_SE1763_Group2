package Controller.User;

import dao.AccountDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Account;
import model.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class updateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO uDAO = new UserDAO();
        User user = (User) request.getSession().getAttribute("account");
        if (user != null) {
            request.setAttribute("param1", user);
            request.setAttribute("account", uDAO.getAccount(user.getUser_id()));
            System.out.println(user);
        } else {
            request.setAttribute("msg", "Not found Account");
        }

        request.getRequestDispatcher("views/editProfile.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String msg = "";
            User u = (User) request.getSession().getAttribute("account");
            int userId = u.getUser_id();
            
            String full_name = request.getParameter("full_name");
            String dateOfBirthStr = request.getParameter("date_of_birth");
            String address = request.getParameter("address");
            String phone_number_str = request.getParameter("phone_number");
            String email = request.getParameter("email");
            Part filePart = request.getPart("file");
            // Kiểm tra xem các trường thông tin có được cung cấp không
            if (full_name == null || dateOfBirthStr == null || address == null || phone_number_str == null || email == null) {
                throw new ServletException("Vui lòng điền đầy đủ thông tin.");
            }

            // Kiểm tra định dạng của ngày sinh
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateOfBirthUtil;
            try {
                dateOfBirthUtil = dateFormat.parse(dateOfBirthStr);
            } catch (ParseException e) {
                throw new ServletException("Định dạng ngày sinh không hợp lệ. Sử dụng định dạng yyyy-MM-dd.");
            }
            java.sql.Date date_of_birth = new java.sql.Date(dateOfBirthUtil.getTime());

            // Kiểm tra định dạng của số điện thoại
//            int phone_number;
//            try {
//                phone_number = Integer.parseInt(phone_number_str);
//            } catch (NumberFormatException e) {
//                throw new ServletException("Số điện thoại phải là một số nguyên.");
//            }
            // Kiểm tra định dạng của email
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new ServletException("Địa chỉ email không hợp lệ.");
            }
            InputStream inputStream = filePart.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileBytes = outputStream.toByteArray();

            // Determine the media type based on the file's extension
            String fileName = filePart.getSubmittedFileName();
            String mediaTypeString;
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                mediaTypeString = "image/jpeg";
            } else if (fileName.endsWith(".png")) {
                mediaTypeString = "image/png";
            } else {
                String redirectURL = request.getContextPath() + "/updateProfileController?userId=" + userId;
                msg = " Unsupported file types";
                redirectURL += "&message=" + msg; 
                response.sendRedirect(redirectURL);
                return;
            }
            // Create the request body with the file content and determined media type
            RequestBody requestBody = RequestBody.create(MediaType.parse(mediaTypeString), fileBytes);
            //upload to imgur
            Request requestImgur = new Request.Builder()
                    .header("Authorization", "Client-ID f939fe0c5f68029")
                    .url("https://api.imgur.com/3/image")
                    .post(requestBody)
                    .build();
            try ( Response responseImgur = new OkHttpClient().newCall(requestImgur).execute()) {
                if (!responseImgur.isSuccessful()) {
                    throw new IOException("Unexpected code " + responseImgur);
                }

                String responseBody = responseImgur.body().string(); // Read the response body once
                System.out.println(responseBody); // Print the response body for debugging

                String imageUrl = extractImageUrl(responseBody);
                if (imageUrl != null) {
                    System.out.println("Extracted imageUrl: " + imageUrl);
                    // Tạo đối tượng User và cập nhật vào cơ sở dữ liệu
                    User user = new User();

                    user.setUser_id(u.getUser_id()); // ???
                    user.setFull_name(full_name);
                    user.setDate_of_birth(date_of_birth);
                    user.setAddress(address);
                    user.setPhone_number(phone_number_str);
                    user.setEmail(email);
                    user.setAvatar(imageUrl);
                    // Cập nhật người dùng trong cơ sở dữ liệu
                    UserDAO userDAO = new UserDAO();
                    userDAO.updateUser(user);
                    
                    HttpSession session = request.getSession();
                    session.removeAttribute("account");
                    session.removeAttribute("user");
                    int uid = user.getUser_id();
                    AccountDAO dao = new AccountDAO();
                    Account a = dao.getAccountByUserId(uid);
                    session.setAttribute("user", a);
                    session.setAttribute("account", user);
                    
                    msg="Update successfully";
                    String redirectURL = request.getContextPath() + "/viewProfile" ;
                    redirectURL += "?message=" + msg; 
                    response.sendRedirect(redirectURL);   
                }
            } catch (Exception e) {
                // Xử lý bất kỳ ngoại lệ nào
                String redirectURL = request.getContextPath() + "/updateProfileController?userId=" + userId;
                msg = e.getMessage();
                redirectURL += "&message=" + msg; 
                response.sendRedirect(redirectURL);
            }
        } catch (ServletException se) {
                String redirectURL = request.getContextPath() + "/updateProfileController";
                redirectURL += "?message=" + se.getMessage(); 
                response.sendRedirect(redirectURL);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private static String extractImageUrl(String responseBody) {
        try {
            // Parse response JSON
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONObject jsonData = jsonResponse.getJSONObject("data");
            return jsonData.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
