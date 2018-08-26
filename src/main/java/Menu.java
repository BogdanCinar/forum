import java.util.List;

public class Menu {

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
                    System.out.println("The option does not exist !!!");
                    break;
            }
        }
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
            setPrompt(user);
            Main.mySQLHandler.setUserLogged(user);
            return true;
        } else {
            System.out.println("invalid login ! ! !");
            return false;
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
            switch (drawCategoryMenu()) {
                case 1:
                    insertCategory();
                    break;
                case 2:
                    showAllCategories();
                    break;
                case 3:
                    if (enterCategory()) {
                        categoryMenu();
                    }
                    break;
                case 4:
                    System.out.println("Have a nice day!");
                    setPrompt();
                    return;
                default:
                    System.out.println("Optiunea nu exista");
                    break;
            }
        }
    }

    private static void categoryMenu() {
        while (true) {
            switch (drawTopicMenu()) {
                case 1:
                  //  insertCategory();
                    break;
                case 2:
                  //  showAllCategories();
                    break;
                case 3:
                    break;
                case 4:
                    setPrompt(MySQLHandler.userLogged);
                    return;
                default:
                    System.out.println("Optiunea nu exista");
                    break;
            }
        }
    }

    private static boolean enterCategory() {
        System.out.println("Enter category = ");
        Integer idCategory = Main.sc.nextInt();
        Main.sc.nextLine();

        Category category = Main.mySQLHandler.getCategoryById(idCategory);

        if (category != null) {
            MySQLHandler.setCurrentCategory(category);
            setPrompt(category);
            return true;
        } else {
            System.out.println("Invalid category ! ! !");
            return false;
        }

    }

    private static void insertCategory() {
        String subjectRead = null;

        System.out.println("Insert subject for the new category = ");
        subjectRead = Main.sc.nextLine();

        Main.mySQLHandler.insertNewCategory(subjectRead);
    }

    private static void showAllCategories() {
        List<Category> list = Main.mySQLHandler.getAllCategories();
        if (list != null && list.size() > 0) {
            for (Category cat : list) {
                System.out.println(cat.toString());
            }
        }
    }

    private static Integer drawCategoryMenu() {
        System.out.println("1. Create category");
        System.out.println("2. Show all categories");
        System.out.println("3. Enter category");
        System.out.println("4. Return");
        System.out.print(Main.prompt);
        Integer option = Main.sc.nextInt();
        Main.sc.nextLine();
        return option;
    }

    private static Integer drawTopicMenu() {
        System.out.println("1. Create Topic");
        System.out.println("2. Show all topic");
        System.out.println("3. Enter Topic");
        System.out.println("4. Return");
        System.out.print(Main.prompt);
        Integer option = Main.sc.nextInt();
        Main.sc.nextLine();
        return option;
    }

    public static Integer drawMainMenu() {
        System.out.println("\n***********************************");
        System.out.println("Operations: ");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print(Main.prompt);
        Integer optiune = Main.sc.nextInt();
        Main.sc.nextLine();
        return optiune;

    }
    private static void setPrompt() {
        Main.prompt = ">";
    }

    private static void setPrompt(User userP) {
        Main.prompt = userP.getUsername() + ">";
    }

    private static void setPrompt(Category categoryP) {
        Main.prompt += categoryP.getSubject() + ">";
    }

    private static void setPrompt(Topic topicP) {
        Main.prompt += topicP.getSubject() + ">";
    }


}
