package music.site;

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
import model.User;

public class EditUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // retrieve the data from the jsp page
        String id = request.getParameter("id");

        // get useDao instance
        UserDao userDao = new UserDao(ConnectionManager.getConnection());

        // get current user by his id
        User user = userDao.getUserById(Integer.parseInt(id));

        // sent the user to the user edit page
        RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
        request.setAttribute("user", user);
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
