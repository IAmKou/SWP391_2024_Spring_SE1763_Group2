/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 *
 * @author ACER
 */
public class handleAutoEndPost implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Start the background task when the web application starts
        autoEndPost.startAutoEndPost();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop the background task when the web application stops
        autoEndPost.stopAutoEndPost();
    }
        public static boolean isAutoEndPostRunning() {
        return autoEndPost.isRunning();
    }
}
