package ru.kbbmstu.studentaccount.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ru.kbbmstu.studentaccount.ArrayAdapters.MarkAdapter;
import ru.kbbmstu.studentaccount.ArrayAdapters.PracticeAdapter;
import ru.kbbmstu.studentaccount.Models.Mark;
import ru.kbbmstu.studentaccount.Models.Practice;
import ru.kbbmstu.studentaccount.R;

public final class StudentPracticesFragment extends Fragment {
    private ListView practiceList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_practices, container, false);
        practiceList = view.findViewById(R.id.list);

        return view;
    }

    public void updateModel(ArrayList<Practice> practices) {
        ArrayAdapter<Practice> adapter = new PracticeAdapter(this.getContext(),
                R.layout.fragment_student_practices, practices);
        practiceList.setAdapter(adapter);
    }
}
