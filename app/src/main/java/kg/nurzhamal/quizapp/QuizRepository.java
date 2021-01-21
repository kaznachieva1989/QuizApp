package kg.nurzhamal.quizapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kg.nurzhamal.quizapp.core.IHistoryStorage;
import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.model.Category;
import kg.nurzhamal.quizapp.model.Question;

public class QuizRepository implements IQuizApiClient {
    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;
    Question question = new Question();


    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
    }

    @Override
    public void getQuestions(QuestionsCallBack callBack, Integer amount, Integer category, String difficulty) {
        quizApiClient.getQuestions(new QuestionsCallBack() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    Question question = result.get(i);
                    ArrayList<String> answers = new ArrayList<>(question.getIncorrectAnswers());
                    answers.add(question.getCorrectAnswer());
                    Collections.shuffle(answers);
                    result.get(i).setIncorrectAnswers(answers);
                    Log.d("response", result.get(i).getIncorrectAnswers().size() + " repository");
                }
                question = null;
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                question = null;
                callBack.onFailure(e);
            }
        }, amount, category, difficulty);
    }

    @Override
    public void getCategory(CategoryCallBack callBack) {
        quizApiClient.getCategory(new CategoryCallBack() {
            @Override
            public void onSuccess(Category result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
            }
        });
    }
}
