package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.R;

public final class LoginHandler extends JsonHttpResponseHandler {
    private PersistentCookieStore cookieStore;
    protected AppCompatActivity context;
    protected ProgressDialog dialog;

    public LoginHandler(AppCompatActivity context, PersistentCookieStore cookieStore) {
        super();
        this.context = context;
        this.cookieStore = cookieStore;
        this.dialog = new ProgressDialog(context);
    }

    @Override
    public void onStart() {
        dialog.setMessage(context.getResources().getString(R.string.waitingForAuthorization));
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response)  {
        try {
            if (!response.getBoolean("Completed")){
                Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
                return;
            }

            if (context instanceof LoginActivity){
                LoginActivity loginActivity = (LoginActivity)context;
                String value = cookieStore.getCookies().get(0).getValue();
                loginActivity.getSettings().edit().putString("token", cookieStore.getCookies().get(0).getValue()).apply();

                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.finish();
                context.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        Toast.makeText(context, R.string.onFailureResponseMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinish() {
        if (dialog != null)
            dialog.dismiss();
    }
}
