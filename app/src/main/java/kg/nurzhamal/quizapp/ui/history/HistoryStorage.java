package kg.nurzhamal.quizapp.ui.history;

import kg.nurzhamal.quizapp.QuizApp;
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
        QuizApp.quizDataBase.quizDao().deleteById(id);
    }

    @Override
    public void deleteAll() {

    }
}
