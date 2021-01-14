package kg.nurzhamal.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.data.Item_for_type_questions;
import kg.nurzhamal.quizapp.data.Questions_boolean;
import kg.nurzhamal.quizapp.data.Questions_multi;
import kg.nurzhamal.quizapp.databinding.ActivityQuestionsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsBooleanItemsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsMultiItemBinding;
import kg.nurzhamal.quizapp.model.Question;
import kg.nurzhamal.quizapp.model.QuizResponse;
import kg.nurzhamal.quizapp.ui.adapter.QuestionsAdapter;
import kg.nurzhamal.quizapp.ui.main.MainFragment;

public class QuestionsActivity extends AppCompatActivity {
    QuestionViewModel questionViewModel;
    List<Question> items = new ArrayList<>();
    ActivityQuestionsBinding binding;
    int categoryId;
    String difficulty;
    QuestionsAdapter questionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_questions);

        categoryId = getIntent().getIntExtra(MainFragment.CATEGORY, -1);
        difficulty = getIntent().getStringExtra(MainFragment.DIFFCULTY);
        initRecycler();

        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        getData();

    }


    private void getData() {
        questionViewModel.getQuestions(20, categoryId, difficulty);
        questionViewModel.quizResponseMutableLiveData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> quizResponse) {
                Log.d("Tilya2", String.valueOf(quizResponse.size()));
                questionsAdapter.setItems(quizResponse);
            }
        });
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.questionsRecycler.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionsAdapter = new QuestionsAdapter();
        binding.questionsRecycler.setAdapter(questionsAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.questionsRecycler);
    }
}