package com.arktech.waqasansari.thescholarsinn.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arktech.waqasansari.thescholarsinn.support_classes.ClassScheduledTests;
import com.arktech.waqasansari.thescholarsinn.R;

import java.util.List;

/**
 * Created by Linta Ansari on 6/28/2016.
 */
public class AdapterListScheduledTests extends BaseAdapter {
    private List<ClassScheduledTests> testsList;
    LayoutInflater inflater;

    public AdapterListScheduledTests(List<ClassScheduledTests> testsList, Context context){
        this.testsList = testsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return testsList.size();
    }

    @Override
    public Object getItem(int position) {
        return testsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.custom_list_scheduled_test, null);

        ClassScheduledTests test = testsList.get(position);
        if(test != null){
            ((TextView) convertView.findViewById(R.id.txtClassName)).setText(test.getClassName());
            ((TextView) convertView.findViewById(R.id.txtSubject)).setText(test.getSubject());
            ((TextView) convertView.findViewById(R.id.txtTopic)).setText(test.getTopic());
            ((TextView) convertView.findViewById(R.id.txtTeacher)).setText(test.getTeacher());
        }
        return convertView;
    }
}
