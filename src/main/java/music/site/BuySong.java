/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package music.site;

import DAO.OrderDao;
import DAO.SongDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;

public class BuySong extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // retrieve the data from the jsp page
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            String price = request.getParameter("price").replaceAll("[^0-9]", "");;
            Integer song_id = Integer.parseInt(request.getParameter("id"));

            // get the current session where user is set and retrieve his id
            HttpSession session = request.getSession(true);
            Integer userId = (Integer) session.getAttribute("userId");

            // update the sales with the current quantity
            SongDao songDao = new SongDao(ConnectionManager.getConnection());
            int rowsUpdated = songDao.updateSongSales(song_id, quantity);

            // add the current order to the order history 
            OrderDao orderDao = new OrderDao(ConnectionManager.getConnection());
            Integer total = quantity * Integer.parseInt(price);
            Order order =  new Order(song_id, userId, price, quantity, total);
            orderDao.saveOrder(order);
            
            // re render the songs with the updated values
            response.sendRedirect(request.getContextPath() + "/ViewAllSongs");
        } catch (SQLException ex) {
            Logger.getLogger(BuySong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
