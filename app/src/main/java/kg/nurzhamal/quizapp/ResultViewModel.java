package kg.nurzhamal.quizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kg.nurzhamal.quizapp.model.QuizResult;

public class ResultViewModel extends ViewModel {

    MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public void onFinishClicked(){
        isFinished.setValue(true);
    }

    public void initResult(QuizResult resultQuiz) {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

    }
}
