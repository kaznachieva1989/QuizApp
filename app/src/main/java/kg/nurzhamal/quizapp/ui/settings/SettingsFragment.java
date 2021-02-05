package kg.nurzhamal.quizapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import kg.nurzhamal.quizapp.QuizApp;
import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.databinding.SettingsFragmentBinding;
import kg.nurzhamal.quizapp.model.ThemeQuiz;
import kg.nurzhamal.quizapp.ui.adapter.ThemeAdapter;

public class SettingsFragment extends Fragment implements ThemeAdapter.Listener {

    private SettingsViewModel mViewModel;
    SettingsFragmentBinding binding;
    ThemeAdapter adapter;


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        adapter = new ThemeAdapter();
        mViewModel.themes.observe(this, new Observer<List<ThemeQuiz>>() {
            @Override
            public void onChanged(List<ThemeQuiz> themeQuizs) {
                adapter.setData(themeQuizs);
            }
        });
        binding.recyclerviewTheme.setAdapter(adapter);
        adapter.onCLick(this);
        mViewModel.showTheme.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });

        binding.layout4.setOnClickListener(v -> {
            QuizApp.quizDataBase.quizDao().deleteAll();
            Toast.makeText(requireActivity(), "history is deleted", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onThemeClick(int position) {
        mViewModel.onThemeChanged(position);
    }
}