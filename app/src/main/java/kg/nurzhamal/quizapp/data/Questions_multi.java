package kg.nurzhamal.quizapp.data;

public class Questions_multi {
    private String questions, answers1, answers2, answers3, answers4;

    public Questions_multi(String questions, String answers1, String answers2, String answers3, String answers4) {
        this.questions = questions;
        this.answers1 = answers1;
        this.answers2 = answers2;
        this.answers3 = answers3;
        this.answers4 = answers4;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers1() {
        return answers1;
    }

    public void setAnswers1(String answers1) {
        this.answers1 = answers1;
    }

    public String getAnswers2() {
        return answers2;
    }

    public void setAnswers2(String answers2) {
        this.answers2 = answers2;
    }

    public String getAnswers3() {
        return answers3;
    }

    public void setAnswers3(String answers3) {
        this.answers3 = answers3;
    }

    public String getAnswers4() {
        return answers4;
    }

    public void setAnswers4(String answers4) {
        this.answers4 = answers4;
    }
}
