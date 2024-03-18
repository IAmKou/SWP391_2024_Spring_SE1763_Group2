/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Feedback;

import dao.AccountDAO;
import dao.FeedbackDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import model.Account;
import model.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig(
    location = "${java.io.tmpdir}",
    maxFileSize = 20848820,    // Maximum size allowed for uploaded files (in bytes)
    maxRequestSize = 418018841 // Maximum size allowed for multipart/form-data requests (in bytes)
)
/**
 *
 * @author luong
 */
@WebServlet(name="FeedbackController", urlPatterns={"/FeedbackController"})
public class FeedbackController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String content = request.getParameter("content");
       int uid = Integer.parseInt(request.getParameter("uid"));
       int pid = Integer.parseInt(request.getParameter("pid"));
       String name = request.getParameter("uname");
       Part filePart = request.getPart("file");
       String msg = "";
       if (filePart!=null){
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
                String redirectURL = "/views/post.jsp";
                msg = " Unsupported file types";
                redirectURL += "?message=" + msg; 
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
                    FeedbackDAO dao = new FeedbackDAO();
                    LocalDateTime now = LocalDateTime.now();
                    dao.insertFeedback(pid, uid, name, now, content,imageUrl);
                    String redirectURL = request.getContextPath() + "/view" ;
                    redirectURL += "?message=" + msg + "&post_id=" + pid; 
                    response.sendRedirect(redirectURL);   
                }
            }
    }else{
                FeedbackDAO dao = new FeedbackDAO();
                    LocalDateTime now = LocalDateTime.now();
                    dao.insertFeedback(pid, uid, name, now, content, null);
                    String redirectURL = request.getContextPath() + "/view" ;
                    redirectURL += "?message=" + msg + "&post_id=" + pid; 
                    response.sendRedirect(redirectURL);   
            } 
    }
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
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
