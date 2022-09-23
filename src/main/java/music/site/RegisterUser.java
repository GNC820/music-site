package music.site;

import DAO.UserDao;
import connection.ConnectionManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import utils.PasswordHashing;

public class RegisterUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = PasswordHashing.encrypt(request.getParameter("password"), "Test");
            String isAdmin = request.getParameter("isAdmin");
            String email = request.getParameter("email");

            User user = new User(username, password, isAdmin, email);

            boolean status = false;
            UserDao userDao = new UserDao(ConnectionManager.getConnection());
            status = userDao.saveUser(user);

            if (status) {
                // Step: Redirect to a View
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
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
