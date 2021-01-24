package kg.nurzhamal.quizapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kg.nurzhamal.quizapp.model.QuizResult;

@Database(entities = {QuizResult.class}, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
