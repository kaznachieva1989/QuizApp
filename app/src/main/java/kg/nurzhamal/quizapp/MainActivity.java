package kg.nurzhamal.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import kg.nurzhamal.quizapp.data.IQuizApiClient;
import kg.nurzhamal.quizapp.model.Question;
import kg.nurzhamal.quizapp.ui.adapter.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private MyViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(QuizApp.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        viewPager = findViewById(R.id.main_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        // viewPager.setPagingEnabled(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.main_nav).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.history_nav).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.settings_nav).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_nav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.history_nav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.settings_nav:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("kokoko", "main activity onResume: " + QuizApp.getInstance().getPrefs().getTheme());
        this.setTheme(QuizApp.getInstance().getPrefs().getTheme());
    }
}