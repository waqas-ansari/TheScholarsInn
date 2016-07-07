package com.arktech.waqasansari.thescholarsinn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Linta Ansari on 7/2/2016.
 */
public class AdapterListDetailedMarks extends BaseAdapter {
    List<ClassDetailedMarks> detailedMarksList;
    LayoutInflater inflater;

    public AdapterListDetailedMarks(List<ClassDetailedMarks> detailedMarksList, Context context){
        this.detailedMarksList = detailedMarksList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return detailedMarksList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailedMarksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.custom_list_separate_marks, null);

        TextView txtTestDate = (TextView) convertView.findViewById(R.id.txtDateOfTest);
        TextView txtObtMarks = (TextView) convertView.findViewById(R.id.txtObtMarks);
        TextView txtMaxMarks = (TextView) convertView.findViewById(R.id.txtMaxMarks);

        ClassDetailedMarks detailedMarks = detailedMarksList.get(position);

        txtTestDate.setText(detailedMarks.getTestDate().substring(0, detailedMarks.getTestDate().indexOf('T')));
        txtObtMarks.setText(String.valueOf(detailedMarks.getObtMarks()));
        txtMaxMarks.setText(String.valueOf(detailedMarks.getMaxMarks()));

        return convertView;
    }
}
