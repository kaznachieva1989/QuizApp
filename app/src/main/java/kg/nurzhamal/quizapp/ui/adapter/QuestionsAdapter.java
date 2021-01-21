package kg.nurzhamal.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.databinding.QuestionsBooleanItemsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsMultiItemBinding;
import kg.nurzhamal.quizapp.model.Question;

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Question> questionsList = new ArrayList<>();
    private OnClickQuestionAdapter onClickQuestionAdapter;

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList.addAll(questionsList);
        notifyDataSetChanged();
    }

    public void setListener(OnClickQuestionAdapter onClickQuestionAdapter) {
        this.onClickQuestionAdapter = onClickQuestionAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            QuestionsMultiItemBinding bindingMulti = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_multi_item, parent, false));
            return new QuestionsMultiViewHolder(bindingMulti);
        } else {
            QuestionsBooleanItemsBinding bindingBoolean = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_boolean_items, parent, false));
            return new QuestionsBooleanViewHolder(bindingBoolean);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            ((QuestionsMultiViewHolder) holder).onBind(questionsList.get(position));
        } else {
            ((QuestionsBooleanViewHolder) holder).onBind(questionsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (questionsList.get(position).getType().equals("multiple")) {
            return 0;
        } else if (questionsList.get(position).getType().equals("boolean")) {
            return 1;
        }
        return 9;
    }

    public class QuestionsMultiViewHolder extends RecyclerView.ViewHolder {
        QuestionsMultiItemBinding mBindingMulti;

        public QuestionsMultiViewHolder(@NonNull QuestionsMultiItemBinding bindingMulti) {
            super(bindingMulti.getRoot());
            this.mBindingMulti = bindingMulti;

        }

        public void onBind(Question q) {
            mBindingMulti.setQuestions(q);
            mBindingMulti.buttonAnswer1.setBackgroundResource(R.drawable.bg_button4);
            mBindingMulti.buttonAnswer2.setBackgroundResource(R.drawable.bg_button4);
            mBindingMulti.buttonAnswer3.setBackgroundResource(R.drawable.bg_button4);
            mBindingMulti.buttonAnswer4.setBackgroundResource(R.drawable.bg_button4);
            mBindingMulti.buttonAnswer1.setTextColor(Color.BLUE);
            mBindingMulti.buttonAnswer2.setTextColor(Color.BLUE);
            mBindingMulti.buttonAnswer3.setTextColor(Color.BLUE);
            mBindingMulti.buttonAnswer4.setTextColor(Color.BLUE);

            if(q.isClicked()) buttonState(q);

            mBindingMulti.buttonAnswer1.setOnClickListener(v -> {
                if(!q.isClicked()){
                    btn_state(mBindingMulti.buttonAnswer1, q);
                    onClickQuestionAdapter.onClickOnQuestion(getAdapterPosition(),0);
                    buttonState(q);
                    q.setUserChoisePos(1);
                }
            });

            mBindingMulti.buttonAnswer2.setOnClickListener(v -> {
                if(!q.isClicked()){
                    btn_state(mBindingMulti.buttonAnswer2, q);
                    onClickQuestionAdapter.onClickOnQuestion(getAdapterPosition(),1);
                    buttonState(q);
                    q.setUserChoisePos(2);
                }
            });
            mBindingMulti.buttonAnswer3.setOnClickListener(v -> {
                if(!q.isClicked()){
                    btn_state(mBindingMulti.buttonAnswer3, q);
                    onClickQuestionAdapter.onClickOnQuestion(getAdapterPosition(),2);
                    buttonState(q);
                    q.setUserChoisePos(3);
                }
            });

            mBindingMulti.buttonAnswer4.setOnClickListener(v -> {
                if(!q.isClicked()){
                    btn_state(mBindingMulti.buttonAnswer4, q);
                    onClickQuestionAdapter.onClickOnQuestion(getAdapterPosition(),3);
                    buttonState(q);
                    q.setUserChoisePos(4);
                }
            });
        }

        private void buttonState(Question q) {
            switch (q.getUserChoisePos()){
                case 1:
                    mBindingMulti.buttonAnswer1.setBackgroundResource(R.drawable.bg_button3);
                    mBindingMulti.buttonAnswer1.setTextColor(Color.WHITE);
                    break;
                case 2:
                    mBindingMulti.buttonAnswer2.setBackgroundResource(R.drawable.bg_button3);
                    mBindingMulti.buttonAnswer2.setTextColor(Color.WHITE);
                    break;
                case 3:
                    mBindingMulti.buttonAnswer3.setBackgroundResource(R.drawable.bg_button3);
                    mBindingMulti.buttonAnswer3.setTextColor(Color.WHITE);
                    break;
                case 4:
                    mBindingMulti.buttonAnswer4.setBackgroundResource(R.drawable.bg_button3);
                    mBindingMulti.buttonAnswer4.setTextColor(Color.WHITE);
                    break;
            }
            showCorrectBtn(q);
        }

        private void showCorrectBtn(Question q) {
            String correctAnc = q.getCorrectAnswer();
            int positionCorrectAnc = 0;
            for (int i = 0; i < q.getIncorrectAnswers().size(); i++) {
                if (correctAnc.equals(q.getIncorrectAnswers().get(i)))
                    positionCorrectAnc = i;
            }
            switch (positionCorrectAnc) {
                case 0:
                    mBindingMulti.buttonAnswer1.setBackgroundResource(R.drawable.bg_button2);
                    mBindingMulti.buttonAnswer1.setTextColor(Color.WHITE);
                    break;

                case 1:
                    mBindingMulti.buttonAnswer2.setBackgroundResource(R.drawable.bg_button2);
                    mBindingMulti.buttonAnswer2.setTextColor(Color.WHITE);
                    break;
                case 2:
                    mBindingMulti.buttonAnswer3.setBackgroundResource(R.drawable.bg_button2);
                    mBindingMulti.buttonAnswer3.setTextColor(Color.WHITE);
                    break;
                case 3:
                    mBindingMulti.buttonAnswer4.setBackgroundResource(R.drawable.bg_button2);
                    mBindingMulti.buttonAnswer3.setTextColor(Color.WHITE);
                    break;
            }

        }
    }

    public class QuestionsBooleanViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        QuestionsBooleanItemsBinding mBindingBoolean;

        public QuestionsBooleanViewHolder(@NonNull QuestionsBooleanItemsBinding bindingBoolean) {
            super(bindingBoolean.getRoot());
            this.mBindingBoolean = bindingBoolean;
        }

        @SuppressLint("ClickableViewAccessibility")
        public void onBind(Question q) {
            mBindingBoolean.setQuestionsForBoolean(q);
            mBindingBoolean.buttonBAnswer1.setBackgroundResource(R.drawable.bg_button4);
            mBindingBoolean.buttonBAnswer2.setBackgroundResource(R.drawable.bg_button4);
            mBindingBoolean.buttonBAnswer1.setTextColor(Color.BLUE);
            mBindingBoolean.buttonBAnswer2.setTextColor(Color.BLUE);

            mBindingBoolean.buttonBAnswer1.setOnTouchListener(this);
            mBindingBoolean.buttonBAnswer2.setOnTouchListener(this);

            if (q.isClicked()) buttonState(q);

            mBindingBoolean.buttonBAnswer1.setOnClickListener(v -> {
                if (!q.isClicked()) {
                    btn_state(mBindingBoolean.buttonBAnswer1, q);
                    onClickQuestionAdapter.onClickOnQuestion(getAdapterPosition(), 0);
                    buttonState(q);
                    q.setUserChoisePos(1);
                }
            });

            mBindingBoolean.buttonBAnswer2.setOnClickListener(v -> {
                if (!q.isClicked()) {
                    btn_state(mBindingBoolean.buttonBAnswer2, q);
                    onClickQuestionAdapter.onClickOnQuestion(getAdapterPosition(), 1);
                    buttonState(q);
                    q.setUserChoisePos(2);
                }
            });
        }

        private void buttonState(Question q) {
            switch (q.getUserChoisePos()) {
                case 1:
                    mBindingBoolean.buttonBAnswer1.setBackgroundResource(R.drawable.bg_button3);
                    mBindingBoolean.buttonBAnswer1.setTextColor(Color.WHITE);
                    break;

                case 2:
                    mBindingBoolean.buttonBAnswer2.setBackgroundResource(R.drawable.bg_button3);
                    mBindingBoolean.buttonBAnswer2.setTextColor(Color.WHITE);
                    break;
            }
            showCorrectBtn(q);
        }

        private void showCorrectBtn(Question question) {
            String correctAnc = question.getCorrectAnswer();
            int positionCorrectAnc = 0;
            for (int i = 0; i < question.getIncorrectAnswers().size(); i++) {
                if (correctAnc.equals(question.getIncorrectAnswers().get(i)))
                    positionCorrectAnc = i;
            }
            switch (positionCorrectAnc) {
                case 0:
                    mBindingBoolean.buttonBAnswer1.setBackgroundResource(R.drawable.bg_button2);
                    break;

                case 1:
                    mBindingBoolean.buttonBAnswer2.setBackgroundResource(R.drawable.bg_button2);
                    break;
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mBindingBoolean.getQuestionsForBoolean().isClicked()) return false;
            Button button = (Button) v;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    button.setBackgroundResource(R.drawable.bg_button1);
                    button.setTextColor(Color.WHITE);
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    button.setBackgroundResource(R.drawable.bg_button4);
                    button.setTextColor(Color.BLUE);
                    return false; // if you want to handle the touch event
            }
            return false;

        }
    }

    private void btn_state(Button button, Question q) {
        if (q.getCorrectAnswer().equals(q.getIncorrectAnswers().get(0))) {
            button.setBackgroundResource(R.drawable.bg_button2);
        } else {
            button.setBackgroundResource(R.drawable.bg_button3);
        }
        button.setTextColor(Color.WHITE);
    }

    public interface OnClickQuestionAdapter {
        void onClickOnQuestion(int position, int answerPosition);

        void onClickOnQuestion(int position);
    }
}
