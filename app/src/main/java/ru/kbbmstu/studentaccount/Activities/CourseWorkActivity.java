package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

public class CourseWorkActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private CourseWorkActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_work);

        settings = getSharedPreferences(getResources().getString(R.string.Shared_preferences_file_name), Context.MODE_PRIVATE);
        context = this;

        TextView head = findViewById(R.id.courseworkHead);
        TextView subject = findViewById(R.id.courseworkSubject);
        TextView theme = findViewById(R.id.courseworkTheme);
        final TextView editTheme = findViewById(R.id.courseworkEditTheme);
        TextView rating = findViewById(R.id.courseworkRating);
        TextView semester = findViewById(R.id.courseworkSemester);
        Button setTheme = findViewById(R.id.courseworkSetTheme);
        LinearLayout showLinear = findViewById(R.id.courseworkLinearText);
        LinearLayout editLinear = findViewById(R.id.courseworkLinearEditText);


        final Intent params = getIntent();

        head.setText(params.getStringExtra("head"));
        subject.setText(params.getStringExtra("subject"));
        theme.setText(params.getStringExtra("theme"));
        rating.setText(params.getStringExtra("rating"));
        semester.setText(params.getStringExtra("semester"));

        if (!params.getStringExtra("theme").isEmpty()) {
            setTheme.setVisibility(View.GONE);
            editLinear.setVisibility(View.GONE);
        } else {
            showLinear.setVisibility(View.GONE);
        }

//        intent.putExtra("id", String.valueOf(work.getId()));


        setTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTheme = editTheme.getText().toString();

                if (newTheme.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Необходимо указать тему", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "вы указали " + newTheme, Toast.LENGTH_LONG).show();

                    HttpClient.SetCourseWorkTheme(context, params.getStringExtra("id"), newTheme);
                }
            }
        });


    }

    public SharedPreferences getSettings() {
        return settings;
    }
}
