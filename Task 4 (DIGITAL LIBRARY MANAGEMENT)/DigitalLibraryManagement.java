import java.util.*;
class User {
    private String username;
    private String password;
    private boolean isAdmin;


    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}


class Library {
    private List<User> users;
    private List<String> books;


    public Library() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
    }


    public void addUser(User user) {
        users.add(user);
    }


    public void addBook(String bookTitle) {
        books.add(bookTitle);
    }


    public void displayAllBooks() {
        System.out.println("Books in the Library:");
        for (String book : books) {
            System.out.println(book);
        }
    }


    public void displayMainMenu() {
        System.out.println("******************** Welcome to Digital Library ********************");
        System.out.println("********************     ~By Harish Gautham     ********************");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. User Sign Up");
        System.out.println("0. Exit");
        System.out.println("********************************************************************");
    }

    public void displayAdminMenu() {
        System.out.println("******************** Admin Menu ********************");
        System.out.println("1. Add Book");
        System.out.println("2. Display All Books");
        System.out.println("0. Logout");
        System.out.println("***************************************************");
    }


    public void displayUserMenu() {
        System.out.println("******************** User Menu ********************");
        System.out.println("1. View Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("0. Logout");
        System.out.println("***************************************************");
    }


    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}


public class DigitalLibraryManagement {

    public static void main(String[] args) {
        Library library = new Library();


        library.addUser(new User("admin", "admin123", true));
        library.addUser(new User("user1", "password1", false));
        library.addUser(new User("user2", "password2", false));

        library.addBook("Java Programming");
        library.addBook("Python Programming");
        library.addBook("Data Structures and Algorithms");

        Scanner scanner = new Scanner(System.in);
        int choice;
        @SuppressWarnings("unused")
        User currentUser = null;

        do {
            library.displayMainMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:

                    System.out.println("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.println("Enter admin password: ");
                    String adminPassword = scanner.nextLine();

                    User adminUser = library.authenticateUser(adminUsername, adminPassword);
                    if (adminUser != null && adminUser.isAdmin()) {
                        currentUser = adminUser;
                        System.out.println("Admin login successful.");
                        int adminChoice;
                        do {
                            library.displayAdminMenu();
                            System.out.print("Enter your choice: ");
                            adminChoice = scanner.nextInt();
                            scanner.nextLine(); 
                            switch (adminChoice) {
                                case 1:
                                    System.out.println("Enter book title to add: ");
                                    String bookTitle = scanner.nextLine();
                                    library.addBook(bookTitle);
                                    System.out.println("Book added successfully.");
                                    break;
                                case 2:
                                    library.displayAllBooks();
                                    break;
                                case 0:
                                    System.out.println("Logging out...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        } while (adminChoice != 0);
                    } else {
                        System.out.println("Admin login failed. Invalid username or password.");
                    }
                    break;
                case 2:
     
                    System.out.println("Enter user username: ");
                    String userUsername = scanner.nextLine();
                    System.out.println("Enter user password: ");
                    String userPassword = scanner.nextLine();

                    User user = library.authenticateUser(userUsername, userPassword);
                    if (user != null && !user.isAdmin()) {
                        currentUser = user;
                        System.out.println("User login successful.");
                        int userChoice;
                        do {
                            library.displayUserMenu();
                            System.out.print("Enter your choice: ");
                            userChoice = scanner.nextInt();
                            scanner.nextLine(); 

                            switch (userChoice) {
                                case 1:
                                    library.displayAllBooks();
                                    break;
                                case 2:
                                    System.out.println("Enter book title to borrow: ");
                                    
                                    break;
                                case 3:
                                    System.out.println("Enter book title to return: ");
                                    
                                    break;
                                case 0:
                                    System.out.println("Logging out...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        } while (userChoice != 0);
                    } else {
                        System.out.println("User login failed. Invalid username or password.");
                    }
                    break;
                case 3:

                    System.out.println("User sign up feature is not implemented in this example.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}
