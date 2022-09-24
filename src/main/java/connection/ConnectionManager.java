package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

    private static Connection con;

    public static Connection getConnection() {

        try {
            // get the mysql class  driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // create database if not exists
            createDatabase();
            // get the database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music", "root", "admin");

            // get tables if not exists
            createTables(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    private static void createDatabase() {
        Statement st;
        try {
            // create a connection without a database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");

            st = con.createStatement();
            // create the music database
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS music");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createTable(String table, Connection connection) {

        try {
            Statement st = connection.createStatement();
            // create a table based on sql
            st.execute(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection con) {
        // sql string for user table
        String userTable = "CREATE TABLE IF NOT EXISTS `user` (\n"
                + "  `id` int NOT NULL AUTO_INCREMENT,\n"
                + "  `username` varchar(45) DEFAULT NULL,\n"
                + "  `password` varchar(45) DEFAULT NULL,\n"
                + "  `isAdmin` varchar(45) DEFAULT NULL,\n"
                + "  `email` varchar(45) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id`)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        // sql string for song table
        String songTable = "CREATE TABLE IF NOT EXISTS `song` (\n"
                + "  `id` int NOT NULL AUTO_INCREMENT,\n"
                + "  `title` varchar(45) DEFAULT NULL,\n"
                + "  `artist` varchar(45) DEFAULT NULL,\n"
                + "  `price` varchar(45) DEFAULT NULL,\n"
                + "  `description` varchar(1000) DEFAULT NULL,\n"
                + "  `salesAmount` int DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id`)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
       // sql string for order table
        String orderTable = "CREATE TABLE  IF NOT EXISTS `order` (\n"
                + "  `id` int NOT NULL AUTO_INCREMENT,\n"
                + "  `userId` int DEFAULT NULL,\n"
                + "  `songId` int DEFAULT NULL,\n"
                + "  `price` varchar(45) DEFAULT NULL,\n"
                + "  `quantity` int DEFAULT NULL,\n"
                + "  `total` int DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id`),\n"
                + "  KEY `userId_idx` (`userId`),\n"
                + "  KEY `songId_idx` (`songId`),\n"
                + "  CONSTRAINT `songId` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE CASCADE,\n"
                + "  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        // persist the tables into databases
        createTable(userTable, con);
        createTable(songTable, con);
        createTable(orderTable, con);
    }
}
