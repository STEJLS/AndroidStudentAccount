package ru.kbbmstu.studentaccount.Utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import java.io.File;

import ru.kbbmstu.studentaccount.Activities.CustomActivity;
import ru.kbbmstu.studentaccount.Activities.LoginActivity;
import ru.kbbmstu.studentaccount.Models.User;
import ru.kbbmstu.studentaccount.ResponseHandlers.FileResponseHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.CheckAnswerResponseHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.FosAndRpdHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.LoginHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentArticlesHandler;
import ru.kbbmstu.studentaccount.ResponseHandlers.StudentCourseWorksHandler;
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

    public static void Logout(final CustomActivity context) {
        setCookie(context);
        client.post(Urls.Logout, null, new JsonHttpResponseHandler());
    }

    public static void GetStudentInfo(final CustomActivity context) {
        getWithCookie(context, Urls.StudentInfo, new StudentInfoHandler(context));
    }

    public static void GetStudentMarks(CustomActivity context) {
        getWithCookie(context, Urls.StudentMarks, new StudentMarksHandler(context));
    }

    public static void GetStudentPractices(CustomActivity context) {
        getWithCookie(context, Urls.StudentPractices, new StudentPracticesHandler(context));
    }

    public static void GetStudentArticles(CustomActivity context) {
        getWithCookie(context, Urls.StudentArticles, new StudentArticlesHandler(context));
    }

    public static void GetStudentCourseWorks(CustomActivity context) {
        getWithCookie(context, Urls.StudentCourseWorks, new StudentCourseWorksHandler(context));
    }

    public static void GetStudentArticle(CustomActivity context, String id, File file) {
        GetFile(context, Urls.GetArticle + id, file);
    }

    public static void GetDocument(CustomActivity context, String id, File file) {
        GetFile(context, Urls.GetDocumnet + id, file);
    }

    public static void SetCourseWorkTheme(CustomActivity context, String id, String theme) {
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token=" + context.getSettings().getString("token", ""));
        RequestParams params = new RequestParams();
        params.put("theme", theme);
        params.put("id", id);

        client.post(Urls.SetCourseWorkTheme, params, new CheckAnswerResponseHandler(context));
    }

    public static void GetFosAndRpd(CustomActivity context) {
        getWithCookie(context, Urls.FosAndRpd, new FosAndRpdHandler(context));
    }

    // Supporting functions
    private static void getWithCookie(CustomActivity context, String url, JsonHttpResponseHandler handler) {
        setCookie(context);
        client.get(url, null, handler);
    }

    private static void setCookie(CustomActivity context) {
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token="+context.getSettings().getString("token",""));
    }

    private static void GetFile(CustomActivity context, String url, File file) {
        client.removeHeader("Cookie");
        client.addHeader("Cookie", "token=" + context.getSettings().getString("token", ""));
        client.get(url, null, new FileResponseHandler(context, file));
    }
}


