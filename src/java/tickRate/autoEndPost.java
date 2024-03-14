/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import dao.NotificationDAO;
import dao.PostDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.Post;

/**
 *
 * @author ACER
 */
public class autoEndPost {
    private static boolean isRunning = true;
    private static Set<Integer> notifiedPosts = new HashSet<>();

    public static void startAutoEndPost() {
        Thread thread = new Thread(() -> {
            while (isRunning) {
                checkPostEndTimes();
                try {
                    // Sleep for some time before checking again (e.g., every day)
                    Thread.sleep(60000); // 24 hours in milliseconds set 60000 for checking every 1 min
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

 private static void checkPostEndTimes() {
        LocalDateTime now = LocalDateTime.now();
        PostDAO dao = new PostDAO();
        ArrayList<Post> nepList = dao.getAllPost();
        for (Post post : nepList) {
            if (!notifiedPosts.contains(post.getPost_id())) { // Check if post has already been notified
                LocalDateTime endTime = post.getEnd_time();
                if (now.isAfter(endTime)) {
                    updateStatusInDatabase(post.getPost_id());
                    System.out.println(post.getPoster_id());
                    sendNotification(post.getPoster_id(), now);
                    notifiedPosts.add(post.getPost_id()); // Mark post as notified
                }
            }
        }
    }

    public static void stopAutoEndPost() {
        isRunning = false;
    }

    private static void updateStatusInDatabase(int post_id) {

        PostDAO dao = new PostDAO();
        dao.changePostStatus(post_id, 7);
    }

    private static void sendNotification(int poster_id, LocalDateTime time){
        NotificationDAO dao = new NotificationDAO();
        String message = "Your post have expired";
        dao.insertNotification(poster_id, message, time);
    }
        public static boolean isRunning() {
        return isRunning;
    }
}

