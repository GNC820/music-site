package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

public class OrderDao {

    Connection con;

    public OrderDao(Connection con) {
        this.con = con;
    }

    public boolean saveOrder(Order order) {
        boolean set = false;
        try {
            // create sql query for inserting an order record
            String query = "insert into music.order(songId, userId, price, quantity, total) values(?,?,?,?,?)";

            PreparedStatement pt = this.con.prepareStatement(query);

            // set the ? parameters with the current order value
            pt.setInt(1, order.getSongId());
            pt.setInt(2, order.getUserId());
            pt.setString(3, order.getPrice());
            pt.setInt(4, order.getQuantity());
            pt.setInt(5, order.getTotal());

            // persist the order into the database
            pt.executeUpdate();
            set = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public List<Order> selectAllOrdersByUserId(Integer userId) {
        // create sql query for selecting all the orders by the user id
        String query = "SELECT * FROM music.order where userId = ? ;";
        List<Order> orders = new ArrayList<Order>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);

            // set the ? parameters with the current order value
            preparedStatement.setInt(1, userId);

            // get the result set( mapping the database information into a Java object)
            ResultSet rs = preparedStatement.executeQuery();

            // process the ResultSet object.
            while (rs.next()) {
                // here is the actual mapping
                Integer id = rs.getInt("id");
                Integer song_id = rs.getInt("songId");
                Integer user_id = rs.getInt("userId");
                String price = rs.getString("price");
                Integer quantity = rs.getInt("quantity");
                Integer total = rs.getInt("total");

                // add each order to the order list
                orders.add(new Order(id, song_id, user_id, price, quantity, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }
}
