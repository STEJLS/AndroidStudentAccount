package ru.kbbmstu.studentaccount.ArrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.Models.Practice;
import ru.kbbmstu.studentaccount.R;

public final class PracticeAdapter extends ArrayAdapter<Practice> {
    private LayoutInflater inflater;
    private int layout;
    private List<Practice> practices;

    public PracticeAdapter(Context context, int resource, List<Practice> practices) {
        super(context, resource, practices);
        this.practices = practices;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.fragment_student_practice, parent, false);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView head = (TextView) view.findViewById(R.id.head);
        TextView company = (TextView) view.findViewById(R.id.company);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView rating = (TextView) view.findViewById(R.id.rating);
        TextView semester = (TextView) view.findViewById(R.id.semester);

        Practice practice = practices.get(position);

        name.setText(practice.getName());
        head.setText(practice.getHead());
        company.setText(practice.getCompany());
        date.setText(practice.getDate());
        rating.setText(String.valueOf(practice.getRating()));
        semester.setText("Семестр " + String.valueOf(practice.getSemester()));

        return view;
    }
}
