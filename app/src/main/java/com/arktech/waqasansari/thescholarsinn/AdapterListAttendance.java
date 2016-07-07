package com.arktech.waqasansari.thescholarsinn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Linta Ansari on 7/3/2016.
 */
public class AdapterListAttendance extends BaseAdapter {
    String[] date;
    String[] present;
    LayoutInflater inflater;

    public AdapterListAttendance(String[] date, String[] present, Context context){
        this.date = date;
        this.present = present;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return date.length;
    }

    @Override
    public Object getItem(int position) {
        return date[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_list_attendance, null);
        }

        ((TextView) convertView.findViewById(R.id.txtAttendanceDate)).setText(date[position].substring(0, date[position].indexOf('T')));
        ((TextView) convertView.findViewById(R.id.txtPresent)).setText(present[position]);



        return convertView;
    }
}
