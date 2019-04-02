package ru.kbbmstu.studentaccount.Utils;

import android.content.Context;
import android.os.Environment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import java.io.File;

import ru.kbbmstu.studentaccount.Activities.ArticleActivity;
import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.User;
import ru.kbbmstu.studentaccount.ResponseHandlers.ArticleResponseHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.LoginHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentArticlesHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentInfoHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentMarksHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentPracticesHandler;
import ru.kbbmstu.studentaccount.Urls;

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

    public static void Logout(final MainActivity context){
        setCookie(context);
        client.post(Urls.Logout, null, new JsonHttpResponseHandler());
    }

    public static void GetStudentInfo(final MainActivity context) {
        getWithCookie(context, Urls.StudentInfo, new StudentInfoHandler(context));
    }

    public static void GetStudentMarks(MainActivity context) {
        getWithCookie(context, Urls.StudentMarks, new StudentMarksHandler(context));
    }

    public static void GetStudentPractices(MainActivity context) {
        getWithCookie(context, Urls.StudentPractices, new StudentPracticesHandler(context));
    }

    public static void GetStudentArticles(MainActivity context) {
        getWithCookie(context, Urls.StudentArticles, new StudentArticlesHandler(context));
    }

    public static void GetStudentArticle(ArticleActivity context, String id, File file) {
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token=" + context.getSettings().getString("token", ""));
        client.get(Urls.GetArticle + id, null, new ArticleResponseHandler(context, file));
    }

    // Supporting functions
    private static void getWithCookie(MainActivity context, String url, JsonHttpResponseHandler handler) {
        setCookie(context);
        client.get(url, null, handler);
    }

    private static void setCookie(MainActivity context) {
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token="+context.getSettings().getString("token",""));
    }
}


