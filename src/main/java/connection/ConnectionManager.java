package connection;

import java.sql.*;


public class ConnectionManager {
    private static Connection con;
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root","admin");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}