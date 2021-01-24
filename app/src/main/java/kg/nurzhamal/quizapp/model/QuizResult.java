package kg.nurzhamal.quizapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

import kg.nurzhamal.quizapp.db.converter.DateConverter;
import kg.nurzhamal.quizapp.db.converter.QuestionsConverter;

@Entity(tableName = "QResult")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private final String category;
    @ColumnInfo(name = "difficulty")
    private final String difficulty;
    @ColumnInfo(name = "correctAnswer")
    private final int correctAnswer;
    @TypeConverters({DateConverter.class})
    private Date createdAt;
    @TypeConverters({QuestionsConverter.class})
    private List<Question> questions;

    public QuizResult(String category, String difficulty, int correctAnswer, Date createdAt, List<Question> questions) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

