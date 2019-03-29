package ru.kbbmstu.studentaccount.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ru.kbbmstu.studentaccount.MarkAdapter;
import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.R;

public class StudentMarksFragment extends Fragment {
    private ListView marksList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_marks, container, false);
        marksList = view.findViewById(R.id.list);

        return view;
    }

    public void updateModel(ArrayList<Mark> marks) {
        ArrayAdapter<Mark> adapter = new MarkAdapter(this.getContext(),
                R.layout.fragment_student_marks, marks);
        marksList.setAdapter(adapter);
    }
}
