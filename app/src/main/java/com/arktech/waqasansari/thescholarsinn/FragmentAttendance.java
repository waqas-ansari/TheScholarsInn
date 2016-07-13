package com.arktech.waqasansari.thescholarsinn;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAttendance extends Fragment {
    LayoutInflater inflater;

    ScrollView scrollAttendance;

    TextView txtClassName;
    TextView txtStudentName;
    TextView txtFatherName;

    ProgressBar progressBar;

    String month;

    public FragmentAttendance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        scrollAttendance = (ScrollView) view.findViewById(R.id.scrollAttendance);


        progressBar = (ProgressBar) view.findViewById(R.id.progress);

        month = getArguments().getString("month");

        new FetchAttendance(getArguments().getString("date"), getArguments().getString("id")).execute();

        return view;
    }


    private class FetchAttendance extends AsyncTask<Void, Void, String> {
        private String date;
        private String studentId;

        public FetchAttendance(String date, String studentId) {
            this.date = date;
            this.studentId = studentId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            ApiCalls calls = ApiCalls.initializeAPI();
            try {
                return calls.sendGet("Attendance", date, studentId);
            } catch (final Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(! s.equals("error")) {

                View view = inflater.inflate(R.layout.view_attendance, null);
                txtClassName = (TextView) view.findViewById(R.id.txtClassName);
                txtStudentName = (TextView) view.findViewById(R.id.txtStudentName);
                txtFatherName = (TextView) view.findViewById(R.id.txtFatherName);

                final ExpandedListView lstAttendance = (ExpandedListView) view.findViewById(R.id.lstAttendance);

                final ImageView imgShowHide = (ImageView) view.findViewById(R.id.imgShowHide);
                imgShowHide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lstAttendance.getVisibility() == View.VISIBLE) {
                            lstAttendance.setVisibility(View.GONE);

                            Animation animateLeft = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_left);
                            animateLeft.setFillAfter(true);

                            imgShowHide.clearAnimation();
                            imgShowHide.setAnimation(animateLeft);
                            imgShowHide.animate();
                        }
                        else {
                            lstAttendance.setVisibility(View.VISIBLE);

                            Animation animateRight = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_right);
                            animateRight.setFillAfter(true);

                            imgShowHide.clearAnimation();
                            imgShowHide.setAnimation(animateRight);
                            imgShowHide.animate();
                        }
                    }
                });



                try {
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 0) {
                        JSONObject object = jsonArray.getJSONObject(0);
                        setTexts(object.getString("ClassName") + "-" + object.getString("SectionName") + " (" + object.getString("GroupName") + ")",
                                object.getString("StudentName"), object.getString("FatherName"));

                        String[] date = new String[jsonArray.length()];
                        String[] present = new String[jsonArray.length()];
                        int count = 0;
                        int totalCount=0;
                        for (int i=0; i<jsonArray.length(); i++){
                            object = jsonArray.getJSONObject(i);
                            date[i] = object.getString("Date");
                            present[i] = object.getString("Attendance");
                            if(present[i].equals("A"))
                                totalCount++;
                            if(present[i].equals("P"))
                                count++;
                        }

                        ((TextView) view.findViewById(R.id.txtMonth)).setText(month);
                        ((TextView) view.findViewById(R.id.txtTotalPresents)).setText(String.valueOf(count));
                        ((TextView) view.findViewById(R.id.txtTotalAttendance)).setText(String.valueOf(count + totalCount));

                        lstAttendance.setAdapter(new AdapterListAttendance(date, present, getContext()));

                        scrollAttendance.removeAllViews();
                        scrollAttendance.addView(view);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getContext(), "No result found\nInvalid ID or Date", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    public void setTexts(String className, String studentName, String fatherName){
        txtClassName.setText(className);
        txtStudentName.setText(studentName);
        txtFatherName.setText(fatherName);
    }

}
