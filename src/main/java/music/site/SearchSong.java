package music.site;

import DAO.SongDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Song;

public class SearchSong extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String inputString = request.getParameter("search");
        SongDao songDao = new SongDao(ConnectionManager.getConnection());

        List<Song> songs = songDao.search(inputString);
        RequestDispatcher dispatcher = request.getRequestDispatcher("songs.jsp");
        request.setAttribute("allSongs", songs);
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
