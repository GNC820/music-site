package music.site;

import DAO.UserDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

public class DeleteUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // retrieve data from the jsp page
        int id = Integer.parseInt(request.getParameter("id"));

        // create the userDao object
        UserDao userDao = new UserDao(ConnectionManager.getConnection());

        try {
            // delete the user
            boolean rowDeleted = userDao.deleteUser(id);
            
            if (rowDeleted) {
                // if user is deleted get the updated users list
                List<User> listUser = userDao.selectAllUsers();
                request.setAttribute("listUser", listUser);

                // re render the updated users list
                response.sendRedirect(request.getContextPath() + "/ViewAllUsers");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
