package music.site;

import DAO.SongDao;
import DAO.UserDao;
import connection.ConnectionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Song;
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

            HttpSession session = null;
            if (user != null && user.getIsAdmin().equals("Yes")) {;
                session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", user.getIsAdmin());
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewusers.jsp");
                dispatcher.forward(request, response);
            } else if (user != null && user.getIsAdmin().equals("No")) {
                SongDao songDao = new SongDao(ConnectionManager.getConnection());
                
                List<Song> songs = new ArrayList<Song>() {
                    {
                        add(new Song("Out of Time", "The Weeknd", "£22 ", "A respite from the fast-paced front half of the Weeknd’s Dawn FM, “Out of Time” slows things down and looks inward, inspired by Japanese city pop from the early Eighties and sampling Tomoko Aran’s “Midnight Pretenders.”", 0));
                        add(new Song("Vegas", "Doja Cat", "£28", "Leiber and Stoller wrote it, Big Mama Thornton sang it, Elvis covered it — and who better than Doja Cat to rewrite the whole tangled history of American pop music by remaking “Hound Dog” in her own uproariously raw style?", 0));
                        add(new Song("About Damn Time", "Lizzo", "£15", "“It’s bad bitch o’clock,” Lizzo declares on the first single since her 2019 major-label debut, Cuz I Love You, turned the Minneapolis singer, rapper, and occasional flautist into a pop sensation.", 0));
                        add(new Song("Cursed", "King Princess", "£33", "With lines like “Did you stop smoking weed/Or trying to please your dad?” the latest King Princess single is a cheeky pop gem — the kind that can shine long after you’ve exhausted your “play” button.Dog for one messy web of Nineties nostalgia", 0));
                    }
                };
                for (Song song : songs) {
                    songDao.saveSong(song);
                }
                
                List<Song> allSongs = songDao.selectAllSongs();
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("songs.jsp");
                request.setAttribute("allSongs", allSongs);
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
