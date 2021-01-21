package kg.nurzhamal.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.$Gson$Preconditions;

import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.core.OnPopupMenuClickListener;
import kg.nurzhamal.quizapp.databinding.HistoryItemBinding;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private OnPopupMenuClickListener onPopupMenuClick;

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
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private final HistoryItemBinding historyItemBinding;

        public HistoryViewHolder( HistoryItemBinding binding) {
            super(binding.getRoot());
            historyItemBinding = binding;
            binding.setHandlers(onPopupMenuClick);
            binding.popUpMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPopupMenuClick.onPopupMenuClick(v, getAdapterPosition());
                }
            });
        }
    }
}
