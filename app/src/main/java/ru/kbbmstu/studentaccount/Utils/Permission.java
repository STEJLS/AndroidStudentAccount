package ru.kbbmstu.studentaccount.Utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import ru.kbbmstu.studentaccount.Activities.LoginActivity;

public final class Permission {
    public static final int REQUEST_PERMISSION_WRITE = 123;

    public static boolean checkPermissions(LoginActivity context) {

        if (!isExternalStorageReadable() || !isExternalStorageWriteable()) {
            Toast.makeText(context, "Внешнее хранилище не доступно", Toast.LENGTH_LONG).show();
            return false;
        }
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        }
        return true;
    }

    // проверяем, доступно ли внешнее хранилище для чтения и записи
    private static boolean isExternalStorageWriteable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    // проверяем, доступно ли внешнее хранилище хотя бы только для чтения
    private static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

}
