package kg.nurzhamal.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.databinding.ActivityQuestionsBinding;
import kg.nurzhamal.quizapp.model.Question;
import kg.nurzhamal.quizapp.ui.adapter.QuestionsAdapter;
import kg.nurzhamal.quizapp.ui.main.MainFragment;

public class QuestionsActivity extends AppCompatActivity implements QuestionsAdapter.OnClickQuestionAdapter {
    QuestionViewModel questionViewModel;
    List<Question> items = new ArrayList<>();
    ActivityQuestionsBinding binding;
    int categoryId;
    int position;
    int questionAmount;
    String difficulty = "any type";
    int clickedPosition;
    QuestionsAdapter questionsAdapter;
    List<Question> quizResponse;

    public static final String CORRECT_ANSWERS = "correct_answers";
    public static final String QUESTIONS = "questions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_questions);

        categoryId = getIntent().getIntExtra(MainFragment.CATEGORY, 99);
        difficulty = getIntent().getStringExtra(MainFragment.DIFFCULTY);
        questionAmount = getIntent().getIntExtra(MainFragment.ID,-1);
        initRecycler();
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        getData();

        lastQuestion();

        binding.backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionsActivity.this, ResultActivity.class));
                if (position > clickedPosition){
                    questionViewModel.skip(position);
                }
                binding.questionsRecycler.scrollToPosition(position + 1);
            }
        });

        binding.questionsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                CustomLinearLayoutManager linearLayoutManager = (CustomLinearLayoutManager) recyclerView.getLayoutManager();
                position = linearLayoutManager.findFirstVisibleItemPosition();
                binding.setPos(position + 1);
                binding.progressTv.setText(String.valueOf(position + 1) + "/" + String.valueOf(questionViewModel.questions.size()));
                binding.themeCategoryB.setText(questionViewModel.categories.get(position));
            }
        });

        questionViewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                binding.questionsRecycler.scrollToPosition(position);
            }
        });

        questionViewModel.finishEvent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                finish();
            }
        });
    }

    private void lastQuestion() {
        questionViewModel.isLast.observe(this, correctA -> {
            Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
            intent.putExtra(CORRECT_ANSWERS, correctA);
            intent.putExtra(QUESTIONS,new Gson().toJson(questionViewModel.questions));
            startActivity(intent);
            finish();
        });
    }

    private void getData() {
        questionViewModel.getQuestions(questionAmount, categoryId, difficulty);
        questionViewModel.questionsMutableLiveData.observe(this, quizResponse -> {
            questionsAdapter.setQuestionsList(quizResponse);
            QuestionsActivity.this.quizResponse = quizResponse;
            binding.progressBar.setMax(quizResponse.size());
            binding.themeCategoryB.setText(quizResponse.get(0).getCategory());
        });
    }

    private void initRecycler() {
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.questionsRecycler.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionsAdapter = new QuestionsAdapter();
        questionsAdapter.setListener(this);
        binding.questionsRecycler.setAdapter(questionsAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        binding.setPos(1);
        snapHelper.attachToRecyclerView(binding.questionsRecycler);
    }

    @Override
    public void onBackPressed() {
        questionViewModel.onBackPressed();
    }

    @Override
    public void onClickOnQuestion(int position, int answerPosition) {
        questionViewModel.onAnswerClick(position, answerPosition);
        questionViewModel.moveToQuestionFinish(position);
    }

    @Override
    public void onClickOnQuestion(int position) {
        startActivity(new Intent(this, ResultActivity.class));
    }
}