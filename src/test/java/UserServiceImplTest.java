import org.junit.jupiter.api.*;
import pl.adcom.Gender;
import pl.adcom.User;
import pl.adcom.UserService;
import pl.adcom.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pressure", "root", "");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY, firstName VARCHAR(50), lastName VARCHAR(50), gender VARCHAR(1), email VARCHAR(100))");
    }

    @AfterEach
    public void tearDown() throws Exception {
        Statement stmt = connection.createStatement();
        stmt.execute("DROP TABLE user");
        connection.close();
    }

    @Test
    public void testAddUser() {
        UserService userService = new UserServiceImpl();
        User user = new User(1, "John", "Doe", Gender.M, "john.doe@example.com");

        userService.addUser(user);

        // Verify the user was added
        try {
            Statement stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM user WHERE id = 1");
            assertTrue(rs.next());
            assertEquals("John", rs.getString("firstName"));
            assertEquals("Doe", rs.getString("lastName"));
            assertEquals("M", rs.getString("gender"));
            assertEquals("john.doe@example.com", rs.getString("email"));
        } catch (SQLException e) {
            fail("Failed to verify user in database");
        }
    }
}
