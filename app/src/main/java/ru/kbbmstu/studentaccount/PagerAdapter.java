package ru.kbbmstu.studentaccount;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.kbbmstu.studentaccount.Fragments.StudentInfoFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentMarksFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentPracticesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int tabsNumber;
    private List<Fragment> fragmentList;
    public PagerAdapter(FragmentManager fragmentManager, int tabsNumber) {
        super(fragmentManager);
        fragmentList = fragmentManager.getFragments();
        if (fragmentList.size() == 0) {
            fragmentList = new ArrayList<>();
            fragmentList.add(new StudentInfoFragment());
            fragmentList.add(new StudentMarksFragment());
            fragmentList.add(new StudentPracticesFragment());
            fragmentList.add(new StudentInfoFragment());
            fragmentList.add(new StudentInfoFragment());
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
