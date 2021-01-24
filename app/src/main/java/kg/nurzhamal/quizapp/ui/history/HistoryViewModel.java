package kg.nurzhamal.quizapp.ui.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import kg.nurzhamal.quizapp.QuizApp;
import kg.nurzhamal.quizapp.core.SingleLiveEvent;
import kg.nurzhamal.quizapp.model.QuizResult;

public class HistoryViewModel extends ViewModel {
    public MutableLiveData<ArrayList<QuizResult>> historyQuizResult = new MutableLiveData<>();
    public SingleLiveEvent<Void> deleteById = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> share = new SingleLiveEvent<>();


    public void popupMenuDelete(int position) {
        QuizApp.repository.deleteById(historyQuizResult.getValue().get(position).getId());
    }
}