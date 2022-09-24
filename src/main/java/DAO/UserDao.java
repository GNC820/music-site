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

    //for register an user 
    public boolean saveUser(User user) {
        boolean set = false;
        try {
            // create sql query for inserting an user record
            String query = "insert into user(username,password, email, isAdmin) values(?,?,?,?)";

            PreparedStatement pt = this.con.prepareStatement(query);
            // set the ? parameters with the current user values
            pt.setString(1, user.getUsername());
            pt.setString(2, user.getPassword());
            pt.setString(3, user.getEmail());
            pt.setString(4, user.getIsAdmin());

            // persist the user into the database
            pt.executeUpdate();
            set = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public User login(String username, String password) {
        // create sql query for checking if the credetials are valid
        String query = "select * from user where username = ? and password = ?";
        User user = null;
        try {
            PreparedStatement pt = this.con.prepareStatement(query);

            // set the ? parameters with the current user values
            pt.setString(1, username);
            pt.setString(2, password);

            // get the result set( mapping the database information into a Java object)
            ResultSet rst = pt.executeQuery();

            if (rst.next()) {
                // here is the actual mapping
                Integer user_id = rst.getInt("id");
                username = rst.getString("username");
                password = rst.getString("password");
                String isAdmin = rst.getString("isAdmin");
                String email = rst.getString("email");

                // add the user to the Java User object
                user = new User(user_id, username, password, email, isAdmin);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        // create sql query for selecting all users
        String query = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);

            // get the result set( mapping the database information into a Java object)
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object.
            while (rs.next()) {
                // here is the actual mapping
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String isAdmin = rs.getString("isAdmin");

                // add each user to the users list
                users.add(new User(id, username, email, isAdmin));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User getUserById(int id) {
        // create sql query for selecting an user by id
        String query = "select * from user where id = ?";
        User user = null;

        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.con.prepareStatement(query);

            // set the ? parameters with the current id value
            preparedStatement.setInt(1, id);

            // get the result set( mapping the database information into a Java object)
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object.
            while (rs.next()) {
                // here is the actual mapping
                Integer user_id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String isAdmin = rs.getString("isAdmin");

                // add the database user to the Java User object
                user = new User(user_id, username, email, isAdmin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;

        // create sql query for updating an user
        String query = "update user set username = ?,email= ?, isAdmin = ? where id = ?;";

        PreparedStatement preparedStatement;
        preparedStatement = this.con.prepareStatement(query);

        // set the ? parameters with the current user values
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getIsAdmin());
        preparedStatement.setInt(4, user.getId());

        // if rowUpdated > 0 means that the users has been updated
        rowUpdated = preparedStatement.executeUpdate() > 0;

        return rowUpdated;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        // create sql query for deleting an user by his id
        String query = "delete from user where id = ?;";
        PreparedStatement preparedStatement;

        preparedStatement = this.con.prepareStatement(query);

        preparedStatement.setInt(1, id);
        
        // if rowDeleted > 0 means that the users has been deleted
        rowDeleted = preparedStatement.executeUpdate() > 0;

        return rowDeleted;
    }

}
