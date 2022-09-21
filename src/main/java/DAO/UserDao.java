package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDao {

    Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    //for register user 
    public boolean saveUser(User user) {
        boolean set = false;
        try {
            //Insert register data to database
            String query = "insert into user(username,password, email, isAdmin) values(?,?,?,?)";

            PreparedStatement pt = this.con.prepareStatement(query);
            pt.setString(1, user.getUsername());
            pt.setString(2, user.getPassword());
            pt.setString(3, user.getEmail());
            pt.setString(4, user.getIsAdmin());

            pt.executeUpdate();
            set = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public User login(String username, String password) {
        String query = "select * from user where username = ? and password = ?";
        User user = null;
        try {
            PreparedStatement pt = this.con.prepareStatement(query);
            pt.setString(1, username);
            pt.setString(2, password);
            ResultSet rst = pt.executeQuery();

            if (rst.next()) {
                username = rst.getString("username");
                password = rst.getString("password");
                String isAdmin = rst.getString("isAdmin");
                String email = rst.getString("email");

                user = new User(username, password, isAdmin, email);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        String query = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String isAdmin = rs.getString("isAdmin");
                users.add(new User(id, username, email, isAdmin));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
}
