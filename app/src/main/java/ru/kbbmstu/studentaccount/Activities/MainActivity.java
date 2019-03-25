package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.kbbmstu.studentaccount.PagerAdapter;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private EditText test;
    private Button logoutButton;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        settings = getSharedPreferences(getResources().getString(R.string.Shared_preferences_file_name), Context.MODE_PRIVATE);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        initTab();
    }

    @Override
    protected void onResume() {
        HttpClient.GetUserInfo(this);
        super.onResume();
    }

    public SharedPreferences getSettings() {
        return settings;
    }

    public EditText getTest() {
        return test;
    }

    private void initTab(){
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount()-1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
