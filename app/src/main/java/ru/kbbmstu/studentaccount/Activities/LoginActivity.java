package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.kbbmstu.studentaccount.Models.User;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

import static ru.kbbmstu.studentaccount.Utils.Internet.CheckConnection;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private EditText login;
    private EditText password;

    private SharedPreferences settings;

    @Override
    public void onResume() {
        super.onResume();
        String token = settings.getString(getResources().getString(R.string.authTokenName),"");

        if (token != ""){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.finish();
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = findViewById(R.id.loginBtn);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        settings = getSharedPreferences(getResources().getString(R.string.Shared_preferences_file_name), Context.MODE_PRIVATE);

        loginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        User loginModel = new User(login.getText().toString(), password.getText().toString());
        if (CheckConnection(this)){
            HttpClient.Login(this, loginModel);
        }
    }

    public SharedPreferences getSettings() {
        return settings;
    }
}
