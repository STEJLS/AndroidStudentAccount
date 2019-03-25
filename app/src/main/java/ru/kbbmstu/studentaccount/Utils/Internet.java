package ru.kbbmstu.studentaccount.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ru.kbbmstu.studentaccount.R;

public final class Internet {
    public static boolean CheckConnection(AppCompatActivity context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }
        Toast.makeText(context, R.string.needInternet, Toast.LENGTH_LONG).show();
        return false;
    }
}
