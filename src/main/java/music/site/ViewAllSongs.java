package music.site;

import DAO.SongDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Song;

public class ViewAllSongs extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // create the songDao instance
        SongDao songDao = new SongDao(ConnectionManager.getConnection());
        
        // get all songs from the database
        List<Song> allSongs = songDao.selectAllSongs();
        
        // redirect the user with the songs page
        RequestDispatcher dispatcher = request.getRequestDispatcher("songs.jsp");
        // add the songs to the songs.jsp page
        request.setAttribute("allSongs", allSongs);
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
