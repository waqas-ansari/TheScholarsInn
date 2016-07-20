package com.arktech.waqasansari.thescholarsinn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Linta Ansari on 7/16/2016.
 */
public class AdapterTimeTable extends RecyclerView.Adapter<AdapterTimeTable.TimeTableViewHolder> {
    List<ClassTimeTable> timeTableList;
    LayoutInflater inflater;

    public AdapterTimeTable(List<ClassTimeTable> timeTableList, Context context) {
        this.timeTableList = timeTableList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public TimeTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_time_table, parent, false);
        return new TimeTableViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TimeTableViewHolder holder, int position) {
        ClassTimeTable timeTable = timeTableList.get(position);

        holder.txtClass.setText(timeTable.getClassName());

        holder.testDaysLayout.removeAllViews();
        holder.timeTableLayout.removeAllViews();

        String[] strings = timeTable.getTestDays().trim().split("\n");

        View view;
        for(int i=1; i < strings.length; i++) {
            view = inflater.inflate(R.layout.view_time_table, null);
            String temp[] = strings[i].trim().split("-");
            if(temp.length > 1) {
                ((TextView) view.findViewById(R.id.txtDay)).setText(getCompleteDay(temp[0]));
                if(temp[1].charAt(0) == ' ')
                    temp[1] = temp[1].substring(1);
                ((TextView) view.findViewById(R.id.txtSubject)).setText(temp[1]);
                holder.testDaysLayout.addView(view);
            }
        }
        String[] timeTableText = timeTable.getTimeTable().trim().split("\n");
        holder.txtTimeTableDays.setText(timeTableText[2]);
        for(int j=3; j < timeTableText.length; j++) {
            view = inflater.inflate(R.layout.view_time_table, null);
            String[] temp = timeTableText[j].trim().split(" ");
            if(temp.length > 1) {
                ((TextView) view.findViewById(R.id.txtDay)).setText(temp[0] + temp[1] + temp[2] + " pm");
                ((TextView) view.findViewById(R.id.txtSubject)).setText(temp[3]);
                holder.timeTableLayout.addView(view);
            }
        }
    }

    @Override
    public int getItemCount() {
        return timeTableList.size();
    }

    public static class TimeTableViewHolder extends RecyclerView.ViewHolder {
        TextView txtClass, txtTimeTableDays;
        LinearLayout testDaysLayout, timeTableLayout;

        public TimeTableViewHolder(View itemView) {
            super(itemView);
            txtClass = (TextView) itemView.findViewById(R.id.txtClass);
            txtTimeTableDays = (TextView) itemView.findViewById(R.id.txtTimeTableDays);
            testDaysLayout = (LinearLayout) itemView.findViewById(R.id.testDayLayout);
            timeTableLayout = (LinearLayout) itemView.findViewById(R.id.timeTableLayout);
        }

    }

    private String getCompleteDay(String day) {
        switch (day) {
            case "Mon":
                return "Monday";
            case "Tue":
                return "Tuesday";
            case "Wed":
                return "Wednesday";
            case "Thu":
                return "Thursday";
            case "Fri":
                return "Friday";
        }
        return null;
    }

}