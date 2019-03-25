package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.R;

public class CustomJsonHttpResponseHandler extends JsonHttpResponseHandler {
    protected AppCompatActivity context;
    protected ProgressDialog dialog;

    CustomJsonHttpResponseHandler(AppCompatActivity context){
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        Toast.makeText(context, R.string.onFailureResponseMessage, Toast.LENGTH_LONG).show();
    }
}
