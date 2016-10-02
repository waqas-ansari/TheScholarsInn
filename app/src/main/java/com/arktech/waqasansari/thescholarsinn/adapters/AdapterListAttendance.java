package com.arktech.waqasansari.thescholarsinn.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arktech.waqasansari.thescholarsinn.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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


        ((TextView) convertView.findViewById(R.id.txtPresent)).setText(present[position]);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date dateTemp = null;
        try {
            dateTemp = format.parse(date[position]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
        ((TextView) convertView.findViewById(R.id.txtAttendanceDate)).setText(format.format(dateTemp));
        return convertView;
    }
}
