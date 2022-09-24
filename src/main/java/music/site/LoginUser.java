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
            // retrieve the data from the jsp page
            String username = request.getParameter("username");
            String password = PasswordHashing.encrypt(request.getParameter("password"), "Test");

            // get the userDao instance
            UserDao userDao = new UserDao(ConnectionManager.getConnection());

            // login user with his credentials
            User user = userDao.login(username, password);

            // create HTTP session
            HttpSession session = null;
            session = request.getSession();

            // if user exists and is admin
            if (user != null && user.getIsAdmin().equals("Yes")) {
                // set sesstion values
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", user.getIsAdmin());
                session.setAttribute("userId", user.getId());

                // get the list of the user
                List<User> listUser = userDao.selectAllUsers();
                request.setAttribute("listUser", listUser);

                // add the list to the viewuser page and redirect to that page
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewusers.jsp");
                dispatcher.forward(request, response);

                // if user exists and is not admin
            } else if (user != null && user.getIsAdmin().equals("No")) {
                  // set sesstion values
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", user.getIsAdmin());
                session.setAttribute("userId", user.getId());
                
                // create the songDao instance
                SongDao songDao = new SongDao(ConnectionManager.getConnection());
                
                // pre-populate the song table if no songs are available
                List<Song> songs = new ArrayList<Song>() {
                    {
                        add(new Song("Out of Time", "The Weeknd", "£22 ", "A respite from the fast-paced front half of the Weeknd’s Dawn FM, “Out of Time” slows things down and looks inward, inspired by Japanese city pop from the early Eighties and sampling Tomoko Aran’s “Midnight Pretenders.”", 0));
                        add(new Song("Vegas", "Doja Cat", "£28", "Leiber and Stoller wrote it, Big Mama Thornton sang it, Elvis covered it — and who better than Doja Cat to rewrite the whole tangled history of American pop music by remaking “Hound Dog” in her own uproariously raw style?", 0));
                        add(new Song("That’s What I Want", "Lil Nas X", "£50", "Lil Nas X was so ubiquitous in 2021 that the word “underrated” might seem not to apply in any fashion… but it did once you got past his attention-getting singles to the full album", 0));
                        add(new Song("To Be Loved", "Adele", "£45", "Nothing could be duller or more predictable than espousing an argument that Adele is one of the great singers of our lifetime. But when you’re listening to her deliver a vocal turn like the one on “To Be Loved,”.", 0));
                        add(new Song("Valentine", "Snail Mail", "£8", "Lindsey Jordan, the artist who calls herself Snail Mail, has put quite a pensive “Valentine’s” Day card in the post with this plea to a past/potential lover. Could it possibly fall on deaf ears, with a hook this fantastic? ", 0));
                        add(new Song("Come to Life", "Kanye West", "£36", "If you wanted to have everything that ‘s complicated, self-contradictory and fascinating about Kanye West (now just Ye) boiled down into one song, it’d probably have to be “Come to Life.” ", 0));
                        add(new Song("About Damn Time", "Lizzo", "£15", "“It’s bad bitch o’clock,” Lizzo declares on the first single since her 2019 major-label debut, Cuz I Love You, turned the Minneapolis singer, rapper, and occasional flautist into a pop sensation.", 0));
                        add(new Song("Cursed", "King Princess", "£33", "With lines like “Did you stop smoking weed/Or trying to please your dad?” the latest King Princess single is a cheeky pop gem — the kind that can shine long after you’ve exhausted your “play” button.Dog for one messy web of Nineties.", 0));
                    }
                };
                // persist each song into the database
                for (Song song : songs) {
                    songDao.saveSong(song);
                }
                
                // retrivere all the songs
                List<Song> allSongs = songDao.selectAllSongs();
                
                // redierect the user to the songs page
                RequestDispatcher dispatcher = request.getRequestDispatcher("songs.jsp");
                // add songs data to the songs.jsp page
                request.setAttribute("allSongs", allSongs);
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
