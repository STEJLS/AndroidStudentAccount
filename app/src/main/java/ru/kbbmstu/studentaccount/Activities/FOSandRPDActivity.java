package ru.kbbmstu.studentaccount.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.kbbmstu.studentaccount.ArrayAdapters.FosAndRpdAdapter;
import ru.kbbmstu.studentaccount.ArrayAdapters.PracticeAdapter;
import ru.kbbmstu.studentaccount.Models.FosAndRpd;
import ru.kbbmstu.studentaccount.Models.Practice;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

public class FOSandRPDActivity extends CustomActivity {
    private ListView practiceList;
    private TextView emptyTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fosand_rpdactivity);
        practiceList = findViewById(R.id.listFosAndRpd);
        emptyTitle = findViewById(R.id.emptyListFosAndRpd);

        HttpClient.GetFosAndRpd(this);
    }

    public void updateModel(ArrayList<FosAndRpd> fosAndRpds) {
        if (!fosAndRpds.isEmpty()) {
            emptyTitle.setVisibility(View.GONE);
        }

        ArrayAdapter<FosAndRpd> adapter = new FosAndRpdAdapter(this,
                R.layout.fos_and_rdp_item, fosAndRpds);
        practiceList.setAdapter(adapter);
    }
}
