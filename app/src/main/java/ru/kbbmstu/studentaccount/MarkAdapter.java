package ru.kbbmstu.studentaccount;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.kbbmstu.studentaccount.Models.Mark;

public final class MarkAdapter extends ArrayAdapter<Mark> {

    private LayoutInflater inflater;
    private int layout;
    private List<Mark> states;

    public MarkAdapter(Context context, int resource, List<Mark> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.fragment_student_mark, parent, false);

        TextView subject = (TextView) view.findViewById(R.id.subject);
        TextView rating = (TextView) view.findViewById(R.id.rating);
        TextView passType = (TextView) view.findViewById(R.id.passtype);
        TextView repass = (TextView) view.findViewById(R.id.repass);

        Mark mark = states.get(position);

        if (mark.getSubject().startsWith("Семестр")) {
            subject.setText(mark.getSubject());
            subject.setTypeface(null, Typeface.BOLD_ITALIC);
            subject.setTextColor(Color.BLACK);
            subject.setTextSize(16);
            rating.setText("");
            passType.setText("");
            repass.setText("");
            if (view.findViewById(R.id.list).getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                FrameLayout.LayoutParams p = (FrameLayout.LayoutParams) view.findViewById(R.id.list).getLayoutParams();
                p.setMargins(0, 50, 0, 0);
                view.requestLayout();
            }
        } else {
            if (mark.getSemester() == 0) {
                subject.setText("Предмет");
                subject.setTypeface(null, Typeface.BOLD_ITALIC);
                subject.setTextColor(Color.BLACK);
                rating.setText("Оценка");
                rating.setTypeface(null, Typeface.BOLD_ITALIC);
                rating.setTextColor(Color.BLACK);
                passType.setText("Тип");
                passType.setTypeface(null, Typeface.BOLD_ITALIC);
                passType.setTextColor(Color.BLACK);
                repass.setText("Пересдача");
                repass.setTypeface(null, Typeface.BOLD_ITALIC);
                repass.setTextColor(Color.BLACK);

            } else {
                subject.setText(mark.getSubject());
                rating.setText(String.valueOf(mark.getRating()));
                passType.setText(mark.getPassType());
                if (mark.isRepass()) {
                    repass.setText("да");

                } else {
                    repass.setText("нет");
                }
            }
        }

        return view;
    }
}
