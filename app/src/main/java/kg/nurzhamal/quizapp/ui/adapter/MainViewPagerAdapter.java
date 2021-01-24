package kg.nurzhamal.quizapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kg.nurzhamal.quizapp.ui.history.HistoryFragment;
import kg.nurzhamal.quizapp.ui.main.MainFragment;
import kg.nurzhamal.quizapp.ui.settings.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 2:
                fragment = new SettingsFragment();
                break;
            case 1:
                fragment = new HistoryFragment();
                break;
            default:
                fragment = new MainFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
