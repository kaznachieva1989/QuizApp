package kg.nurzhamal.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.data.Item_for_type_questions;
import kg.nurzhamal.quizapp.data.Questions_boolean;
import kg.nurzhamal.quizapp.data.Questions_multi;
import kg.nurzhamal.quizapp.databinding.ActivityQuestionsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsBooleanItemsBinding;
import kg.nurzhamal.quizapp.databinding.QuestionsMultiItemBinding;
import kg.nurzhamal.quizapp.ui.adapter.QuestionsAdapter;

public class QuestionsActivity extends AppCompatActivity {
    List<Item_for_type_questions> items = new ArrayList<>();
    ActivityQuestionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_questions);

        items.add(new Item_for_type_questions(1, new Questions_boolean("Ты готов?", "Yes", "No")));
        items.add(new Item_for_type_questions(0, new Questions_multi("что такое Android?", "это мобильная операционная система", "это Матрица", "это Активити", "это Контейнер"))); //ответ 1
        items.add(new Item_for_type_questions(0, new Questions_multi("назовите 4 основные класса Java?", "Sensor", "SensorEvent", "SensorEventListener ", "SensorManager"))); // все ответы верные
        items.add(new Item_for_type_questions(1, new Questions_boolean("Продолжаем?", "Yes", "No")));
        items.add(new Item_for_type_questions(0, new Questions_multi("какие два основных метода хранения данных на Android?", "Shared Preferences", "Internal Storage", "Fragment ", "SensorManager"))); // ответ1,2
        items.add(new Item_for_type_questions(1, new Questions_boolean("Могут ли два разных приложения для Андроид иметь одинаковый Linux ID?", "Yes", "No"))); //ответ да
        items.add(new Item_for_type_questions(0, new Questions_multi("Какие компоненты включает в себя ядро Android?", "Activity", "Content Provider", "Fragment", "Intents and Services"))); // все ответы верны
        items.add(new Item_for_type_questions(0, new Questions_multi("ПЕРЕЧИСЛИТЕ, КАКИЕ ОСНОВНЫЕ ДИАЛОГОВЫЕ ОКНА МОГУТ ИСПОЛЬЗОВАТЬСЯ В АНДРОИД ПРИЛОЖЕНИИ?", "AlertDialog", "ProgressDialog", "DatePickerDialog", "TimePickerDialog"))); // все ответы верны
        items.add(new Item_for_type_questions(0, new Questions_multi("The capital of Russia?", "Sochi", "Milan", "Birma", "Moscow")));
        items.add(new Item_for_type_questions(1, new Questions_boolean("Жапаров С. будет Президентом КР?", "Yes", "No")));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.questionsRecycler.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.questionsRecycler.setAdapter(new QuestionsAdapter(items));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.questionsRecycler);
    }
}