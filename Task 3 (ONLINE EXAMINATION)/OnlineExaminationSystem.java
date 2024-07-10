import java.io.*;
import java.util.*;

// Class for MCQ question
class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String question;
    private List<String> options;
    private int correctAnswer;  // Index of correct answer in options

    public Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int selectedAnswer) {
        return selectedAnswer == correctAnswer;
    }
}

// Class to manage user details
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String name;
    private String email;
    private int score;

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.score = 0;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Method to update profile details
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Username: " + username + ", Email: " + email + ", Score: " + score;
    }
}

// Main Exam System
public class OnlineExaminationSystem {
    private static final String DATA_FILE = "users_scores.dat";
    private static List<User> users = new ArrayList<>(); // Store users (for demo purposes)
    private static List<Question> questions = new ArrayList<>(); // Store questions
    private static final int EXAM_DURATION_MINUTES = 20; // Exam duration in minutes

    public static void main(String[] args) {
        // Initialize sample users (demo purposes only)
        initializeUsers();

        // Sample questions creation (you can add more questions)
        createQuestions();

        // Display online examination banner
        displayBanner();

        // Simulate login and menu navigation
        simulateLoginAndMenu();
    }

    // Method to initialize sample users (demo purposes only)
    private static void initializeUsers() {
        // Load users and scores from file if exists, otherwise initialize with demo data
        File file = new File(DATA_FILE);
        if (file.exists()) {
            loadUsers();
        } else {
            // Demo users
            users.add(new User("harish", "123", "harish", "h@gmail.com"));
        }
    }

    // Method to load users and scores from file
    @SuppressWarnings("unchecked")
    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to save users and scores to file
    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to create questions
    private static void createQuestions() {
        // Add your 20 questions here as previously outlined
        // Questions about Cristiano Ronaldo
        questions.add(new Question("Cristiano Ronaldo began his senior career with which club?",
                Arrays.asList("Real Madrid", "Manchester United", "Juventus", "Barcelona"), 1));
        questions.add(new Question("How many Ballon d'Or awards has Cristiano Ronaldo won as of 2024?",
                Arrays.asList("4", "5", "6", "7"), 3));
        questions.add(new Question("Which club did Cristiano Ronaldo join after leaving Real Madrid in 2018?",
                Arrays.asList("Juventus", "Paris Saint-Germain", "Manchester City", "Bayern Munich"), 0));
        questions.add(new Question("In which year did Cristiano Ronaldo win his first UEFA Champions League title with Manchester United?",
                Arrays.asList("2005", "2008", "2010", "2012"), 1));
        questions.add(new Question("What is Cristiano Ronaldo's nationality?",
                Arrays.asList("Spanish", "Portuguese", "Brazilian", "Italian"), 1));

        // Questions about Neymar
        questions.add(new Question("Which club did Neymar join from Barcelona in a world-record transfer fee in 2017?",
                Arrays.asList("Real Madrid", "Juventus", "Paris Saint-Germain", "Manchester United"), 2));
        questions.add(new Question("Neymar won the Copa Libertadores with which Brazilian club before moving to Europe?",
                Arrays.asList("Flamengo", "Santos", "Corinthians", "Palmeiras"), 1));
        questions.add(new Question("What is Neymar's full name?",
                Arrays.asList("Neymar Silva Santos Jr.", "Neymar de Oliveira Santos Jr.", "Neymar da Silva Santos Jr.", "Neymar Santos da Silva Jr."), 2));
        questions.add(new Question("At which international tournament did Neymar win a gold medal with Brazil in 2016?",
                Arrays.asList("FIFA World Cup", "UEFA European Championship", "Copa America", "Olympic Games"), 3));
        questions.add(new Question("Neymar made his professional debut for which Brazilian club?",
                Arrays.asList("Santos", "Flamengo", "Corinthians", "Palmeiras"), 0));

        // Questions about UEFA Champions League (UCL)
        questions.add(new Question("Which club has won the most UEFA Champions League titles as of 2024?",
                Arrays.asList("Real Madrid", "Bayern Munich", "Barcelona", "Manchester United"), 0));
        questions.add(new Question("In which city is the UEFA Champions League final typically held?",
                Arrays.asList("Rome", "London", "Madrid", "Istanbul"), 3));
        questions.add(new Question("Which team won the inaugural UEFA Champions League title in the 1955-56 season?",
                Arrays.asList("Real Madrid", "AC Milan", "Liverpool", "Ajax"), 0));
        questions.add(new Question("Who is the all-time top scorer in UEFA Champions League history as of 2024?",
                Arrays.asList("Lionel Messi", "Cristiano Ronaldo", "Robert Lewandowski", "Raul Gonzalez"), 1));
        questions.add(new Question("Which country has hosted the most UEFA Champions League finals?",
                Arrays.asList("Spain", "Italy", "England", "Germany"), 0));

        // Additional Questions
        questions.add(new Question("Which player is nicknamed \"CR7\"?",
                Arrays.asList("Cristiano Ronaldo", "Lionel Messi", "Neymar", "Kylian Mbappe"), 0));
        questions.add(new Question("Which player famously transferred from Barcelona to PSG for €222 million in 2017?",
                Arrays.asList("Lionel Messi", "Cristiano Ronaldo", "Neymar", "Kylian Mbappe"), 2));
        questions.add(new Question("Who is the current president of UEFA?",
                Arrays.asList("Gianni Infantino", "Michel Platini", "Aleksander Čeferin", "Florentino Pérez"), 2));
        questions.add(new Question("Which club did Cristiano Ronaldo score his first hat-trick for in the UEFA Champions League?",
                Arrays.asList("Manchester United", "Real Madrid", "Juventus", "Sporting CP"), 0));
        questions.add(new Question("Who won the UEFA Champions League in the 2020-21 season?",
                Arrays.asList("Bayern Munich", "Chelsea", "Manchester City", "Real Madrid"), 1));
    }

    // Method to display online examination banner
    private static void displayBanner() {
        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*       WELCOME TO ONLINE EXAMINATION  *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
    }

    // Method to simulate login and menu navigation
    private static void simulateLoginAndMenu() {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;
        User currentUser = null;

        while (!loggedIn) {
            System.out.println("Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.print("Enter your choice (1-2): ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    currentUser = login(sc);
                    if (currentUser != null) {
                        loggedIn = true;
                        System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case 2:
                    signup(sc);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        // Once logged in, show main menu
        showMenu(currentUser, sc);

        // Save user data and scores to file before exiting
        saveUsers();
        System.out.println("Logged out. Thank you for using the online examination system!");
        sc.close();
    }

    // Method for user login
    private static User login(Scanner sc) {
        System.out.println("\nLogin:");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Login failed
    }

    // Method for user signup
    private static void signup(Scanner sc) {
        System.out.println("\nSignup:");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        // Check if username already exists
        boolean usernameExists = users.stream().anyMatch(user -> user.getUsername().equals(username));
        if (usernameExists) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            User newUser = new User(username, password, name, email);
            users.add(newUser);
            System.out.println("Signup successful! Please login to continue.");
        }
    }

    // Method to display main menu options after login
    private static void showMenu(User currentUser, Scanner sc) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Start Exam");
            System.out.println("2. Scoreboard");
            System.out.println("3. Update Profile");
            System.out.println("4. Logout");
            System.out.print("Enter your choice (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    startExam(currentUser, sc);
                    break;
                case 2:
                    displayScoreboard();
                    break;
                case 3:
                    updateProfile(currentUser, sc);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    // Method to start the exam session
    private static void startExam(User user, Scanner sc) {
        int totalQuestions = questions.size();
        int score = 0;

        System.out.println("\nStarting exam for user: " + user.getName());
        System.out.println("You have " + totalQuestions + " questions. Let's begin:\n Time Limit: 10mins\n");

        // Timer logic (not implemented here, conceptual outline)
        long startTime = System.currentTimeMillis();
        long endTime = startTime + EXAM_DURATION_MINUTES * 60 * 1000; // Convert minutes to milliseconds
        boolean examTimeUp = false;

        for (int i = 0; i < totalQuestions; i++) {
            if (System.currentTimeMillis() > endTime) {
                System.out.println("Time's up! Automatically submitting remaining questions.");
                examTimeUp = true;
                break;
            }

            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getQuestion());
            List<String> options = q.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            int chosenAnswer = -1;
            long questionStartTime = System.currentTimeMillis();

            // Auto-submit if time exceeds 10 seconds per question
            while (chosenAnswer < 1 || chosenAnswer > options.size()) {
                if (System.currentTimeMillis() - questionStartTime > 10 * 1000) {
                    System.out.println("Auto-submitting question due to time limit exceeded.");
                    chosenAnswer = 1; // Assuming first option as auto-submit
                    break;
                }

                System.out.print("Enter your answer (1-" + options.size() + "): ");
                chosenAnswer = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (chosenAnswer < 1 || chosenAnswer > options.size()) {
                    System.out.println("Invalid answer. Please choose again.");
                }
            }

            if (!examTimeUp && !q.isCorrectAnswer(chosenAnswer - 1)) {
                System.out.println("Wrong answer.");
            } else if (q.isCorrectAnswer(chosenAnswer - 1)) {
                score++;
            }

            System.out.println("Answer submitted.");
            System.out.println("****************************************");
        }

        user.setScore(score);
        System.out.println("Exam completed. Your score: " + score + "/" + totalQuestions);
        System.out.println("****************************************");
    }

    // Method to display scoreboard
    private static void displayScoreboard() {
        System.out.println("\nScoreboard:");
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
        System.out.println("****************************************");
    }

    // Method to update user profile
    private static void updateProfile(User user, Scanner sc) {
        System.out.println("\nUpdate Profile:");
        System.out.print("Enter new name: ");
        String newName = sc.nextLine().trim();
        System.out.print("Enter new email: ");
        String newEmail = sc.nextLine().trim();

        user.updateProfile(newName, newEmail);
        System.out.println("Profile updated successfully.");
        System.out.println("****************************************");
    }
}
