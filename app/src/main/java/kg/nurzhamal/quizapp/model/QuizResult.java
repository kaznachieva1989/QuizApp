package kg.nurzhamal.quizapp.model;

import java.util.Date;
import java.util.List;

public class QuizResult {
    private final boolean isWin;
    private final String category;
    private final String difficulty;
    private final String correctAns;
    private final String resultPercentage;
    private final int AmountCorrectAns;

    public QuizResult(boolean isWin, String category, String difficulty, String correctAns, String resultPercentage, int amountCorrectAns) {
        this.isWin = isWin;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.resultPercentage = resultPercentage;
        AmountCorrectAns = amountCorrectAns;
    }

    public boolean isWin() {
        return isWin;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String getResultPercentage() {
        return resultPercentage;
    }

    public int getAmountCorrectAns() {
        return AmountCorrectAns;
    }

}

