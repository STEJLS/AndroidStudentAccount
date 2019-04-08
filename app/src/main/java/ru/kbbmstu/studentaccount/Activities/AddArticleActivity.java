package ru.kbbmstu.studentaccount.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import ru.kbbmstu.studentaccount.Models.Article;
import ru.kbbmstu.studentaccount.OnClickListeners.SelectArticleOnClickListner;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;
import ru.kbbmstu.studentaccount.Utils.UriUtils;

public class AddArticleActivity extends CustomActivity {
    private Button selectFileBtn;
    private Button addArticleBtn;
    private TextView selectedFileName;
    private Spinner type;
    private EditText name;
    private EditText journal;
    private EditText link;

    private File article;
    private AddArticleActivity currentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        currentContext = this;

        selectFileBtn = findViewById(R.id.selectFileBtn);
        selectFileBtn.setOnClickListener(new SelectArticleOnClickListner(this));

        selectedFileName = findViewById(R.id.addArticleSelectedFileName);


        name = findViewById(R.id.addArticleName);
        journal = findViewById(R.id.addArticleJournal);
        link = findViewById(R.id.addArticleLink);
        type = findViewById(R.id.addArticleType);
        addArticleBtn = findViewById(R.id.addArticle);

        addArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "";
                if (article != null) {
                    fileName = article.getName();
                }
                Article newArticle = new Article(name.getText().toString(), journal.getText().toString(), link.getText().toString(), type.getSelectedItem().toString(), fileName);

                if (validateArticle(newArticle)) {
                    try {
                        HttpClient.AddArticle(currentContext, newArticle, article);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private boolean validateArticle(Article newArticle) {
        if (newArticle.getName().length() < 10) {
            Toast.makeText(this, "Название статьи должно быть не менее 10 символов", Toast.LENGTH_LONG).show();
            return false;
        }
        if (newArticle.getJournal().length() < 10) {
            Toast.makeText(this, "Название журнала должно быть не менее 10 символов", Toast.LENGTH_LONG).show();
            return false;
        }
        if (newArticle.getBiblioRecord().length() < 10) {
            Toast.makeText(this, "Длина библиографической ссылки должна быть не менее 10 символов", Toast.LENGTH_LONG).show();
            return false;
        }
        if (newArticle.getArticlType().isEmpty()) {
            Toast.makeText(this, "Укажите тип статьи", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SelectArticleOnClickListner.PICKFILE_RESULT_CODE:
                if (resultCode == -1) {
                    String filePath = UriUtils.getAbsolutePathFromUri(this, data.getData());
                    article = new File(filePath);
                    selectedFileName.setText(article.getName());
                }
                break;
        }
    }
}
