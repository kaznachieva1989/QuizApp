package kg.nurzhamal.quizapp;

import android.app.Application;

import kg.nurzhamal.quizapp.core.IHistoryStorage;
import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.data.QuizApiClient;
import kg.nurzhamal.quizapp.ui.history.HistoryStorage;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizRepository repository;


    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();
        historyStorage = new HistoryStorage();
        repository = new QuizRepository(quizApiClient, historyStorage);
    }
}
