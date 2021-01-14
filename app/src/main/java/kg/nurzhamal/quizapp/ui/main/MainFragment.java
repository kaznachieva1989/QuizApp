package kg.nurzhamal.quizapp.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.QuestionsActivity;
import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.model.Category;
import kg.nurzhamal.quizapp.model.TriviaCategory;

public class MainFragment extends Fragment {
    public static final String CATEGORY = "category";
    public static final String DIFFCULTY = "diffculty";


    SeekBar seekBar;
    TextView amount;
    Spinner spinner, spinnerDifficulty;
    Integer mCategory;
    private String difficulties;
    List<String> list = new ArrayList<>();
    Button btn_Start;

    ArrayAdapter<String> spinnerAdapter;

    private String nameCategoryTitleQuestionActivity = "";

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        amount = (TextView) view.findViewById(R.id.question_amount);
        spinner = view.findViewById(R.id.spinner_services);
        btn_Start = view.findViewById(R.id.big_next_button);

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) spinnerDifficulty.getSelectedView();
                String result = textView.getText().toString().toLowerCase();

                Intent intent = new Intent(getContext(), QuestionsActivity.class);
                intent.putExtra(CATEGORY, mCategory);
                intent.putExtra(DIFFCULTY, result);
                startActivity(intent);
            }
        });
        spinnerDifficulty = view.findViewById(R.id.spinner_services2);

        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(spinnerAdapter);
        list.add("Any type");
        list.add("Easy");
        list.add("Medium");
        list.add("Hard");

        spinnerAdapter.addAll(list);
        spinnerAdapter.notifyDataSetChanged();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                amount.setText("Question amount: " + progress);
                // Toast.makeText(getContext(), "SeekBar in progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Toast.makeText(getContext(), "SeekBar in StartTracking", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                amount.setText("Question amount: " + progress_value);
                // Toast.makeText(getContext(), "SeekBar in StopTracking", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        getData();
    }

    private void getData() {
        mViewModel.getCategory();
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                List<TriviaCategory> categoryList = category.getTriviaCategories();
                List<String> name_category = new ArrayList<>();
                for (TriviaCategory triviaCategory : categoryList) {
                    name_category.add(triviaCategory.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mCategory = category.getTriviaCategories().get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}