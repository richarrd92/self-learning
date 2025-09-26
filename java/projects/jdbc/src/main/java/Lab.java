

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    public void createSong(Song song)  {
        //write jdbc code here
        
        // statement for insertion - preparedStatement for security
        String sqlQueryString = "INSERT INTO songs (title, artist) VALUES (?, ?)";

        // automatically closes connection without use of finally
        try(
            // set up connection using util file
            Connection connection = ConnectionUtil.getConnection();
            // set up preparedStatement 
            PreparedStatement statement = connection.prepareStatement(sqlQueryString);
        ){
            statement.setString(1, song.gettitle());
            statement.setString(2, song.getArtist());


            statement.executeUpdate(); // actually inserts 

        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        String sqlQueryString = "SELECT * FROM songs"; // can also specify each column

        try (
            Connection connection = ConnectionUtil.getConnection(); // get connection
            PreparedStatement statement = connection.prepareStatement(sqlQueryString); // get the query string
            ResultSet result = statement.executeQuery(); // pointer to loop through result of query
        ) { 
            // using result pointer
            while(result.next()){
                int id = result.getInt("id");
                String title = result.getString("title");
                String artist = result.getString("artist");

                // create a new song object
                Song song = new Song(id, title, artist);
                songs.add(song);

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return songs;
    }
}
