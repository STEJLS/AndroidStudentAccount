package ru.kbbmstu.studentaccount.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kbbmstu.studentaccount.Activities.ArticleActivity;
import ru.kbbmstu.studentaccount.Activities.MainActivity;
import ru.kbbmstu.studentaccount.Models.Article;
import ru.kbbmstu.studentaccount.R;

public class StudentArticlesFragment extends Fragment {
    private ExpandableListView expandableListView;
    private TextView emptyList;

    private Context context;

    private ArrayList<Article> confirmed;
    private ArrayList<Article> unConfirmed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_articles, container, false);

        expandableListView = view.findViewById(R.id.expListView);
        emptyList = view.findViewById(R.id.emptyList);

        context = inflater.getContext();

        return view;
    }

    public void updateModel(ArrayList<Article> articles) {
        if (articles.size() == 0) {
            emptyList.setTextSize(20);
            emptyList.setText("У вас нет статей");
        }
        confirmed = new ArrayList<>();
        unConfirmed = new ArrayList<>();

        for (Article article : articles) {
            if (article.isConfirmed())
                confirmed.add(article);
            else
                unConfirmed.add(article);
        }


        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        Map<String, String> map;
        // коллекция для групп
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп
        if (confirmed.size() != 0) {
            map = new HashMap();
            map.put("groupName", "Подтвержденные статьи"); // время года
            groupDataList.add(map);

            // создаем коллекцию элементов для первой группы
            ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
            // заполняем список атрибутов для каждого элемента
            for (Article article : confirmed) {
                map = new HashMap<>();
                map.put("articleName", article.getName()); // название месяца
                childDataItemList.add(map);
            }
            // добавляем в коллекцию коллекций
            сhildDataList.add(childDataItemList);
        }

        if (unConfirmed.size() != 0) {
            map = new HashMap();
            map.put("groupName", "Неподтвержденные статьи"); // время года
            groupDataList.add(map);

            // создаем коллекцию элементов для второй группы
            ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
            for (Article article : unConfirmed) {
                map = new HashMap<>();
                map.put("articleName", article.getName());
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
                Toast.makeText(context, getArticlesArrayByGroupPosition(groupPosition).get(childPosition).getName(), Toast.LENGTH_LONG).show();

                Article article = getArticlesArrayByGroupPosition(groupPosition).get(childPosition);
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id", article.getId());
                intent.putExtra("name", article.getName());
                intent.putExtra("journal", article.getJournal());
                intent.putExtra("biblioRecord", article.getBiblioRecord());
                intent.putExtra("articlType", article.getArticlType());
                intent.putExtra("isFile", article.isFile());

                startActivity(intent);

                return true;
            }
        });

        //развернуть
//        int count = adapter.getGroupCount();
//        for (int position = 1; position <= count; position++)
//            expandableListView.expandGroup(position - 1);

    }

    private ArrayList<Article> getArticlesArrayByGroupPosition(int groupPosition) {
        if (confirmed.size() == 0) {
            return unConfirmed;
        }

        switch (groupPosition) {
            case 0:
                return confirmed;
            case 1:
                return unConfirmed;
        }

        return null;
    }
}
