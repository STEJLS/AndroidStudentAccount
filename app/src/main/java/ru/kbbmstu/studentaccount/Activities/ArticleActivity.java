package ru.kbbmstu.studentaccount.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ru.kbbmstu.studentaccount.R;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle intent) {
        super.onCreate(intent);
        setContentView(R.layout.activity_article);

        TextView name = findViewById(R.id.articleName);
        TextView journal = findViewById(R.id.articleJournal);
        TextView biblioRecord = findViewById(R.id.articleBiblioRecord);
        TextView articlType = findViewById(R.id.articleArticlType);
        Button download = findViewById(R.id.articleDownload);

        Intent params = getIntent();

        String name1 = params.getStringExtra("name");
        String journal1 = params.getStringExtra("journal");


        name.setText(params.getStringExtra("name"));
        journal.setText(params.getStringExtra("journal"));
        biblioRecord.setText(params.getStringExtra("biblioRecord"));
        articlType.setText(params.getStringExtra("articlType"));

        if (params.getBooleanExtra("isFile", false)) {
            download.setText("скачать " + String.valueOf(params.getIntExtra("id", 0)));
        } else {
            download.setText("нет файла");
        }

    }
}
