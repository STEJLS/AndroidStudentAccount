package ru.kbbmstu.studentaccount.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kbbmstu.studentaccount.Activities.ArticleActivity;
import ru.kbbmstu.studentaccount.Activities.CourseWorkActivity;
import ru.kbbmstu.studentaccount.Models.Article;
import ru.kbbmstu.studentaccount.Models.CourseWork;
import ru.kbbmstu.studentaccount.R;

public final class StudentCourseWorksFragment extends Fragment {
    private final String confirmedTitle = "Подтвержденные курсовые";
    private final String unConfirmedTitle = "Укажите темы курсовых работ";
    private final String waitingForVerifTitle = "Ожидают подтверждения";

    private ExpandableListView expandableListView;
    private TextView emptyList;
    private TextView title;

    private Context context;

    private ArrayList<CourseWork> confirmed;
    private ArrayList<CourseWork> unConfirmed;
    private ArrayList<CourseWork> waitingForVerif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_articles, container, false);

        expandableListView = view.findViewById(R.id.expListView);
        emptyList = view.findViewById(R.id.emptyList);
        title = view.findViewById(R.id.articleTitle);

        context = inflater.getContext();

        return view;
    }

    public void updateModel(ArrayList<CourseWork> courseWorks) {
        if (courseWorks.size() == 0) {
            emptyList.setTextSize(20);
            emptyList.setText("У вас нет статей");
        } else {
            title.setText("Курсовые работы");
        }
        confirmed = new ArrayList<>();
        unConfirmed = new ArrayList<>();
        waitingForVerif = new ArrayList<>();

        for (CourseWork work : courseWorks) {
            if (work.isConfirmed())
                confirmed.add(work);
            else if (work.getTheme().isEmpty()) {
                unConfirmed.add(work);
            } else {
                waitingForVerif.add(work);
            }
        }


        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        Map<String, String> map;
        // коллекция для групп
        final ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп
        if (confirmed.size() != 0) {
            map = new HashMap();
            map.put("groupName", confirmedTitle); // время года
            groupDataList.add(map);

            // создаем коллекцию элементов для первой группы
            ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
            // заполняем список атрибутов для каждого элемента
            for (CourseWork work : confirmed) {
                map = new HashMap<>();
                map.put("articleName", work.getSubject()); // название месяца
                childDataItemList.add(map);
            }
            // добавляем в коллекцию коллекций
            сhildDataList.add(childDataItemList);
        }

        if (unConfirmed.size() != 0) {
            map = new HashMap();
            map.put("groupName", unConfirmedTitle); // время года
            groupDataList.add(map);

            // создаем коллекцию элементов для второй группы
            ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
            for (CourseWork work : unConfirmed) {
                map = new HashMap<>();
                map.put("articleName", work.getSubject());
                childDataItemList.add(map);
            }
            сhildDataList.add(childDataItemList);
        }

        if (waitingForVerif.size() != 0) {
            map = new HashMap();
            map.put("groupName", waitingForVerifTitle); // время года
            groupDataList.add(map);

            // создаем коллекцию элементов для второй группы
            ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
            for (CourseWork work : waitingForVerif) {
                map = new HashMap<>();
                map.put("articleName", work.getSubject());
                childDataItemList.add(map);
            }
            сhildDataList.add(childDataItemList);
        }

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{"articleName"};
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                context, groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, сhildDataList, android.R.layout.simple_list_item_1,
                childFrom, childTo);


        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String accessibilityClassName = parent.getTransitionName();
                CourseWork work = getWorksArrayByGroupPosition(groupDataList.get(groupPosition).get("groupName")).get(childPosition);

                Intent intent = new Intent(context, CourseWorkActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id", String.valueOf(work.getId()));
                intent.putExtra("rating", String.valueOf(work.getRating()));
                intent.putExtra("semester", String.valueOf(work.getSemester()));
                intent.putExtra("head", work.getHead());
                intent.putExtra("subject", work.getSubject());
                intent.putExtra("theme", work.getTheme());
                intent.putExtra("confirmed", work.isConfirmed());

                startActivityForResult(intent, 1);

                return true;
            }
        });

        //развернуть
//        int count = adapter.getGroupCount();
//        for (int position = 1; position <= count; position++)
//            expandableListView.expandGroup(position - 1);

    }

    private ArrayList<CourseWork> getWorksArrayByGroupPosition(String groupName) {

        switch (groupName) {
            case confirmedTitle:
                return confirmed;
            case unConfirmedTitle:
                return unConfirmed;
            case waitingForVerifTitle:
                return waitingForVerif;
        }

        return null;
    }
}
