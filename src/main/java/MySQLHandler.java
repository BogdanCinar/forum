import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/forum";
    private static final String USER = "arun";
    private static final String PASSWORD = "password";
    public static User userLogged;
    public static Category currentCategory;

    public static void setUserLogged(User userLogged) {
        MySQLHandler.userLogged = userLogged;
    }

    public static void setCurrentCategory(Category currentCategory) {
        MySQLHandler.currentCategory = currentCategory;
    }
    private Connection connection;

    MySQLHandler() throws SQLException {
        System.out.println("S-a apelat conexiunea");
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public User getUserByUsername(String usernameP) {
        User userFromDB = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usernameP);

            ResultSet resultSet = preparedStatement.executeQuery();

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
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

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

        String query = " INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

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
            List<Category> lista = new ArrayList<Category>();
            Integer idFromDB = null;
            Integer idUserFromDB = null;
            String subjectFromDB = null;

            while (resultSet.next()) {
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

    public void insertNewCategory(String subjectRead) {
        String query = " INSERT INTO categories (id_user, subject) VALUES (?, ?)";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, userLogged.getId());
            preparedStmt.setString(2, subjectRead);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(Integer idCategory) {
        Category categoryFromDB = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM categories WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCategory);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int categoryIdFromDB = resultSet.getInt("id");
                int userIdFromDB = resultSet.getInt("id_user");
                String subjectFromDB = resultSet.getString("subject");

                categoryFromDB = new Category(userIdFromDB, getUserByid(userIdFromDB), subjectFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryFromDB;
    }

}
