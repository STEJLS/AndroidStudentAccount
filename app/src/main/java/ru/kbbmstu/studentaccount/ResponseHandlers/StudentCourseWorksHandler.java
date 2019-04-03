package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.CourseWork;
import ru.kbbmstu.studentaccount.Models.Practice;
import ru.kbbmstu.studentaccount.R;

public final class StudentCourseWorksHandler extends CustomJsonHttpResponseHandler {

    public StudentCourseWorksHandler(AppCompatActivity context) {
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

                ArrayList<CourseWork> courseWorks = parseCourseWork(response.getJSONArray("Body"));

                mainActivity.UpdateStudentCourseWorkFragment(courseWorks);
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private ArrayList<CourseWork> parseCourseWork(JSONArray body) throws JSONException {
        ArrayList<CourseWork> result = new ArrayList<>(body.length());

        for (int i = 0; i < body.length(); i++) {
            CourseWork courseWork = new CourseWork(
                    body.getJSONObject(i).getInt("ID"),
                    body.getJSONObject(i).getString("FIO"),
                    body.getJSONObject(i).getString("Head"),
                    body.getJSONObject(i).getString("Subject"),
                    body.getJSONObject(i).getString("Team"),
                    body.getJSONObject(i).getJSONObject("Theme").getString("String"),
                    body.getJSONObject(i).getJSONObject("Theme").getBoolean("Valid"),
                    body.getJSONObject(i).getBoolean("Confirmed"),
                    body.getJSONObject(i).getInt("Rating"),
                    body.getJSONObject(i).getInt("Semester")
            );

            result.add(courseWork);
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
