package kg.nurzhamal.quizapp.data;

public class Questions_boolean {
    private String questions_b, answers1_b, answers2_b;

    public Questions_boolean(String questions, String answers1, String answers2) {
        this.questions_b = questions;
        this.answers1_b = answers1;
        this.answers2_b = answers2;
    }

    public String getQuestions_b() {
        return questions_b;
    }

    public void setQuestions_b(String questions_b) {
        this.questions_b = questions_b;
    }

    public String getAnswers1_b() {
        return answers1_b;
    }

    public void setAnswers1_b(String answers1_b) {
        this.answers1_b = answers1_b;
    }

    public String getAnswers2_b() {
        return answers2_b;
    }

    public void setAnswers2_b(String answers2_b) {
        this.answers2_b = answers2_b;
    }
}
