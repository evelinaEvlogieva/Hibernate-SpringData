import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        final String connectionURL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";

        Connection connection = DriverManager.getConnection(connectionURL,
                                        "root", "evelina329");

        Engine engine = new Engine(connection);
        engine.run();
    }
}
