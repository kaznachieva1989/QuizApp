package kg.nurzhamal.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;


import kg.nurzhamal.quizapp.databinding.ActivityResultBinding;
import kg.nurzhamal.quizapp.model.Question;
import kg.nurzhamal.quizapp.model.QuizResult;

public class ResultActivity extends AppCompatActivity {
    ResultViewModel resultViewModel;
    ActivityResultBinding binding;
    QuizResult quizResult;
    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        binding.setViewModel(resultViewModel);

        correctAnswers = getIntent().getIntExtra(QuestionsActivity.CORRECT_ANSWERS, 0);
        resultViewModel.getData(getIntent());

        resultViewModel.mutableLiveDataQuestions.observe(this, new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                if (questions != null && questions.size() > 0) {
                    quizResult = new QuizResult(questions.get(0).getCategory()
                            , questions.get(0).getDifficulty()
                            , correctAnswers
                            , new Date(System.currentTimeMillis())
                            , questions);
                    resultViewModel.getPercent(questions.size(), correctAnswers);
                    binding.setResult(quizResult);
                }
            }
        });

        binding.btnFinish.setOnClickListener(view -> {
            resultViewModel.saveResultToDB(quizResult);
            finish();
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}