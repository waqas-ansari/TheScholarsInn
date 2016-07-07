package com.arktech.waqasansari.thescholarsinn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Linta Ansari on 6/29/2016.
 */
public class AdapterListTestMarks extends BaseAdapter {
    List<ClassSubjectWiseResult> subjectWiseResultList;
    List<List<ClassDetailedMarks>> detailedMarksList;
    LayoutInflater inflater;
    Context context;

    public AdapterListTestMarks(List<ClassSubjectWiseResult> subjectWiseResultList, List<List<ClassDetailedMarks>> detailedMarksList, Context context){
        this.subjectWiseResultList = subjectWiseResultList;
        this.detailedMarksList = detailedMarksList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AdapterListTestMarks(List<ClassSubjectWiseResult> subjectWiseResultList, Context context){
        this.subjectWiseResultList = subjectWiseResultList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return subjectWiseResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return subjectWiseResultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.custom_list_marks, null);

        TextView txtSubject = (TextView) convertView.findViewById(R.id.txtSubject);
        TextView txtObtMarks = (TextView) convertView.findViewById(R.id.txtObtMarks);
        TextView txtMaxMarks = (TextView) convertView.findViewById(R.id.txtMaxMarks);
        final ImageView imgShowHide = (ImageView) convertView.findViewById(R.id.imgShowHide);



        final LinearLayout lstDetailedMarks = (LinearLayout) convertView.findViewById(R.id.lstSeparateMarks);
        lstDetailedMarks.removeAllViews();


        ClassSubjectWiseResult result = subjectWiseResultList.get(position);

        txtSubject.setText(result.getSubject());
        txtObtMarks.setText(String.valueOf(result.getObtMarks()));
        txtMaxMarks.setText(String.valueOf(result.getMaxMarks()));

        List<ClassDetailedMarks> marksList = detailedMarksList.get(position);
        for(ClassDetailedMarks marks : marksList) {
            View view = inflater.inflate(R.layout.custom_list_separate_marks, null);
            ((TextView) view.findViewById(R.id.txtDateOfTest)).setText(marks.getTestDate().substring(0, marks.getTestDate().indexOf('T')));
            ((TextView) view.findViewById(R.id.txtObtMarks)).setText(String.valueOf(marks.getObtMarks()));
            ((TextView) view.findViewById(R.id.txtMaxMarks)).setText(String.valueOf(marks.getMaxMarks()));

            lstDetailedMarks.addView(view);
        }

        imgShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lstDetailedMarks.getVisibility() == View.VISIBLE) {
                    lstDetailedMarks.setVisibility(View.GONE);



                    Animation animateLeft = AnimationUtils.loadAnimation(context, R.anim.rotate_left);
                    animateLeft.setFillAfter(true);

                    imgShowHide.clearAnimation();
                    imgShowHide.setAnimation(animateLeft);
                    imgShowHide.animate();
                }
                else {
                    lstDetailedMarks.setVisibility(View.VISIBLE);

                    Animation animateRight = AnimationUtils.loadAnimation(context, R.anim.rotate_right);
                    animateRight.setFillAfter(true);

                    imgShowHide.clearAnimation();
                    imgShowHide.setAnimation(animateRight);
                    imgShowHide.animate();
                }
            }
        });

        return convertView;
    }
}
