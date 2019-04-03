package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.kbbmstu.studentaccount.R;

public class CustomActivity extends AppCompatActivity {
    private SharedPreferences settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(getResources().getString(R.string.Shared_preferences_file_name), Context.MODE_PRIVATE);
    }

    public SharedPreferences getSettings() {
        return settings;
    }
}
