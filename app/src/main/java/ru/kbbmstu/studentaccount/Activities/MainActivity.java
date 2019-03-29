package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ru.kbbmstu.studentaccount.Fragments.StudentInfoFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentMarksFragment;
import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.Models.UserInfo;
import ru.kbbmstu.studentaccount.PagerAdapter;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

import android.support.design.widget.TabLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (  item.getItemId()){
            case R.id.shedule:
                Toast.makeText(this, "shedule", Toast.LENGTH_LONG).show();
                break;
            case R.id.addArticle:
                Toast.makeText(this, "addArticle", Toast.LENGTH_LONG).show();
                break;
            case R.id.RdpFos:
                Toast.makeText(this, "RdpFos", Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                HttpClient.Logout(this);
                settings.edit().putString("token", "").commit();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        settings = getSharedPreferences(getResources().getString(R.string.Shared_preferences_file_name), Context.MODE_PRIVATE);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    protected void onResume() {
        initTab();
        HttpClient.GetStudentInfo(this);
        HttpClient.GetStudentMarks(this);
        super.onResume();
    }

    public SharedPreferences getSettings() {
        return settings;
    }

    private void initTab(){
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
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

    private StudentInfoFragment getStudentInfoFragment() {
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            if (pagerAdapter.getItem(i) instanceof StudentInfoFragment) {
                return (StudentInfoFragment) pagerAdapter.getItem(i);
            }
        }
        return null;
    }

    private StudentMarksFragment getStudentMarksFragment() {
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            if (pagerAdapter.getItem(i) instanceof StudentMarksFragment) {
                return (StudentMarksFragment) pagerAdapter.getItem(i);
            }
        }
        return null;
    }

    public void UpdateStudentInfoFragment(UserInfo model) {
        getStudentInfoFragment().updateModel(model);
    }

    public void UpdateStudentMarksFragment(ArrayList<Mark> marks) {
        getStudentMarksFragment().updateModel(marks);
    }
}
