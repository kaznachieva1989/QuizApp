package kg.nurzhamal.quizapp;

import android.app.Application;

import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.data.QuizApiClient;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();
    }
}
