package ru.kbbmstu.studentaccount.ResponseHandlers;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;
import ru.kbbmstu.studentaccount.Activities.CustomActivity;
import ru.kbbmstu.studentaccount.R;

public final class FileResponseHandler extends FileAsyncHttpResponseHandler {
    protected CustomActivity context;
    protected ProgressDialog dialog;

    public FileResponseHandler(CustomActivity context, File f) {
        super(f);
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    @Override
    public void onStart() {
        super.onStart();

        dialog.setMessage(context.getResources().getString(R.string.waitingForFile));
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, File file) {

    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (dialog != null)
            dialog.dismiss();
        Toast.makeText(context, "Файл загружен", Toast.LENGTH_LONG).show();
    }
}
