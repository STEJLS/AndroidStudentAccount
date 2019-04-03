package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.Article;
import ru.kbbmstu.studentaccount.R;

public class CheckAnswerResponseHandler extends CustomJsonHttpResponseHandler {
    public CheckAnswerResponseHandler(AppCompatActivity context) {
        super(context);
    }

    @Override
    public void onStart() {
        dialog.setMessage(context.getResources().getString(R.string.waitingForData));
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            if (!response.getBoolean("Completed")) {
                Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
                //return;
            }

            Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("from", 1);
            context.setResult(context.RESULT_OK, intent);
            context.finish();


        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onFinish() {
        super.onFinish();
        if (dialog != null)
            dialog.dismiss();
    }
}
