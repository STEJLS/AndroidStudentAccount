package ru.kbbmstu.studentaccount.OnClickListeners;

import android.content.Intent;
import android.view.View;

import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

public final class LogoutOnClickListener implements View.OnClickListener {
    private MainActivity context;

    public LogoutOnClickListener(MainActivity context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        HttpClient.Logout(context);
        context.getSettings().edit().putString("token", "").apply();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.finish();
        context.startActivity(intent);
    }
}
