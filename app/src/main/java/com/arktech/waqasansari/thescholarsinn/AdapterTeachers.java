package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by WaqasAhmed on 6/15/2016.
 */
public class AdapterTeachers extends RecyclerView.Adapter<AdapterTeachers.TeachersViewHolder> {
    private List<ClassTeachers> teachersList;

    private int teachers_image[] = {
            R.drawable.atif_ata,
            R.drawable.empty_icon,
            R.drawable.yousuf_ata,
            R.drawable.hussain_ali,
            R.drawable.waqas_ansari,
            R.drawable.fahad_nadeem,
            R.drawable.empty_icon,
            R.drawable.empty_icon
    };

    public AdapterTeachers(List<ClassTeachers> teachersList){
        this.teachersList = teachersList;
    }

    @Override
    public TeachersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_teachers, parent, false);

        return new TeachersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TeachersViewHolder holder, int position) {
        ClassTeachers teachers = teachersList.get(position);
        holder.txtName.setText(teachers.getTeacherName());
        holder.txtQualification.setText(teachers.getQualification());
        holder.txtInstitute.setText(teachers.getInstitute());
        holder.txtSubject.setText(teachers.getSubject());
        holder.txtEmail.setText(teachers.getEmail());
        holder.imgTeacher.setImageResource(teachers_image[position]);
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    public class TeachersViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtQualification;
        TextView txtInstitute;
        TextView txtSubject;
        TextView txtEmail;
        ImageView imgTeacher;


        public TeachersViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtQualification = (TextView) itemView.findViewById(R.id.txtQual);
            txtInstitute = (TextView) itemView.findViewById(R.id.txtInst);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSub);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            imgTeacher = (ImageView) itemView.findViewById(R.id.imgTeacher);
        }
    }
}
