import org.json.JSONObject;

public class Createquiz {

    private String question;
    private String[] options;
    private int answerKey;

    public Createquiz(String question, String[] options, int answerKey) {
        this.question = question;
        this.options = options;
        this.answerKey = answerKey;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswerKey() {
        return answerKey;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("option 1", options[0]);
        json.put("option 2", options[1]);
        json.put("option 3", options[2]);
        json.put("option 4", options[3]);
        json.put("answerkey", answerKey);
        return json;
    }
}