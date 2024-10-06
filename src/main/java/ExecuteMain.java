import java.util.List;
import java.util.Scanner;

public class ExecuteMain {
    public static void main(String[] args) {
        Validateuser userManager = new Validateuser();
        Savequiz quizManager = new Savequiz();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userManager.ValidateUser(username, password);
        if (user == null) {
            System.out.println("Invalid credentials.");
            return;
        }

        if ("admin".equals(user.getRole())) {
            adminMenu(scanner, quizManager);
        } else if ("student".equals(user.getRole())) {
            studentMenu(scanner, quizManager);
        }

        scanner.close();
    }

    public static void adminMenu(Scanner scanner, Savequiz quizManager) {
        while (true) {
            System.out.print("Input your question: ");
            String question = scanner.nextLine();
            String[] options = new String[4];

            for (int i = 0; i < 4; i++) {
                System.out.print("Input option " + (i + 1) + ": ");
                options[i] = scanner.nextLine();
            }

            System.out.print("What is the answer key?: ");
            int answerKey = Integer.parseInt(scanner.nextLine());

            quizManager.addQuiz(new Createquiz(question, options, answerKey));

            System.out.print("Saved successfully! Add more questions? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
        }
    }

    public static void studentMenu(Scanner scanner, Savequiz quizManager) {
        System.out.println("Welcome to the quiz! Press 's' to start.");
        if (!scanner.nextLine().equalsIgnoreCase("s")) return;


        List<Createquiz> selectedQuizzes = quizManager.getRandomQuizzes(10);
           boolean isRunning=true;
           while(isRunning){
               int score = 0;
               for (int i = 0; i < selectedQuizzes.size(); i++) {
                   Createquiz quiz = selectedQuizzes.get(i);
                   System.out.println("\n[Question " + (i + 1) + "] " + quiz.getQuestion());
                   String[] options = quiz.getOptions();
                   for (int j = 0; j < options.length; j++) {
                       System.out.println((j + 1) + ". " + options[j]);
                   }
                   System.out.print("Your answer: ");
                   int answer = Integer.parseInt(scanner.nextLine());
                   if (answer == quiz.getAnswerKey()) score++;



               }
             //  System.out.println("You scored " + score + " out of " + selectedQuizzes.size() + ".");
               displayResult(score);
               System.out.print("Do you want to add more questions? (press s for start and q for quit): ");
               String more = scanner.nextLine();
               if (more.equalsIgnoreCase("q")) {
               isRunning=false;
               }

           }

    }
    public static void displayResult(int score){
        System.out.println("\nYou have completed the quiz! Your score is " + score + " out of 10.");
        if (score >= 8) {
            System.out.println("Excellent!");
        } else if (score >= 5) {
            System.out.println("Good.");
        } else if (score >= 2) {
            System.out.println("Very poor!");
        } else {
            System.out.println("Very sorry you are failed.");
        }


    }

}

