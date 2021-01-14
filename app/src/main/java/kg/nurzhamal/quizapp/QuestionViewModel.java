package kg.nurzhamal.quizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.data.QuizApiClient;
import kg.nurzhamal.quizapp.model.Question;
import kg.nurzhamal.quizapp.model.QuizResponse;

public class QuestionViewModel extends ViewModel {
    public MutableLiveData<List<Question>> quizResponseMutableLiveData = new MutableLiveData<>();

    public void getQuestions(Integer amount, Integer category, String difficulty) {
        QuizApp.quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                quizResponseMutableLiveData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, amount, category, difficulty);
    }
}
