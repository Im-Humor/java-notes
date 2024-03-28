import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    public static void createNote(String db_path, String noteName) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
                Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);
            statement.executeUpdate("insert into note_list (note_name) values ('"+noteName+"')");
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }
    public static void listNoteNames(String db_path) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
                Statement statement = connection.createStatement();
        )
        {
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from note_list");
            while(rs.next()) {
                System.out.println("name = " + rs.getString("note_name") + " id: " + rs.getString("id"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void deleteNote(String db_path, String noteId) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
                Statement statement = connection.createStatement();
        )
        {
            statement.setQueryTimeout(30);
            statement.executeUpdate("DELETE FROM note_contents WHERE parent_id= " + noteId);
            statement.executeUpdate("DELETE FROM note_list WHERE id= " + noteId);
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void viewNoteContents(String db_path, String noteId) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
                Statement statement = connection.createStatement();
        )
        {
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT note_list.id, note_contents.id AS note_contents_id, note_contents.subnote_content " +
                    "FROM note_list INNER JOIN note_contents on note_list.id = note_contents.parent_id " +
                    "WHERE note_list.id = " + noteId);
            while(rs.next()) {
                System.out.println("id: " + rs.getString("note_contents_id") + ": " + rs.getString("subnote_content"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void createSubnote(String db_path, String noteId, String textContent) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
                Statement statement = connection.createStatement();
        )
        {
            statement.setQueryTimeout(30);
            statement.executeUpdate("INSERT INTO note_contents (parent_id, subnote_content) " +
                    "VALUES (" + noteId + ", '" + textContent + "')");
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void deleteSubnote(String db_path, String noteId) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
                Statement statement = connection.createStatement();
        )
        {
            statement.setQueryTimeout(30);
            statement.executeUpdate("DELETE FROM note_contents WHERE id=" + noteId);
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }

}
