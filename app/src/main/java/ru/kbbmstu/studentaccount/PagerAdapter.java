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
    private List<Fragment> fragmentList;
    public PagerAdapter(FragmentManager fragmentManager, int tabsNumber) {
        super(fragmentManager);
        fragmentList = fragmentManager.getFragments();
        if (fragmentList.size() == 0) {
            fragmentList = new ArrayList<>();
            fragmentList.add(new SimpleFragment());
            fragmentList.add(new SimpleFragment());
            fragmentList.add(new SimpleFragment());
            fragmentList.add(new SimpleFragment());
            fragmentList.add(new SimpleFragment());
        }
        this.tabsNumber = fragmentList.size();

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
