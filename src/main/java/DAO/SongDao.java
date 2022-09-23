package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;
import model.User;

public class SongDao {

    Connection con;

    public SongDao(Connection con) {
        this.con = con;
    }

    public boolean saveSong(Song song) {
        boolean set = false;
        try {
            String query = "insert into song(title,artist, price, description, salesAmount) values(?,?,?,?,?)";

            PreparedStatement pt = this.con.prepareStatement(query);

            ResultSet rs = checkTitle(song.getTitle());
            System.out.println(!rs.isBeforeFirst());
            if (!rs.isBeforeFirst()) {
                pt.setString(1, song.getTitle());
                pt.setString(2, song.getArtist());
                pt.setString(3, song.getPrice());
                pt.setString(4, song.getDescription());
                pt.setInt(5, song.getSalesAmount());
                pt.executeUpdate();
                set = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public List<Song> selectAllSongs() {
        String query = "select * from song";
        List<Song> songs = new ArrayList<Song>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String price = rs.getString("price");
                String description = rs.getString("description");
                Integer salesAmount = rs.getInt("salesAmount");

                songs.add(new Song(id, title, artist, price, description, salesAmount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return songs;
    }

    public Song getSongById(int id) {
        Song song = null;
        String query = "select * from song where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer song_id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String price = rs.getString("price");
                String description = rs.getString("description");
                Integer salesAmount = rs.getInt("salesAmount");
                song = new Song(song_id, title, artist, price, description, salesAmount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return song;
    }

    public boolean updateSong(User user) throws SQLException {
        boolean rowUpdated;

        String query = "update user set username = ?,email= ?, isAdmin = ? where id = ?;";
        PreparedStatement preparedStatement;

        preparedStatement = this.con.prepareStatement(query);

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getIsAdmin());
        preparedStatement.setInt(4, user.getId());

        rowUpdated = preparedStatement.executeUpdate() > 0;

        return rowUpdated;
    }

    public List<Song> search(String inputString) {
        String query = "select * from song WHERE title LIKE CONCAT( '%',?,'%') or artist LIKE CONCAT( '%',?,'%')";
        List<Song> songs = new ArrayList<Song>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1,inputString);
            preparedStatement.setString(2, inputString);
        
            ResultSet rs = preparedStatement.executeQuery();
            

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String price = rs.getString("price");
                String description = rs.getString("description");
                Integer salesAmount = rs.getInt("salesAmount");

                songs.add(new Song(id, title, artist, price, description, salesAmount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return songs;
    }

    public ResultSet checkTitle(String title) {
        String query = "select * from song where title = ?";
        PreparedStatement ps;
        ResultSet rs = null;

        try {
            ps = this.con.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SongDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;//return result rs here
    }
}
