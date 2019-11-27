import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {
    public DatabaseManager () {
        Connection connection = null;
        try {
            connection = getConnection();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Michael/Google Drive/University/Year 3/Fall Term 2019/COIS 3400/Labs/Lab 3/MusicLibrary.db");
            if (connection == null)
                throw new Exception("Connection to the database is NULL", null);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Problem Encountered!");
            e.printStackTrace();
        }

        return connection;
    }

    public ArrayList findArtistSongsFeaturedIn (int creatorID) throws Exception { //Case 1
        ArrayList<String> resultsList = new ArrayList<String>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery("" +
                "SELECT Name\n" +
                "FROM AudioFile\n" +
                "INNER JOIN Song ON AudioFile.Fk_AudioFileID_2_SongID = Song.Id INNER JOIN Features F on\n" +
                "Song.Id = F.FK_FeatureID_2_SongID\n" +
                "WHERE F.FK_FeatureID_2_CreatorID = "+ creatorID + ";");

        while (results.next()) {
            resultsList.add(results.getString(1));
        }

        statement.close();
        connection.close();

        return resultsList;
    }

    public ArrayList findAlbumsByGroup (int groupID) throws Exception{ //Case 2
        ArrayList<String> resultsList = new ArrayList<String>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery("" +
                "SELECT NAME\n" +
                "FROM Album\n" +
                "WHERE Fk_AlbumID_2_GroupID = " + groupID + ";");

        while (results.next()) {
            resultsList.add(results.getString(1));
        }

        statement.close();
        connection.close();

        return resultsList;
    }

    public void deleteSong (int songID) throws Exception { //Case 3
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate(
                "DELETE FROM Song WHERE StudentId = "+ songID);
    }

    public void createArtist (String name, int age, String biography, String DOB, int groupAffiliation) throws Exception { //Case 4
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        if (groupAffiliation > 0) { //Artist was given a group affilliation, no need to create new group for the artist

        } else {

        } //No affiliation, artist needs a group

        statement.close();
        connection.close();
    }

    public ArrayList findHighestRatedSongs () throws Exception { //Case 5
        ArrayList<String> resultsList = new ArrayList<String>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(
                "SELECT NAME\n" +
                "FROM AudioFile\n" +
                "WHERE Fk_AudioFileID_2_SongID NOT NULL AND Rating = (SELECT MAX(Rating) FROM\n" +
                "AudioFile)");

        while (results.next()) {
            resultsList.add(results.getString(1));
        }

        statement.close();
        connection.close();

        return resultsList;
    }

    public ArrayList findHighestRatedBooks () throws Exception { //Case 6
        ArrayList<String> resultsList = new ArrayList<String>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(
                "SELECT NAME\n" +
                "FROM AudioFile\n" +
                "WHERE FK_AudioFileID_2_BookID NOT NULL AND Rating = (SELECT MAX(Rating) FROM\n" +
                "AudioFile)");

        while (results.next()) {
            resultsList.add(results.getString(1));
        }

        statement.close();
        connection.close();

        return resultsList;
    }

    public ArrayList findSongsByGroup (int groupID) throws Exception { //Case 7
        ArrayList<String> resultsList = new ArrayList<String>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(
                "SELECT Name\n" +
                "FROM AudioFile\n" +
                "WHERE Fk_AudioFileID_2_SongID in (\n" +
                "SELECT Song.Id from Song\n" +
                "\n" +
                "Michael Nichol\n" +
                "SID: 0624004\n" +
                "WHERE Fk_SongID_2_AlbumID in (\n" +
                "SELECT Album.Id from Album\n" +
                "WHERE Fk_AlbumID_2_GroupID in (\n" +
                "SELECT \"Group\".Id from \"Group\"\n" +
                "WHERE \"Group\".Id = " + groupID + "\n" +
                ")\n" +
                ")\n" +
                ");");

        while (results.next()) {
            resultsList.add(results.getString(1));
        }

        statement.close();
        connection.close();

        return resultsList;
    }

    public ArrayList findAlbumsByGenre (String genre) throws Exception { //Case 8
        ArrayList<String> resultsList = new ArrayList<String>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(
                "SELECT *\n" +
                "FROM Album\n" +
                "WHERE Genre = '" + genre +"';");

        while (results.next()) {
            resultsList.add(results.getString(1));
        }

        statement.close();
        connection.close();

        return resultsList;
    }

    public String findEarliestGroup () throws Exception { //Case 9
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(
                "SELECT MIN(DateFounded) as EarliestDate\n" +
                "FROM \"Group\"");

        statement.close();
        connection.close();

        return results.getString(1);
    }

    public String findLongestSong () throws Exception { //Case 10
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(
                "SELECT MAX(FileLength) as LatestDate\n" +
                "FROM AudioFile");

        statement.close();
        connection.close();

        return results.getString(1);
    }
}
