import java.util.List;

public class Menu {
    public static Integer drawMainMenu() {
        System.out.println("\n***********************************");
        System.out.println("Operatiuni: ");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        Integer optiune = Main.sc.nextInt();
        Main.sc.nextLine();
        return optiune;

    }

    public static void mainMenu() {
        while (true) {
            switch (drawMainMenu()) {
                case 1:
                    if (doLogin()) {
                        loginMenu();
                    }
                    break;
                case 2:
                    doRegister();
                    break;
                case 3:
                    System.out.println("Have a nice day!");
                    return;
                default:
                    System.out.println("Optiunea nu exista");
                    break;
            }
        }

    }

    private static void doRegister() {
        String readUserName = null;
        String readPassword = null;
        String readMail = null;

        System.out.println("Insert username = ");
        readUserName = Main.sc.nextLine();

        System.out.println("Insert password = ");
        readPassword = Main.sc.nextLine();

        System.out.println("Insert mail = ");
        readMail = Main.sc.nextLine();

        Main.mySQLHandler.insertUser(readUserName, readPassword, readMail);

    }

    private static void loginMenu() {
        while (true) {
            switch (drawLoginMenu()) {
                case 1:
                    break;
                case 2:
                    showAllCategories();
                    break;
                case 3:
                    System.out.println("Have a nice day!");
                    Main.prompt = "";
                    return;
                default:
                    System.out.println("Optiunea nu exista");
                    break;
            }
        }

    }

    private static void showAllCategories() {
        List<Category> list = Main.mySQLHandler.getAllCategories();
        if (list != null && list.size() > 0) {
            for (Category cat : list) {
                cat.toString();
            }
        }
    }

    private static Integer drawLoginMenu() {
        System.out.println("1. Enter category");
        System.out.println("2. Show all categories");
        System.out.println("3. Return");
        System.out.print(Main.prompt + ">");
        Integer option = Main.sc.nextInt();
        Main.sc.nextLine();
        return option;
    }

    private static boolean doLogin() {
        String readUserName = null;
        String readPassword = null;

        System.out.println("Insert username = ");
        readUserName = Main.sc.nextLine();

        System.out.println("Insert password = ");
        readPassword = Main.sc.nextLine();

        User user = Main.mySQLHandler.getUserByUsername(readUserName);

        if (user != null && user.isThisPasswordMine(readPassword)) {
            Main.prompt = readUserName;
            return true;
        } else {
            System.out.println("invalid login ! ! !");
            return false;
        }
    }


}
