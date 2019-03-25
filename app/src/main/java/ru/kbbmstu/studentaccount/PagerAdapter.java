package ru.kbbmstu.studentaccount;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.kbbmstu.studentaccount.Fragments.SimpleFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentInfo;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int tabsNumber;
    private final List<Fragment> fragmentList = new ArrayList<>();

    public PagerAdapter(FragmentManager fragmentManager, int tabsNumber) {
        super(fragmentManager);
        this.tabsNumber = tabsNumber;
//        fragmentList.add(new StudentInfo());
        fragmentList.add(new SimpleFragment());
        fragmentList.add(new SimpleFragment());
        fragmentList.add(new SimpleFragment());
        fragmentList.add(new SimpleFragment());
        fragmentList.add(new SimpleFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
