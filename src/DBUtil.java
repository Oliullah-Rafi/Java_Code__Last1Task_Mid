import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT name, email,password FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"));
                    users.add(user);
                }
            }
        }
        return users;
    }

    public static void createUser(User user) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getFullName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
            }
        }
    }
}
