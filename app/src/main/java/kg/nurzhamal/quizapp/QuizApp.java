package kg.nurzhamal.quizapp;

import android.app.Application;

import androidx.room.Room;

import kg.nurzhamal.quizapp.core.IHistoryStorage;
import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.data.QuizApiClient;
import kg.nurzhamal.quizapp.db.Preference;
import kg.nurzhamal.quizapp.db.QuizDataBase;
import kg.nurzhamal.quizapp.ui.history.HistoryStorage;

public class QuizApp extends Application {

    private static QuizApp instance;

    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizRepository repository;
    public static QuizDataBase quizDataBase;
    public Preference preference;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        quizApiClient = new QuizApiClient();
        historyStorage = new HistoryStorage();
        quizDataBase = Room.databaseBuilder(
                this,
                QuizDataBase.class,
                "quiz.db"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        repository = new QuizRepository(quizApiClient, historyStorage);
        preference = new Preference(this);
    }
    public static QuizApp getInstance(){
        return instance;
    }

    public static QuizRepository getRepository() {
        return repository;
    }

    public static QuizDataBase getQuizDataBase() {
        return quizDataBase;
    }

    public Preference getPrefs() {
        return preference;
    }

}
