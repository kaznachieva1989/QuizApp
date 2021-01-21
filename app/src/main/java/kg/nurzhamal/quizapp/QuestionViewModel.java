package kg.nurzhamal.quizapp;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.core.SingleLiveEvent;
import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.model.Question;

public class QuestionViewModel extends ViewModel {
    public MutableLiveData<List<Question>> quizResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    ArrayList<String> categories = new ArrayList<>();
    public ArrayList<Question> questions;
    MutableLiveData<Integer> isLast = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    Integer count;

    public QuestionViewModel() {
        currentQuestionPosition.setValue(0);
        this.count = 0;
    }

    int correctAnswers;

    public void getQuestions(Integer amount, Integer category, String difficulty) {
        QuizApp.repository.getQuestions(new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    categories.add(result.get(i).getCategory());
                }
                questions = result;
                Log.d("response", String.valueOf(questions.get(0).getIncorrectAnswers().size()));
                quizResponseMutableLiveData.setValue(questions);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("onFailure", e.getMessage());
            }
        }, amount, category, difficulty);
    }

    public void moveToQuestionFinish() {
        if (currentQuestionPosition.getValue() == questions.size() - 1) {
            finish();
        } else {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue());
        }
    }

    private void finish() {
        isLast.setValue(correctAnswers);
    }

    void skip() {
        if (currentQuestionPosition.getValue() == questions.size() - 1) {
            return;
        } else {
            questions.get(currentQuestionPosition.getValue()).setClicked(true);
            quizResponseMutableLiveData.setValue(questions);
            currentQuestionPosition.setValue(++count);
        }
    }

    public void onAnswerClick(int questionPosition, int answerPosition) {
        if (currentQuestionPosition.getValue() == null || questions == null) {
            return;
        }
        if (questions.size() > questionPosition && questionPosition >= 0) {
            Question question = questions.get(questionPosition);
            question.setSelectAnswerPosition(answerPosition);
            question.setClicked(true);
            if (question.getIncorrectAnswers().get(answerPosition).equals(question.getCorrectAnswer())) {
                correctAnswers++;
                question.setAnswerTrue(true);
            } else {
                question.setAnswerTrue(false);
            }
            questions.set(questionPosition, question);


            new CountDownTimer(500, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    quizResponseMutableLiveData.setValue(questions);
                }
            }.start();

            if (questionPosition + 1 == questions.size()) {

            } else {
                new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        currentQuestionPosition.setValue(++count);
                    }
                }.start();
            }
        }
    }

    void onBackPressed() {
        if (currentQuestionPosition.getValue() != 0) {
            currentQuestionPosition.setValue(--count);
        } else {
            finishEvent.call();
        }
    }
}
