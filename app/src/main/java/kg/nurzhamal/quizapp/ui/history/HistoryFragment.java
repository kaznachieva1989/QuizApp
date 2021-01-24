package kg.nurzhamal.quizapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import kg.nurzhamal.quizapp.QuizApp;
import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.databinding.HistoryFragmentBinding;
import kg.nurzhamal.quizapp.model.QuizResult;
import kg.nurzhamal.quizapp.ui.adapter.HistoryAdapter;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    HistoryAdapter historyAdapter;
    HistoryFragmentBinding binding;
    private int position;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        historyAdapter = new HistoryAdapter();
        binding.recyclerHistory.setAdapter(historyAdapter);
        historyAdapter.setQuizResults((ArrayList<QuizResult>) QuizApp.quizDataBase.quizDao().getAll());
        historyAdapter.setOnPopupMenuClick(this::showPopUpMenu);

    }

    private void showPopUpMenu(View view, int position) {
        this.position = position;
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu
                .setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            mViewModel.deleteById.call();
                            return true;
                        case R.id.no:
                            mViewModel.share.call();
                            return true;
                        default:
                            return false;
                    }
                });

        popupMenu.show();
    }
}