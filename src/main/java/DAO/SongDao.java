package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;

public class SongDao {

    Connection con;

    public SongDao(Connection con) {
        this.con = con;
    }

    public boolean saveSong(Song song) {
        boolean set = false;
        try {
            // create sql query for inserting a song record
            String query = "insert into song(title,artist, price, description, salesAmount) values(?,?,?,?,?)";

            PreparedStatement pt = this.con.prepareStatement(query);
            // check if a title already exists
            ResultSet rs = checkTitle(song.getTitle());
            // if not exists add the song into the database
            if (!rs.isBeforeFirst()) {
                // // set the ? parameters with the current song values
                pt.setString(1, song.getTitle());
                pt.setString(2, song.getArtist());
                pt.setString(3, song.getPrice());
                pt.setString(4, song.getDescription());
                pt.setInt(5, song.getSalesAmount());

                // persist the song into the database
                pt.executeUpdate();
                set = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public List<Song> selectAllSongs() {
        // create sql query for selecting all songs from the database
        String query = "select * from song";
        List<Song> songs = new ArrayList<Song>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);

            // get the result set( mapping the database information into a Java object)
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object.
            while (rs.next()) {
                // here is the actual mapping
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String price = rs.getString("price");
                String description = rs.getString("description");
                Integer salesAmount = rs.getInt("salesAmount");

                // add each song to the songs list
                songs.add(new Song(id, title, artist, price, description, salesAmount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return songs;
    }

    public Song getSongById(int id) {
        Song song = null;
        // create sql query for selecting a song based on id from the database
        String query = "select * from song where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.con.prepareStatement(query);

            // set the ? parameters with the current song values
            preparedStatement.setInt(1, id);

            // get the result set( mapping the database information into a Java object)
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object.
            while (rs.next()) {
                // here is the actual mapping
                Integer song_id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String price = rs.getString("price");
                String description = rs.getString("description");
                Integer salesAmount = rs.getInt("salesAmount");

                // create the Song object from the dabatabse info
                song = new Song(song_id, title, artist, price, description, salesAmount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return song;
    }

    public int updateSongSales(int songId, int amount) throws SQLException {
        int rowUpdated;
        // get existing sale and update the amount
        Song song = getSongById(songId);
        // get the new amount based on existing amount
        Integer newAmount = song.getSalesAmount() + amount;

        // create sql query for updating a song based on id
        String query = "update song set salesAmount = ? where id = ?;";
        PreparedStatement preparedStatement;

        preparedStatement = this.con.prepareStatement(query);

        // set the ? parameters with the current id value
        preparedStatement.setInt(1, newAmount);
        preparedStatement.setInt(2, songId);

        // if preparedStatement.executeUpdate() > 0 then the record was updated
        rowUpdated = preparedStatement.executeUpdate();

        return rowUpdated;
    }

    public List<Song> search(String inputString) {
        // create sql query for searching some songs based on artist and title
        String query = "select * from song WHERE title LIKE CONCAT( '%',?,'%') or artist LIKE CONCAT( '%',?,'%')";
        List<Song> songs = new ArrayList<Song>();
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);

            // set the ? parameters with the current inputString value
            preparedStatement.setString(1, inputString);
            preparedStatement.setString(2, inputString);

            // get the result set( mapping the database information into a Java object)
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object.
            while (rs.next()) {
                // here is the actual mapping
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String price = rs.getString("price");
                String description = rs.getString("description");
                Integer salesAmount = rs.getInt("salesAmount");

                // add each song to the songs list
                songs.add(new Song(id, title, artist, price, description, salesAmount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return songs;
    }

    public ResultSet checkTitle(String title) {
        // check if a record having the given title already exists in the database
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

        return rs;
    }
}
