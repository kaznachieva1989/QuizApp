package kg.nurzhamal.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.core.OnPopupMenuClickListener;
import kg.nurzhamal.quizapp.databinding.HistoryItemBinding;
import kg.nurzhamal.quizapp.model.QuizResult;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private OnPopupMenuClickListener onPopupMenuClick;
    List<QuizResult> quizResults = new ArrayList<>();

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
        notifyDataSetChanged();
    }

    public void setOnPopupMenuClick(OnPopupMenuClickListener onPopupMenuClick) {
        this.onPopupMenuClick = onPopupMenuClick;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(HistoryItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false)));
    }

    @Override
    public int getItemCount() {
        return quizResults.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.binding.setQuizResult(quizResults.get(position));
        holder.binding.hDateTv.setText(getDate(quizResults.get(position).getCreatedAt()));
    }

    String getDate(Date time){
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(time);
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private final HistoryItemBinding binding;

        public HistoryViewHolder( HistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setHandlers(onPopupMenuClick);
            binding.popUpMenu.setOnClickListener(v -> onPopupMenuClick.onPopupMenuClick(v, getAdapterPosition()));
        }
    }
}
