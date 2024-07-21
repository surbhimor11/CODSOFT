package codsoft;
import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Quiz {
    private List<Question> questions;
    private int score;
    private Scanner scanner;
    private Map<Integer, Boolean> results;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.scanner = new Scanner(System.in);
        this.results = new HashMap<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"1. William Shakespeare", "2. Mark Twain", "3. Charles Dickens", "4. Jane Austen"}, 1));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"}, 4));
        questions.add(new Question("What is the boiling point of water?", new String[]{"1. 90°C", "2. 100°C", "3. 110°C", "4. 120°C"}, 2));
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        for (int i = 0; i < questions.size(); i++) {
            displayQuestion(i);
        }
        displayResults();
    }

    private void displayQuestion(int index) {
        Question q = questions.get(index);
        System.out.println("\n" + (index + 1) + ". " + q.question);
        for (String option : q.options) {
            System.out.println(option);
        }
        System.out.print("Your answer (1-4): ");

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Moving to next question.");
                results.put(index, false);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 15000); // 15 seconds for each question

        if (scanner.hasNextInt()) {
            int answer = scanner.nextInt();
            timer.cancel();
            if (answer == q.correctAnswer) {
                System.out.println("Correct!");
                score++;
                results.put(index, true);
            } else {
                System.out.println("Incorrect.");
                results.put(index, false);
            }
        } else {
            System.out.println("Invalid input. Moving to next question.");
            scanner.next(); // clear the invalid input
            results.put(index, false);
        }
    }

    private void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            String result = results.get(i) ? "Correct" : "Incorrect";
            System.out.println((i + 1) + ". " + questions.get(i).question + " - " + result);
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}

