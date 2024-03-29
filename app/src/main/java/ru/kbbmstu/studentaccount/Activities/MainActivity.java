package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ru.kbbmstu.studentaccount.Fragments.StudentArticlesFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentCourseWorksFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentInfoFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentMarksFragment;
import ru.kbbmstu.studentaccount.Fragments.StudentPracticesFragment;
import ru.kbbmstu.studentaccount.Models.Article;
import ru.kbbmstu.studentaccount.Models.CourseWork;
import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.Models.Practice;
import ru.kbbmstu.studentaccount.Models.UserInfo;
import ru.kbbmstu.studentaccount.PagerAdapter;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

import android.support.design.widget.TabLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CustomActivity {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        switch (data.getIntExtra("from", 0)) {
            case 1:
                HttpClient.GetStudentCourseWorks(this); // Установление темы курсовой
            case 2:
                HttpClient.GetStudentArticles(this); // Установление статей
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (  item.getItemId()){
            case R.id.shedule:
                startActivity(new Intent(this, SheduleActivity.class));
                break;
            case R.id.addArticle:

                startActivityForResult(new Intent(this, AddArticleActivity.class), 2);
                break;
            case R.id.RdpFos:
                startActivity(new Intent(this, FOSandRPDActivity.class));
                break;
            case R.id.logout:
                HttpClient.Logout(this);
                getSettings().edit().putString("token", "").commit();
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

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    protected void onResume() {
        if (pagerAdapter == null) {
            initTab();
            HttpClient.GetStudentInfo(this);
            HttpClient.GetStudentMarks(this);
            HttpClient.GetStudentPractices(this);
            HttpClient.GetStudentArticles(this);
            HttpClient.GetStudentCourseWorks(this);
        }
        super.onResume();
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

    private StudentPracticesFragment getStudentPracticesFragment() {
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            if (pagerAdapter.getItem(i) instanceof StudentPracticesFragment) {
                return (StudentPracticesFragment) pagerAdapter.getItem(i);
            }
        }
        return null;
    }

    private StudentArticlesFragment getStudentArticlesFragment() {
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            if (pagerAdapter.getItem(i) instanceof StudentArticlesFragment) {
                return (StudentArticlesFragment) pagerAdapter.getItem(i);
            }
        }
        return null;
    }

    private StudentCourseWorksFragment getStudentCourseWorksFragment() {
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            if (pagerAdapter.getItem(i) instanceof StudentCourseWorksFragment) {
                return (StudentCourseWorksFragment) pagerAdapter.getItem(i);
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

    public void UpdateStudentPracticesFragment(ArrayList<Practice> practices) {
        getStudentPracticesFragment().updateModel(practices);
    }


    public void UpdateStudentArticlesFragment(ArrayList<Article> articles) {
        getStudentArticlesFragment().updateModel(articles);
    }

    public void UpdateStudentCourseWorkFragment(ArrayList<CourseWork> courseWorks) {
        getStudentCourseWorksFragment().updateModel(courseWorks);
    }
}
