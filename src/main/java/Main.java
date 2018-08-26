import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner sc = null;
    static String prompt = ">";

    static MySQLHandler mySQLHandler = null;


    public static void main(String[] args) {
        sc = new Scanner(System.in);

        try {
            mySQLHandler = new MySQLHandler();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Menu.mainMenu();

    }


}

