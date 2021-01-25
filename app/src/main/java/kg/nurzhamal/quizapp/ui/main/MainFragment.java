package kg.nurzhamal.quizapp.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
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
import kg.nurzhamal.quizapp.databinding.MainFragmentBinding;
import kg.nurzhamal.quizapp.model.Category;
import kg.nurzhamal.quizapp.model.TriviaCategory;

public class MainFragment extends Fragment {
    private MainFragmentBinding mainFragmentBinding;

    public static final String CATEGORY = "category";
    public static final String DIFFCULTY = "diffculty";
    public static final String ID = "id";

    Integer mCategory = null;
    List<String> list = new ArrayList<>();

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
        mainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        mainFragmentBinding.setLifecycleOwner(this);
        View view = mainFragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainFragmentBinding.bigNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) mainFragmentBinding.spinnerServices2.getSelectedView();
                String result = textView.getText().toString().toLowerCase();

                Intent intent = new Intent(getContext(), QuestionsActivity.class);
                intent.putExtra(ID, mainFragmentBinding.seekBar.getProgress());
                intent.putExtra(CATEGORY, mCategory);
                intent.putExtra(DIFFCULTY, result);
                startActivity(intent);
            }
        });
        spinnerServices();
        getData();
        seekBarListener();
    }

    private void seekBarListener() {
        mainFragmentBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                mainFragmentBinding.questionAmount.setText("Question amount: " + progress);
                // Toast.makeText(getContext(), "SeekBar in progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Toast.makeText(getContext(), "SeekBar in StartTracking", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mainFragmentBinding.questionAmount.setText("Question amount: " + progress_value);
                // Toast.makeText(getContext(), "SeekBar in StopTracking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spinnerServices() {
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainFragmentBinding.spinnerServices2.setAdapter(spinnerAdapter);
        list.add("Any type");
        list.add("Easy");
        list.add("Medium");
        list.add("Hard");

        spinnerAdapter.addAll(list);
        spinnerAdapter.notifyDataSetChanged();
    }

    private void getData() {
        mViewModel.getCategory();
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                List<TriviaCategory> categoryList = category.getTriviaCategories();
                List<String> name_category = new ArrayList<>();
                for (TriviaCategory triviaCategory : categoryList)
                    name_category.add(triviaCategory.getName());
                name_category.add(0, "Any type");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mainFragmentBinding.spinnerServices.setAdapter(adapter);
                mainFragmentBinding.spinnerServices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) mCategory = null;
                        else mCategory = category.getTriviaCategories().get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}