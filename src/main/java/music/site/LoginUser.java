package music.site;

import DAO.UserDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import utils.PasswordHashing;

public class LoginUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = PasswordHashing.encrypt(request.getParameter("password"), "Test");

            UserDao userDao = new UserDao(ConnectionManager.getConnection());
            User user = userDao.login(username, password);
            System.out.println("avem user"+ user);
            HttpSession session = null;
            if (user != null) {
                 System.out.println("avem user"+ user.getIsAdmin());
                session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", user.getIsAdmin());
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewusers.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

}
