package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.FOSandRPDActivity;
import ru.kbbmstu.studentaccount.Models.FosAndRpd;

public final class FosAndRpdHandler extends CustomJsonHttpResponseHandler {

    public FosAndRpdHandler(AppCompatActivity context) {
        super(context);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            if (!response.getBoolean("Completed")) {
                Toast.makeText(context, response.getString("Message"), Toast.LENGTH_LONG).show();
                return;
            }
            if (context instanceof FOSandRPDActivity) {
                FOSandRPDActivity foSandRPDactivity = (FOSandRPDActivity) context;

                ArrayList<FosAndRpd> fosAndRpds = parseFosAndRpds(response.getJSONArray("Body"));

                foSandRPDactivity.updateModel(fosAndRpds);
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private ArrayList<FosAndRpd> parseFosAndRpds(JSONArray body) throws JSONException {
        ArrayList<FosAndRpd> result = new ArrayList<>(body.length());

        for (int i = 0; i < body.length(); i++) {
            FosAndRpd fosAndRpd = new FosAndRpd(
                    body.getJSONObject(i).getString("Name"),
                    body.getJSONObject(i).getInt("FosID"),
                    body.getJSONObject(i).getInt("RpdID")
            );

            result.add(fosAndRpd);
        }

        return result;
    }

}
