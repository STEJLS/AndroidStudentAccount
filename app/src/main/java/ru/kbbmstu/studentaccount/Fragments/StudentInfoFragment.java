package ru.kbbmstu.studentaccount.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.kbbmstu.studentaccount.Models.UserInfo;
import ru.kbbmstu.studentaccount.R;

public class StudentInfoFragment extends Fragment {
    TextView firstName;
    TextView secondName;
    TextView patronymic;
    TextView faculty;
    TextView department;
    TextView field;
    TextView profile;
    TextView group;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);
        firstName = view.findViewById(R.id.firstName);
        secondName = view.findViewById(R.id.secondName);
        patronymic = view.findViewById(R.id.patronymic);
        faculty = view.findViewById(R.id.faculty);
        department = view.findViewById(R.id.department);
        field = view.findViewById(R.id.field);
        profile = view.findViewById(R.id.profile);
        group = view.findViewById(R.id.group);
        return view;
    }

    public void updateModel(UserInfo model) {
        setFIO(model.getFullName());
        faculty.setText(model.getFaculty());
        department.setText(model.getDepartment());
        field.setText(model.getField());
        profile.setText(model.getFieldProfile());
        group.setText(model.getGroup());
    }

    private void setFIO(String fio) {
        String[] splitFio = fio.split("\\s+");
        if (splitFio.length < 2) {
            firstName.setText(fio);
            return;
        } else {
            firstName.setText(splitFio[0]);
            secondName.setText(splitFio[1]);
        }

        if (splitFio.length == 3) {
            patronymic.setText(splitFio[2]);
        }
    }

}