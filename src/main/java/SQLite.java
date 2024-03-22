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
                System.out.println("name = " + rs.getString("note_name"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
