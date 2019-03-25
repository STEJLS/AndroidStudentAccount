package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.R;

public final class LoginHandler extends CustomJsonHttpResponseHandler {
    private PersistentCookieStore cookieStore;

    public LoginHandler(AppCompatActivity context, PersistentCookieStore cookieStore) {
        super(context);
        this.cookieStore = cookieStore;
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
                loginActivity.getSettings().edit().putString("token", cookieStore.getCookies().get(0).getValue()).commit();

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
    public void onFinish() {
        if (dialog != null)
            dialog.dismiss();
    }
}
