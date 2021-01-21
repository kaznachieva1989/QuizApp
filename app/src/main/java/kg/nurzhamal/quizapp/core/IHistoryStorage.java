package kg.nurzhamal.quizapp.core;

import kg.nurzhamal.quizapp.model.QuizResult;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    void delete(int id);

    void deleteAll();
}
