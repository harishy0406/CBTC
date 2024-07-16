import java.util.*;
public class GuessTN {
    static class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int maxAttempts = 10;
        int rounds = 3;
        List<Player> leaderboard = new ArrayList<>();

        System.out.println("*****************************************");
        System.out.println("### Welcome to the Precision Guess! ###");
        System.out.println("####******* by Harish Gautham *******####");
        System.out.println("*****************************************");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Start Game");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();

            if (option == 1) {
                System.out.print("Enter your name: ");
                sc.nextLine();
                String playerName = sc.nextLine();
                int totalScore = 0;

                for (int round = 1; round <= rounds; round++) {
                    int num = rand.nextInt(100) + 1;
                    int attempts = 0;
                    boolean guessed = false;

                    System.out.println("Round " + round);

                    while (attempts < maxAttempts && !guessed) {
                        System.out.print("Enter a number (1-100): ");
                        int guess = sc.nextInt();
                        attempts++;

                        if (guess == num) {
                            System.out.println("Correct! You guessed the number.");
                            guessed = true;
                            totalScore += (maxAttempts - attempts + 1);
                        } else if (guess < num) {
                            System.out.println("Higher");
                        } else {
                            System.out.println("Lower");
                        }
                    }

                    if (!guessed) {
                        System.out.println("You've used all attempts. The number was: " + num);
                    }
                }
                System.out.println("Game Over! Total Score: " + totalScore);
                leaderboard.add(new Player(playerName, totalScore));
            } else if (option == 2) {
                displayLeaderboard(leaderboard);
            } else if (option == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        sc.close();
    }
    private static void displayLeaderboard(List<Player> leaderboard) {
        System.out.println("\nLeaderboard:");
        leaderboard.sort((p1, p2) -> p2.score - p1.score);
        for (Player player : leaderboard) {
            System.out.println(player.name + ": " + player.score);
        }
    }
}
