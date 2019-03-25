package ru.kbbmstu.studentaccount.Utils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;
import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.User;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.ResponseHandlers.LoginHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentInfoHandler;
import ru.kbbmstu.studentaccount.Urls;

import static ru.kbbmstu.studentaccount.Utils.Internet.CheckConnection;

public final class HttpClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void Login(final LoginActivity context, User user){
        RequestParams params = new RequestParams();
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
        final PersistentCookieStore cookieStore = new PersistentCookieStore(context);

        client.setCookieStore(cookieStore);
        client.post(Urls.Login, params, new LoginHandler(context, cookieStore));
    }

    public static void GetUserInfo(final MainActivity context){
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token="+context.getSettings().getString("token",""));
        client.get(Urls.StudentInfo, null, new StudentInfoHandler(context));
    }

    public static void Logout(final MainActivity context){
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token="+context.getSettings().getString("token",""));
        client.post(Urls.Logout, null, new JsonHttpResponseHandler());
    }
}


