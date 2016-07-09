package com.arktech.waqasansari.thescholarsinn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WaqasAhmed on 6/15/2016.
 */
public class AdapterListTeachers extends BaseAdapter {
    private List<ClassTeachers> teachersList;
    LayoutInflater inflater;

    public AdapterListTeachers(List<ClassTeachers> teachersList, Context context){
        this.teachersList = teachersList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return teachersList.size();
    }

    @Override
    public Object getItem(int position) {
        return teachersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.custom_list_teachers, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtQualification = (TextView) convertView.findViewById(R.id.txtQual);
        TextView txtSubject = (TextView) convertView.findViewById(R.id.txtSub);

        txtName.bringToFront();
        //ImageView imgTeacher = (ImageView) convertView.findViewById(R.id.imgTeacher);



        ClassTeachers teachers = teachersList.get(position);

        txtName.setText(teachers.getTeacherName());
        txtQualification.setText(teachers.getQualification());
        txtSubject.setText(teachers.getSubject());
        //imgTeacher.setImageResource(teachers_image[position]);

        return convertView;
    }
}
