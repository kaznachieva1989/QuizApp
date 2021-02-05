package kg.nurzhamal.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.databinding.ThemeItemBinding;
import kg.nurzhamal.quizapp.model.ThemeQuiz;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {
    List<ThemeQuiz> listTheme = new ArrayList<>();
    Listener listener;

    public void setData(List<ThemeQuiz> listTheme) {
        this.listTheme = listTheme;
        notifyDataSetChanged();
    }

    public void onCLick(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThemeViewHolder(ThemeItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        holder.onBind(listTheme.get(position));
    }

    @Override
    public int getItemCount() {
        return listTheme.size();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {
        private ThemeItemBinding binding;

        public ThemeViewHolder(@NonNull ThemeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> {
                binding.radioButton.setChecked(binding.radioButton.isChecked());
                if (binding.radioButton.isChecked())
                    listener.onThemeClick(getAdapterPosition());
            });
            binding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) listener.onThemeClick(getAdapterPosition());
            });
        }

        public void onBind(ThemeQuiz listTheme) {
            binding.imageViewTheme.setImageDrawable(ContextCompat.getDrawable(binding.getRoot().getContext(), listTheme.getIcon()));
            binding.radioButton.setChecked(listTheme.isChange());
        }
    }

    public interface Listener {
        void onThemeClick(int position);
    }
}
