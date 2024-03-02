/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import dao.PostDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Post;

/**
 *
 * @author ACER
 */
public class autoEndPost {

    private static boolean isRunning = true;

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
            LocalDateTime endTime = post.getEnd_time();
            if (now.isAfter(endTime)) {
                updateStatusInDatabase(post.getPost_id());
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
}
