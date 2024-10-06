
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Savequiz {
    public List<Createquiz> quizzes;

    public Savequiz() {
        quizzes = new ArrayList<>();
        loadQuizzes();
    }


    public void loadQuizzes() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("quiz.json").toURI())));
            JSONArray quizArray = new JSONArray(content);
            for (int i = 0; i < quizArray.length(); i++) {
                JSONObject quizJson = quizArray.getJSONObject(i);
                String question = quizJson.getString("question");
                String[] options = {
                        quizJson.getString("option 1"),
                        quizJson.getString("option 2"),
                        quizJson.getString("option 3"),
                        quizJson.getString("option 4")
                };
                int answerKey = quizJson.getInt("answerkey");
                quizzes.add(new Createquiz(question, options, answerKey));
            }
        } catch (Exception e) {
            System.err.println("Error loading quizzes: " + e.getMessage());
        }
    }

    public void addQuiz(Createquiz quiz) {
        quizzes.add(quiz);
        saveQuizzes();
    }

    public void saveQuizzes() {
        JSONArray jsonArray = new JSONArray();


        for (Createquiz quiz : quizzes) {
            jsonArray.put(quiz.toJson());
        }


        try {
            FileWriter file = new FileWriter("src/main/resources/quiz.json");

            try {
                file.write(jsonArray.toString());

                file.close();
                System.out.println("JSON written to file successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Files.write(Paths.get(this.getClass().getResource("quiz.json").toURI()), jsonArray.toString(4).getBytes());
        } catch (Exception e) {
            System.err.println("Error saving quizzes: " + e.getMessage());
        }
    }

    public List<Createquiz> getRandomQuizzes(int count) {
        List<Createquiz> selectedQuizzes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selectedQuizzes.add(quizzes.get((int) (Math.random() * quizzes.size())));
        }
        return selectedQuizzes;
    }

    public List<Createquiz> getAllQuizzes() {
        return quizzes;
    }
}