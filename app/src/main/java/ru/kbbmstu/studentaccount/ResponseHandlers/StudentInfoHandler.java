package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.UserInfo;
import ru.kbbmstu.studentaccount.R;

public final class StudentInfoHandler extends CustomJsonHttpResponseHandler {
    public StudentInfoHandler(AppCompatActivity context) {
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
            if (!response.getBoolean("Completed")){
                Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
                return;
            }
            if (context instanceof MainActivity){
                MainActivity mainActivity = (MainActivity)context;

                UserInfo ui = new UserInfo(response.getJSONObject("Body"));
                mainActivity.UpdateStudentInfoFragment(ui);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (dialog != null)
            dialog.dismiss();
    }
}
