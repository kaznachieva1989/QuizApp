package kg.nurzhamal.quizapp.core;

import kg.nurzhamal.quizapp.QuizApp;
import kg.nurzhamal.quizapp.model.QuizResult;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    void deleteById(int id);


    void deleteAll();
}
