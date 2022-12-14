/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package music.site;

import DAO.UserDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
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

public class UpdateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // retrieve the data from the jsp page
            Integer id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String isAdmin = request.getParameter("isAdmin");
            String email = request.getParameter("email");

            // create user instance
            User user = new User(id, username, email, isAdmin);

            // create userDao instance
            UserDao userDao = new UserDao(ConnectionManager.getConnection());
            
            // get the result of the updateUser function
            boolean isRowUpdated = userDao.updateUser(user);
            
            // if user was succesfully updated
            if (isRowUpdated) {
                // re render the user list with the updated user
                List<User> listUser = userDao.selectAllUsers();
                request.setAttribute("listUser", listUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewusers.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateUser.class.getName()).log(Level.SEVERE, null, ex);
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
