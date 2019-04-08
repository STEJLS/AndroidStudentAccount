package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.UserInfo;

public final class StudentInfoHandler extends CustomJsonHttpResponseHandler {
    public StudentInfoHandler(AppCompatActivity context) {
        super(context);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            if (!response.getBoolean("Completed")) {
                Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
                if (response.getString("Message") == "Необходимо авторизоваться"){
                    if (context instanceof MainActivity) {
                        MainActivity mainActivity = (MainActivity) context;
                        mainActivity.getSettings().edit().putString("token", "").commit();
                        Intent intent = new Intent(mainActivity, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mainActivity.finish();
                        mainActivity.startActivity(intent);
                    }
                }
                return;
            }
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;

                UserInfo ui = new UserInfo(response.getJSONObject("Body"));
                mainActivity.UpdateStudentInfoFragment(ui);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
