package music.site;

import DAO.OrderDao;
import DAO.UserDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.Song;

public class OrderHistory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get current session
        HttpSession session = request.getSession(true);
        // get current user id from the session
        Integer userId = (Integer) session.getAttribute("userId");

        // create orderDao instance
        OrderDao orderDao = new OrderDao(ConnectionManager.getConnection());
        // get all orders by user id
        List<Order> allOrders = orderDao.selectAllOrdersByUserId(userId);
        
        //redirect to order history page
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderhistory.jsp");
        // add orders to the orderhistory page
        request.setAttribute("allOrders", allOrders);
        dispatcher.forward(request, response);
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
