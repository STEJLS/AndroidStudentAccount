package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CheckAnswerResponseHandler extends CustomJsonHttpResponseHandler {
    private int code;

    public CheckAnswerResponseHandler(AppCompatActivity context, int code) {
        super(context);
        this.code = code;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            if (!response.getBoolean("Completed")) {
                Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("from", code);
            context.setResult(context.RESULT_OK, intent);
            context.finish();


        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
