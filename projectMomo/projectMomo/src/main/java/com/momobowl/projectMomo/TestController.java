//package com.momobowl.projectMomo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@RestController
//    public class TestController {
//        @Autowired
//        private DataSource dataSource;
//
//        @GetMapping("/test-connection")
//        public String testConnection() {
//            try (Connection connection = dataSource.getConnection()) {
//                return "Connection successful: " + connection.getMetaData().getURL();
//            } catch (SQLException e) {
//                return "Connection failed: " + e.getMessage();
//            }
//        }
//    }
