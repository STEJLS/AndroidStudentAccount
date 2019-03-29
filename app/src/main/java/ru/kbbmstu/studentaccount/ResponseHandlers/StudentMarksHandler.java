package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.Models.UserInfo;
import ru.kbbmstu.studentaccount.R;

public final class StudentMarksHandler extends CustomJsonHttpResponseHandler {

    public StudentMarksHandler(AppCompatActivity context) {
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

                ArrayList<Mark> marks = parseMarks(response.getJSONArray("Body"));

                mainActivity.UpdateStudentMarksFragment(prepareMarks(marks));
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (dialog != null)
            dialog.dismiss();
    }

    private ArrayList<Mark> parseMarks(JSONArray marks) throws JSONException {
        ArrayList<Mark> result = new ArrayList<Mark>(marks.length());

        for (int i = 0; i < marks.length(); i++) {
            JSONObject jsonMark = marks.getJSONObject(i);

            result.add(new Mark(jsonMark.getString("Subject"),
                    jsonMark.getInt("Semester"),
                    jsonMark.getInt("Rating"),
                    getPassType(jsonMark.getInt("PassType")),
                    jsonMark.getBoolean("Repass")));
        }

        return result;
    }

    private String getPassType(int i) {
        switch (i) {
            case 0:
                return "экзамен";
            case 1:
                return "д.зачет";
            default:
                return "зачет";
        }
    }

    private ArrayList<Mark> prepareMarks(ArrayList<Mark> marks) {
        marks.sort(new Comparator<Mark>() {
            @Override
            public int compare(Mark x, Mark y) {
                return Integer.compare(x.getSemester(), y.getSemester());
            }
        });

        marks.add(0, getMarkHeader());
        marks.add(0, new Mark("Семестр 1", 1, 0, "нет", true));
        int semester = 1;

        for (int i = 3; i < marks.size(); i++) {
            if (marks.get(i).getSemester() != semester) {
                marks.add(i, getMarkHeader());
                marks.add(i, new Mark("Семестр " + String.valueOf(++semester), 1, 0, "нет", true));
                i += 2;
            }
        }
        return marks;
    }

    private Mark getMarkHeader() {
        return new Mark("", 0, 0, "нет", true);
    }
}
