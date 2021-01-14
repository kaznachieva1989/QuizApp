package kg.nurzhamal.quizapp.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kg.nurzhamal.quizapp.QuizApp;
import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.model.Category;

public class MainViewModel extends ViewModel {

    MutableLiveData<Category> mutableLiveData = new MutableLiveData<>();

    public void getCategory() {
        QuizApp.quizApiClient.getCategory(new IQuizApiClient.CategoryCallBack() {
            @Override
            public void onSuccess(Category result) {
                mutableLiveData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("ololo", e.getMessage());
            }
        });
    }
}