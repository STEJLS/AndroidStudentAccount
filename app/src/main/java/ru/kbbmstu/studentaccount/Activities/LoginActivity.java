package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.kbbmstu.studentaccount.Models.User;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;
import ru.kbbmstu.studentaccount.Utils.Permission;

import static ru.kbbmstu.studentaccount.Utils.Internet.CheckConnection;
import static ru.kbbmstu.studentaccount.Utils.Permission.checkPermissions;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private EditText login;
    private EditText password;

    private SharedPreferences settings;
    private boolean permissionGranted;

    @Override
    public void onResume() {
        super.onResume();
        String token = settings.getString(getResources().getString(R.string.authTokenName), "");

        if (!token.equals("")) {
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

        if (!permissionGranted) {
            checkPermissions(this);
            return;
        }


        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        User loginModel = new User(login.getText().toString(), password.getText().toString());
        if (CheckConnection(this)) {
            HttpClient.Login(this, loginModel);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Permission.REQUEST_PERMISSION_WRITE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                    Toast.makeText(this, "Разрешения получены", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Необходимо дать разрешения", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public SharedPreferences getSettings() {
        return settings;
    }
}
