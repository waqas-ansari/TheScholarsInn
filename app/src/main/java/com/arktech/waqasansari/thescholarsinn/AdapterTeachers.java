package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WaqasAhmed on 6/15/2016.
 */
public class AdapterTeachers extends RecyclerView.Adapter<AdapterTeachers.TeacherViewHolder> {
    private List<ClassTeachers> teachersList;

    public AdapterTeachers(List<ClassTeachers> teachersList){
        this.teachersList = teachersList;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_teachers, parent, false);

        return new TeacherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        ClassTeachers teachers = teachersList.get(position);

        holder.txtName.setText(teachers.getTeacherName());
        holder.txtQualification.setText(teachers.getQualification());
        holder.txtSubject.setText(teachers.getSubject());
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }


    public static class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtQualification;
        TextView txtSubject;

        public TeacherViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtQualification = (TextView) itemView.findViewById(R.id.txtQual);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSub);
        }
    }
}
