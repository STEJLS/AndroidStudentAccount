package ru.kbbmstu.studentaccount.ArrayAdapters;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import ru.kbbmstu.studentaccount.Activities.FOSandRPDActivity;
import ru.kbbmstu.studentaccount.Models.FosAndRpd;
import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.Models.Practice;
import ru.kbbmstu.studentaccount.R;
import ru.kbbmstu.studentaccount.Utils.HttpClient;

public final class FosAndRpdAdapter extends ArrayAdapter<FosAndRpd> {
    private LayoutInflater inflater;
    private int layout;
    private List<FosAndRpd> fosAndRpds;
    private FOSandRPDActivity currentContext;

    public FosAndRpdAdapter(Context context, int resource, List<FosAndRpd> fosAndRpds) {
        super(context, resource, fosAndRpds);

        this.fosAndRpds = fosAndRpds;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.currentContext = (FOSandRPDActivity) context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.fos_and_rdp_item, parent, false);

        TextView name = (TextView) view.findViewById(R.id.fosAndRpdName);
        Button fos = (Button) view.findViewById(R.id.fos);
        Button rpd = (Button) view.findViewById(R.id.rpd);

        final FosAndRpd fosAndRpd = fosAndRpds.get(position);

        name.setText(fosAndRpd.getName());

        fos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "ФОС ";
                String fileSeparator = System.getProperty("file.separator");

                if (fosAndRpd.getName().length() > 35) {
                    fileName += fosAndRpd.getName().substring(0, 14) + ".pdf";
                } else {
                    fileName += fosAndRpd.getName() + ".pdf";
                }

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + fileSeparator + fileName);
                HttpClient.GetDocument(currentContext, String.valueOf(fosAndRpd.getFosID()), file);
            }
        });

        rpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "РПД ";
                String fileSeparator = System.getProperty("file.separator");

                if (fosAndRpd.getName().length() > 35) {
                    fileName += fosAndRpd.getName().substring(0, 14) + ".pdf";
                } else {
                    fileName += fosAndRpd.getName() + ".pdf";
                }

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + fileSeparator + fileName);
                HttpClient.GetDocument(currentContext, String.valueOf(fosAndRpd.getRpdID()), file);
            }
        });

        return view;
    }
}