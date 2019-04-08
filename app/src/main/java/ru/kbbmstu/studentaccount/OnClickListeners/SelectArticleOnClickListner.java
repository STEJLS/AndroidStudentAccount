package ru.kbbmstu.studentaccount.OnClickListeners;

import android.content.Intent;
import android.view.View;

import ru.kbbmstu.studentaccount.Activities.AddArticleActivity;

public final class SelectArticleOnClickListner implements View.OnClickListener {
    public static final int PICKFILE_RESULT_CODE = 123;
    AddArticleActivity context;

    public SelectArticleOnClickListner(AddArticleActivity context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("application/*");
        Intent intent = Intent.createChooser(chooseFile, "Choose a file");
        context.startActivityForResult(intent, PICKFILE_RESULT_CODE);
    }
}
