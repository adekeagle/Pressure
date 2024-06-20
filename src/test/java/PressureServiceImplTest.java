import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.adcom.Pressure;
import pl.adcom.PressureService;
import pl.adcom.PressureServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PressureServiceImplTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pressure", "root", "");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY, firstName VARCHAR(50), lastName VARCHAR(50), gender VARCHAR(1), email VARCHAR(100))");
        stmt.execute("CREATE TABLE IF NOT EXISTS pressure (id BIGINT PRIMARY KEY, upperPressure INT, lowerPressure INT, pulse INT, startedMeassure TIME, userId BIGINT, FOREIGN KEY (userId) REFERENCES user(id))");
        stmt.execute("INSERT INTO user (id, firstName, lastName, gender, email) VALUES (1, 'John', 'Doe', 'M', 'john.doe@example.com')");
    }

    @AfterEach
    public void tearDown() throws Exception {
        Statement stmt = connection.createStatement();
        stmt.execute("DROP TABLE pressure");
        stmt.execute("DROP TABLE user");
        connection.close();
    }

    @Test
    public void testAddPressure() {
        PressureService pressureService = new PressureServiceImpl();
        Pressure pressure = new Pressure(1, 120, 80, 60, LocalTime.of(8, 30), 1);

        pressureService.addPressure(pressure);

        // Verify the pressure was added
        try {
            Statement stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM pressure WHERE id = 1");
            assertTrue(rs.next());
            assertEquals(120, rs.getInt("upperPressure"));
            assertEquals(80, rs.getInt("lowerPressure"));
            assertEquals(60, rs.getInt("pulse"));
            assertEquals("08:30:00", rs.getTime("startedMeassure").toString());
            assertEquals(1, rs.getLong("userId"));
        } catch (SQLException e) {
            fail("Failed to verify pressure in database");
        }
    }
}
