package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.Article;
import ru.kbbmstu.studentaccount.R;

public final class StudentArticlesHandler extends CustomJsonHttpResponseHandler {

    public StudentArticlesHandler(AppCompatActivity context) {
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
                return;
            }
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;

                ArrayList<Article> articles = parseArticles(response.getJSONArray("Body"));

                mainActivity.UpdateStudentArticlesFragment(articles);
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private ArrayList<Article> parseArticles(JSONArray body) throws JSONException {
        ArrayList<Article> result = new ArrayList<>(body.length());

        for (int i = 0; i < body.length(); i++) {
            Article article = new Article(
                    body.getJSONObject(i).getInt("ID"),
                    body.getJSONObject(i).getString("Name"),
                    body.getJSONObject(i).getString("Journal"),
                    body.getJSONObject(i).getString("BiblioRecord"),
                    body.getJSONObject(i).getString("ArticlType"),
                    body.getJSONObject(i).getBoolean("Confirmed"),
                    body.getJSONObject(i).getString("FileName"));
            result.add(article);
        }

        return result;
    }


    @Override
    public void onFinish() {
        super.onFinish();
        if (dialog != null)
            dialog.dismiss();
    }
}
