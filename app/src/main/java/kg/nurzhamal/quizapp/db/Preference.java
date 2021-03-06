package kg.nurzhamal.quizapp.db;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private final SharedPreferences sharedPreferences;

    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences("themeSharedPreferences", Context.MODE_PRIVATE);
    }

    public void setTheme(int value) {
        sharedPreferences.edit().putInt("theme", value).apply();
    }

    public int getTheme() {
        return sharedPreferences.getInt("theme", 0);
    }

    public void setThemePosition(int value) {
        sharedPreferences
                .edit()
                .putInt("themePosition", value)
                .apply();
    }

    public int getThemePosition() {
        return sharedPreferences.getInt("themePosition", 0);
    }

}
