package kg.nurzhamal.quizapp;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kg.nurzhamal.quizapp.model.Question;
import kg.nurzhamal.quizapp.model.QuizResult;

public class ResultViewModel extends ViewModel {

    MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public ObservableField<String> percentField = new ObservableField<>();

    private ArrayList<Question> questions = new ArrayList<>();
    private int percent = 0;

    MutableLiveData<ArrayList<Question>> mutableLiveDataQuestions = new MutableLiveData<>();

    void getData(Intent intent){

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Question>>() {}.getType();

        questions = gson.fromJson(intent.getStringExtra(QuestionsActivity.QUESTIONS),type);
        mutableLiveDataQuestions.setValue(questions);

    }

    public void getPercent(int questionsAmount,int correctAnswers){
        percent = correctAnswers  * 100 / questionsAmount;

        percentField.set(percent + " %");
    }

    public void saveResultToDB(QuizResult quizResult){
        QuizApp.quizDataBase.quizDao().insert(quizResult);
    }

    public void onFinishClicked(){
        isFinished.setValue(true);
    }

    public void initResult(QuizResult resultQuiz) {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

    }
}
