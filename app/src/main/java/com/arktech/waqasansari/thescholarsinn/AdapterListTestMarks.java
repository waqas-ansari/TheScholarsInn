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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Linta Ansari on 6/29/2016.
 */
public class AdapterListTestMarks extends BaseAdapter {
    List<ClassSubjectWiseMarks> subjectWiseResultList;
    List<List<ClassDetailedMarks>> detailedMarksList;
    LayoutInflater inflater;
    Context context;

    public AdapterListTestMarks(List<ClassSubjectWiseMarks> subjectWiseResultList, List<List<ClassDetailedMarks>> detailedMarksList, Context context){
        this.subjectWiseResultList = subjectWiseResultList;
        this.detailedMarksList = detailedMarksList;
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
        TextView txtObtOutOfTotal = (TextView) convertView.findViewById(R.id.txtObtOutOfTotal);
        TextView txtPercentage = (TextView) convertView.findViewById(R.id.txtPercentage);
        final ImageView imgShowHide = (ImageView) convertView.findViewById(R.id.imgShowHide);



        final LinearLayout lstDetailedMarks = (LinearLayout) convertView.findViewById(R.id.lstSeparateMarks);
        lstDetailedMarks.removeAllViews();


        ClassSubjectWiseMarks result = subjectWiseResultList.get(position);

        txtSubject.setText(result.getSubject());
        txtObtOutOfTotal.setText(String.valueOf(result.getObtMarks()) + "/" + String.valueOf(result.getMaxMarks()));

        float percentage = ( (float)result.getObtMarks() / (float)result.getMaxMarks() ) * 100.0f;
        double roundOff = Math.round(percentage * 100.0) / 100.0;

        txtPercentage.setText(String.valueOf(roundOff) + "%");

        List<ClassDetailedMarks> marksList = detailedMarksList.get(position);
        for(ClassDetailedMarks marks : marksList) {
            View view = inflater.inflate(R.layout.custom_list_separate_marks, null);
            ((TextView) view.findViewById(R.id.txtObtOutOfTotal)).setText(String.valueOf(marks.getObtMarks()) + "/" + String.valueOf(marks.getMaxMarks()));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date date = null;
            try {
                date = format.parse(marks.getTestDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            format = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
            ((TextView) view.findViewById(R.id.txtDate)).setText(format.format(date));

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
