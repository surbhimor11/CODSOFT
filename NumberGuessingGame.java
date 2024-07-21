package codsoft;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE_START = 1;
    private static final int RANGE_END = 100;
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            playRound(scanner);
            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }

    private static void playRound(Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(RANGE_END - RANGE_START + 1) + RANGE_START;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("I have generated a number between " + RANGE_START + " and " + RANGE_END + ". Try to guess it!");

        while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
            System.out.println("Enter your guess (attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "):");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == targetNumber) {
                guessedCorrectly = true;
                System.out.println("Congratulations! You guessed the correct number!");
                score += (MAX_ATTEMPTS - attempts + 1); // Higher score for fewer attempts
            } else if (userGuess < targetNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts. The correct number was " + targetNumber);
        }
    }
}

