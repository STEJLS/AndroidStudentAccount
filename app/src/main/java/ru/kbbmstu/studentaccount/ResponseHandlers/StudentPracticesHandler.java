package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.Practice;

public final class StudentPracticesHandler extends CustomJsonHttpResponseHandler {
    public StudentPracticesHandler(AppCompatActivity context) {
        super(context);
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

                ArrayList<Practice> practices = parsePractices(response.getJSONArray("Body"));

                mainActivity.UpdateStudentPracticesFragment(practices);
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private ArrayList<Practice> parsePractices(JSONArray body) throws JSONException {
        ArrayList<Practice> result = new ArrayList<>(body.length());

        for (int i = 0; i < body.length(); i++) {
            Practice practice = new Practice(
                    body.getJSONObject(i).getInt("Semester"),
                    body.getJSONObject(i).getString("Name"),
                    body.getJSONObject(i).getString("Head"),
                    body.getJSONObject(i).getString("Company"),
                    body.getJSONObject(i).getString("Date"),
                    body.getJSONObject(i).getInt("Rating")
            );

            result.add(practice);
        }

        return result;
    }
}
