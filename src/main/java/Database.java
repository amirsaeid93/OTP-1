import java.sql.*;
import javafx.scene.control.Label;

public class Database {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/javafx_mariadb?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static void saveTemperature(double inputValue, String fromUnit,
                                       double convertedValue, String toUnit,
                                       Label statusLabel) {
        String sql = "INSERT INTO temperature_log (input_value, from_unit, converted_value, to_unit) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, inputValue);
            stmt.setString(2, fromUnit);
            stmt.setDouble(3, convertedValue);
            stmt.setString(4, toUnit);
            stmt.executeUpdate();
            statusLabel.setText("Saved to database!");

        } catch (SQLException e) {
            statusLabel.setText("DB Error: " + e.getMessage());
        }
    }
}