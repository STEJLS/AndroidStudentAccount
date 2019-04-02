package ru.kbbmstu.studentaccount.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

public class ArticleActivity extends AppCompatActivity {
    private SharedPreferences settings;

    public SharedPreferences getSettings() {
        return settings;
    }

    @Override
    protected void onCreate(Bundle intent) {
        super.onCreate(intent);
        setContentView(R.layout.activity_article);
        final ArticleActivity ttt = this;

        settings = getSharedPreferences(getResources().getString(R.string.Shared_preferences_file_name), Context.MODE_PRIVATE);

        TextView name = findViewById(R.id.articleName);
        TextView journal = findViewById(R.id.articleJournal);
        TextView biblioRecord = findViewById(R.id.articleBiblioRecord);
        TextView articlType = findViewById(R.id.articleArticlType);
        Button download = findViewById(R.id.articleDownload);

        final Intent params = getIntent();

        name.setText(params.getStringExtra("name"));
        journal.setText(params.getStringExtra("journal"));
        biblioRecord.setText(params.getStringExtra("biblioRecord"));
        articlType.setText(params.getStringExtra("articlType"));
        final String fileName = params.getStringExtra("fileName");
        if (!fileName.isEmpty()) {
            download.setText("скачать");
        } else {
            download.setVisibility(View.GONE);
        }

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileSeparator = System.getProperty("file.separator");
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + fileSeparator + fileName + ".pdf");
                HttpClient.GetStudentArticle(ttt, params.getStringExtra("id"), file);
            }
        });
    }
}
