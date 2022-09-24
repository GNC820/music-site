package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Song;

public class OrderDao {

    Connection con;

    public OrderDao(Connection con) {
        this.con = con;
    }

    public boolean saveOrder(Order order) {
        boolean set = false;
        try {
            String query = "insert into music.order(songId, userId, price, quantity, total) values(?,?,?,?,?)";

            PreparedStatement pt = this.con.prepareStatement(query);

            pt.setInt(1, order.getSongId());
            pt.setInt(2, order.getUserId());
            pt.setString(3, order.getPrice());
            pt.setInt(4, order.getQuantity());
            pt.setInt(5, order.getTotal());
            pt.executeUpdate();
            set = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public List<Order> selectAllOrdersByUserId(Integer userId) {
        String query = "SELECT * FROM music.order where userId = ? ;";
        List<Order> orders = new ArrayList<Order>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer song_id = rs.getInt("songId");
                Integer user_id = rs.getInt("userId");
                String price = rs.getString("price");
                Integer quantity = rs.getInt("quantity");
                Integer total = rs.getInt("total");
                orders.add(new Order(id, song_id, user_id, price, quantity, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }
}
