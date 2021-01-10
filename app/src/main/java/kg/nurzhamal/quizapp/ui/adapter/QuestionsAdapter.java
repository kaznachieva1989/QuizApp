package kg.nurzhamal.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.data.Item_for_type_questions;
import kg.nurzhamal.quizapp.data.Questions_boolean;
import kg.nurzhamal.quizapp.data.Questions_multi;
import kg.nurzhamal.quizapp.databinding.QuestionsBooleanItemsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsMultiItemBinding;

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item_for_type_questions> items;

    public QuestionsAdapter(List<Item_for_type_questions> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            QuestionsMultiItemBinding bindingMulti = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_multi_item, parent, false));
            return new QuestionsMultiViewHolder(bindingMulti);
        } else {
            QuestionsBooleanItemsBinding bindingBoolean = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_boolean_items, parent, false));
            return new QuestionsBoolean_ViewHolder(bindingBoolean);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            Questions_multi questions_multi = (Questions_multi) items.get(position).getObject();
            ((QuestionsMultiViewHolder) holder).mBindingMulti.setQuestions(questions_multi);
        } else {
            Questions_boolean questions_boolean = (Questions_boolean) items.get(position).getObject();
            ((QuestionsBoolean_ViewHolder) holder).mBindingBoolean.setQuestionsForBoolean(questions_boolean);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public static class QuestionsMultiViewHolder extends RecyclerView.ViewHolder {
        QuestionsMultiItemBinding mBindingMulti;

        public QuestionsMultiViewHolder(@NonNull QuestionsMultiItemBinding bindingMulti) {
            super(bindingMulti.getRoot());
            mBindingMulti = bindingMulti;
        }
    }

    public static class QuestionsBoolean_ViewHolder extends RecyclerView.ViewHolder {
        QuestionsBooleanItemsBinding mBindingBoolean;

        public QuestionsBoolean_ViewHolder(@NonNull QuestionsBooleanItemsBinding bindingBoolean) {
            super(bindingBoolean.getRoot());
            mBindingBoolean = bindingBoolean;
        }
    }
}
