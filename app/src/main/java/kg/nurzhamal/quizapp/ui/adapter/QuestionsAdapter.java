package kg.nurzhamal.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.data.Item_for_type_questions;
import kg.nurzhamal.quizapp.data.Questions_boolean;
import kg.nurzhamal.quizapp.data.Questions_multi;
import kg.nurzhamal.quizapp.databinding.QuestionsBooleanItemsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsMultiItemBinding;
import kg.nurzhamal.quizapp.model.Question;

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Question> items = new ArrayList<>();

    public void setItems(List<Question> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
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
            ((QuestionsMultiViewHolder) holder).onBind(items.get(position));
        } else {
            ((QuestionsBoolean_ViewHolder) holder).onBind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).getType().equals("multiple")) {
            return 0;
        } else if (items.get(position).getType().equals("boolean")) {
            return 1;
        }
        return 9;
    }

    public static class QuestionsMultiViewHolder extends RecyclerView.ViewHolder {
        QuestionsMultiItemBinding mBindingMulti;

        public QuestionsMultiViewHolder(@NonNull QuestionsMultiItemBinding bindingMulti) {
            super(bindingMulti.getRoot());
            mBindingMulti = bindingMulti;
        }

        public void onBind(Question q) {
            mBindingMulti.setQuestions(q);
        }
    }

    public static class QuestionsBoolean_ViewHolder extends RecyclerView.ViewHolder {
        QuestionsBooleanItemsBinding mBindingBoolean;

        public QuestionsBoolean_ViewHolder(@NonNull QuestionsBooleanItemsBinding bindingBoolean) {
            super(bindingBoolean.getRoot());
            mBindingBoolean = bindingBoolean;
        }

        public void onBind(Question q) {
            mBindingBoolean.setQuestionsForBoolean(q);
        }
    }
}
