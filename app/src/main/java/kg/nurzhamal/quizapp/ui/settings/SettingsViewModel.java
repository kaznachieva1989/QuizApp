package kg.nurzhamal.quizapp.ui.settings;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.quizapp.QuizApp;
import kg.nurzhamal.quizapp.R;
import kg.nurzhamal.quizapp.model.ThemeQuiz;

public class SettingsViewModel extends ViewModel {
    public MutableLiveData<List<ThemeQuiz>> themes = new MutableLiveData<>();
    public MutableLiveData<Integer> showTheme = new MutableLiveData<>();

    public SettingsViewModel() {
        main();
    }

    private void main() {
        List<ThemeQuiz> data = new ArrayList<>();
        data.add(new ThemeQuiz(R.drawable.green_theme_icon, false));
        data.add(new ThemeQuiz(R.drawable.blue_theme_icon, false));
        data.add(new ThemeQuiz(R.drawable.orange_theme_icon, false));
        data.add(new ThemeQuiz(R.drawable.ic_pink_theme_icon2, false));
        data.add(new ThemeQuiz(R.drawable.ic_yellow_theme_icon, false));
        data.get(QuizApp.getInstance().preference.getThemePosition()).setChange(true);
        themes.setValue(data);
    }

    public void onThemeChanged(int position) {
        switch (position) {
            case 0:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.PinkTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.PinkTheme);
                    showTheme.setValue(R.style.PinkTheme);
                }
                break;
            case 1:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.GreenTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.GreenTheme);
                    showTheme.setValue(R.style.GreenTheme);
                }
                break;
            case 2:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.BlueHeart) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.BlueHeart);
                    showTheme.setValue(R.style.BlueHeart);
                }
                break;
            case 3:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.YellowTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.YellowTheme);
                    showTheme.setValue(R.style.YellowTheme);
                }
            case 4:
                if (QuizApp.getInstance().getPrefs().getTheme() != R.style.OrangeTheme) {
                    QuizApp.getInstance().getPrefs().setTheme(R.style.OrangeTheme);
                    showTheme.setValue(R.style.OrangeTheme);
                }
                break;
        }
        QuizApp.getInstance().preference.setThemePosition(position);
        Log.e("kokoko", "onThemeChanged: " + QuizApp.getInstance().getPrefs().getTheme());
    }
}