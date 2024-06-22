package pl.adcom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PressureServiceImpl implements PressureService {

    private Connection connection;

    @Override
    public void addPressure(Pressure pressure) {
        connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO PRESSURES (id, upperPressure, lowerPressure, pulse, startedMeassure, userId) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, pressure.getId());
            stmt.setInt(2, pressure.getUpperPressure());
            stmt.setInt(3, pressure.getLowerPressure());
            stmt.setInt(4, pressure.getPulse());
            stmt.setObject(5, pressure.getStartedMeassure());
            stmt.setLong(6, pressure.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pressure getPressureById(long id) {
        connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PRESSURES WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pressure(
                        rs.getLong("id"),
                        rs.getInt("upperPressure"),
                        rs.getInt("lowerPressure"),
                        rs.getInt("pulse"),
                        rs.getObject("startedMeassure", LocalTime.class),
                        rs.getLong("userId")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Pressure> getAllPressuresForUser(long userId) {
        connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PRESSURES WHERE userId = ?";
        List<Pressure> pressures = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pressures.add(new Pressure(
                        rs.getLong("id"),
                        rs.getInt("upperPressure"),
                        rs.getInt("lowerPressure"),
                        rs.getInt("pulse"),
                        rs.getObject("startedMeassure", LocalTime.class),
                        rs.getLong("userId")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pressures;
    }

    @Override
    public void updatePressure(Pressure pressure) {
        connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE PRESSURES SET upperPressure = ?, lowerPressure = ?, pulse = ?, startedMeassure = ?, userId = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pressure.getUpperPressure());
            stmt.setInt(2, pressure.getLowerPressure());
            stmt.setInt(3, pressure.getPulse());
            stmt.setObject(4, pressure.getStartedMeassure());
            stmt.setLong(5, pressure.getUserId());
            stmt.setLong(6, pressure.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePressure(long id) {
        connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM PRESSURES WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
