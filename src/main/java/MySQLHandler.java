import java.sql.*;
import java.util.List;

public class MySQLHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/forum";
    private static final String USER = "arun";
    private static final String PASSWORD = "password";

    private Connection connection;

    MySQLHandler() throws SQLException {
        System.out.println("S-a apelat conexiunea");
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public User getUserByUsername(String usernameP) {
        User userFromDB = null;
        String sql = "SELECT * FROM users WHERE username = " + "\"" + usernameP + "\"";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int userIdFromDB = resultSet.getInt("id");
                String usernameFromDB = resultSet.getString("username");
                String passwordFromDB = resultSet.getString("password");
                String mailFromDB = resultSet.getString("email");
                userFromDB = new User(userIdFromDB, usernameFromDB, passwordFromDB, mailFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFromDB;
    }

    public User getUserByid(int id) {
        User userFromDB = null;
        String sql = "SELECT * FROM users WHERE id = " + id;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int userIdFromDB = resultSet.getInt("id");
                String usernameFromDB = resultSet.getString("username");
                String passwordFromDB = resultSet.getString("password");
                String mailFromDB = resultSet.getString("email");
                userFromDB = new User(userIdFromDB, usernameFromDB, passwordFromDB, mailFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFromDB;
    }

    public boolean insertUser(String username, String password, String mail) {

        String query = " insert into users (username, password, email)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);
            preparedStmt.setString(3, mail);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Category> lista = null;
            Integer idFromDB = null;
            Integer idUserFromDB = null;
            String subjectFromDB = null;

            if (resultSet.next()) {
                idFromDB = resultSet.getInt("id");
                idUserFromDB = resultSet.getInt("id_user");
                subjectFromDB = resultSet.getString("subject");
                lista.add(new Category(idFromDB, getUserByid(idUserFromDB), subjectFromDB));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
