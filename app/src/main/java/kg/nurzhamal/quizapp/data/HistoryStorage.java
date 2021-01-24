package kg.nurzhamal.quizapp.data;

import kg.nurzhamal.quizapp.core.IHistoryStorage;
import kg.nurzhamal.quizapp.model.QuizResult;

public class HistoryStorage implements IHistoryStorage {
    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
