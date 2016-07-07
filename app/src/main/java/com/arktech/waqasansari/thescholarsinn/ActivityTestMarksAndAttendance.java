package com.arktech.waqasansari.thescholarsinn;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityTestMarksAndAttendance extends AppCompatActivity {
    ListView lstTestMarks;
    Spinner spnDate;
    EditText edtStudentId;

    Button btnShowMarks;
    Button btnShowAttendance;

    TextView txtClassName;
    TextView txtStudentName;
    TextView txtFatherName;

    String month;

    ProgressDialog dialog;

    LinearLayout linearLayout;

    ScrollView scrollAttendance;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_marks_and_attendance);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        inflater = getLayoutInflater();

        lstTestMarks = (ListView) findViewById(R.id.lstMarks);

        scrollAttendance = (ScrollView) findViewById(R.id.scrollAttendance);

        spnDate = (Spinner) findViewById(R.id.spnDate);
        edtStudentId = (EditText) findViewById(R.id.edtStudentId);

        btnShowMarks = (Button) findViewById(R.id.btnShowMarks);
        btnShowAttendance = (Button) findViewById(R.id.btnShowAttendence);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);


        int startMonth = 5;
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        String monthStrings[] = new String[currentMonth-startMonth+1];
        int count=0;
        for(int i=startMonth; i<=currentMonth; i++){
            monthStrings[count] = getMonthString(i) + " " + Calendar.getInstance().get(Calendar.YEAR);
            count++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(ActivityTestMarksAndAttendance.this, android.R.layout.simple_spinner_item, monthStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDate.setAdapter(adapter);

        btnShowMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtStudentId.getText().toString().isEmpty() || spnDate.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(ActivityTestMarksAndAttendance.this, "Enter Date/Student ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.setMessage("Fetching test marks");
                dialog.show();

                lstTestMarks.setVisibility(View.VISIBLE);
                scrollAttendance.setVisibility(View.GONE);

                String selectedText = spnDate.getSelectedItem().toString();
                String date = selectedText.substring(selectedText.indexOf(' ') + 1) +
                        "-" + getMonthInt(selectedText.substring(0, selectedText.indexOf(' ')))
                        + "-" + "01";
                new FetchTestMarks(date, edtStudentId.getText().toString()).execute();
            }
        });

        btnShowAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtStudentId.getText().toString().isEmpty() || spnDate.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(ActivityTestMarksAndAttendance.this, "Enter Date/Student ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.setMessage("Fetching attendance");
                dialog.show();

                lstTestMarks.setVisibility(View.GONE);
                scrollAttendance.setVisibility(View.VISIBLE);

                String selectedText = spnDate.getSelectedItem().toString();
                month = selectedText.substring(0, selectedText.indexOf(' '));
                String date = selectedText.substring(selectedText.indexOf(' ') + 1) +
                        "-" + getMonthInt(month)
                        + "-" + "01";
                new FetchAttendance(date, edtStudentId.getText().toString()).execute();
            }
        });




        dialog = new ProgressDialog(ActivityTestMarksAndAttendance.this);
        dialog.setTitle("Please wait...");
        dialog.setCancelable(false);


    }


    public void setTexts(String className, String studentName, String fatherName){
        txtClassName.setText("Class: " + className);
        txtStudentName.setText("Name: " + studentName);
        txtFatherName.setText("Father Name: " + fatherName);
    }

    private String getMonthString(int month){
        String monthString="";
        switch (month){
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
        }
        return monthString;
    }

    private String getMonthInt(String month){
        String monthInt = "00";
        switch (month) {
            case "January":
                monthInt = "01";
                break;
            case "February":
                monthInt = "02";
                break;
            case "March":
                monthInt  = "03";
                break;
            case "April":
                monthInt = "04";
                break;
            case "May":
                monthInt = "05";
                break;
            case "June":
                monthInt = "06";
                break;
            case "July":
                monthInt = "07";
                break;
            case "August":
                monthInt = "08";
                break;
            case "September":
                monthInt = "09";
                break;
            case "October":
                monthInt = "10";
                break;
            case "November":
                monthInt = "11";
                break;
            case "December":
                monthInt = "12";
                break;
        }
        return monthInt;
    }




    //Async Task classes
    private class FetchTestMarks extends AsyncTask<Void, Void, String> {
        String date;
        String studentId;


        public FetchTestMarks(String date, String studentId){
            this.date = date;
            this.studentId = studentId;
        }

        @Override
        protected String doInBackground(Void... params) {
            ApiCalls calls = ApiCalls.initializeAPI();
            try {
                return calls.sendGet("TestsMarks", date, studentId);
            } catch (Exception e) {
                e.printStackTrace();
                dialog.dismiss();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(! s.equals("error")){
                List<ClassTestMarks> testMarksList = new ArrayList<>();
                ClassTestMarks testMark;


                List<ClassSubjectWiseResult> subjectWiseResults = new ArrayList<>();
                ClassSubjectWiseResult result;

                List<String> distinctSubjects = new ArrayList<>();


                View view = inflater.inflate(R.layout.marks_list_header, null);

                txtClassName = (TextView) view.findViewById(R.id.txtClassName);
                txtStudentName = (TextView) view.findViewById(R.id.txtStudentName);
                txtFatherName = (TextView) view.findViewById(R.id.txtFatherName);

                try {
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 1){
                        JSONObject object;
                        for(int i=0; i<jsonArray.length(); i++){
                            object = jsonArray.getJSONObject(i);
                            testMark = new ClassTestMarks();
                            testMark.setClassName(object.getString("ClassName") + "-" + object.getString("SectionName") + " (" + object.getString("GroupName") + ")");
                            testMark.setStudentName(object.getString("StudentName"));
                            testMark.setFatherName(object.getString("FatherName"));


                            testMark.setSubject(object.getString("Subject"));

                            distinctSubjects.add(object.getString("Subject"));

                            testMark.setMarksObtained(object.getInt("MarksObtained"));
                            testMark.setMarksMax(object.getInt("MarksMax"));
                            testMark.setTestDate(object.getString("TestDate"));

                            testMarksList.add(testMark);
                        }

                        Set<String> stringSet = new HashSet<>();
                        stringSet.addAll(distinctSubjects);
                        distinctSubjects.clear();
                        distinctSubjects.addAll(stringSet);


                        List<List<ClassDetailedMarks>> detailedLists = new ArrayList<>();
                        List<ClassDetailedMarks> individualDetailList;
                        ClassDetailedMarks detailedMarks;

                        for (int i=0; i<distinctSubjects.size(); i++) {
                            individualDetailList = new ArrayList<>();
                            result = new ClassSubjectWiseResult();
                            for(int j=0; j<testMarksList.size(); j++) {

                                if(testMarksList.get(j).getSubject().equals(distinctSubjects.get(i))) {

                                    detailedMarks = new ClassDetailedMarks();

                                    result.setSubject(testMarksList.get(j).getSubject());
                                    result.setObtMarks(result.getObtMarks() + testMarksList.get(j).getMarksObtained());
                                    result.setMaxMarks(result.getMaxMarks() + testMarksList.get(j).getMarksMax());



                                    detailedMarks.setSubject(testMarksList.get(j).getSubject());
                                    detailedMarks.setObtMarks(testMarksList.get(j).getMarksObtained());
                                    detailedMarks.setMaxMarks(testMarksList.get(j).getMarksMax());
                                    detailedMarks.setTestDate(testMarksList.get(j).getTestDate());
                                    individualDetailList.add(detailedMarks);

                                }

                            }
                            subjectWiseResults.add(result);
                            detailedLists.add(individualDetailList);
                        }




                        setTexts(testMarksList.get(0).getClassName(), testMarksList.get(0).getStudentName(),
                                testMarksList.get(0).getFatherName());

                        lstTestMarks.addHeaderView(view);

                        lstTestMarks.setAdapter(new AdapterListTestMarks(subjectWiseResults, detailedLists, ActivityTestMarksAndAttendance.this));

                        dialog.dismiss();

                    } else {
                        Toast.makeText(ActivityTestMarksAndAttendance.this, "No result found\nInvalid ID or Date", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(ActivityTestMarksAndAttendance.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }
    }

    private class FetchAttendance extends AsyncTask<Void, Void, String>{
        private String date;
        private String studentId;

        public FetchAttendance(String date, String studentId) {
            this.date = date;
            this.studentId = studentId;
        }

        @Override
        protected String doInBackground(Void... params) {
            ApiCalls calls = ApiCalls.initializeAPI();
            try {
                return calls.sendGet("Attendance", date, studentId);
            } catch (Exception e) {
                e.printStackTrace();
                dialog.dismiss();
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



                            Animation animateLeft = AnimationUtils.loadAnimation(ActivityTestMarksAndAttendance.this, R.anim.rotate_left);
                            animateLeft.setFillAfter(true);

                            imgShowHide.clearAnimation();
                            imgShowHide.setAnimation(animateLeft);
                            imgShowHide.animate();
                        }
                        else {
                            lstAttendance.setVisibility(View.VISIBLE);

                            Animation animateRight = AnimationUtils.loadAnimation(ActivityTestMarksAndAttendance.this, R.anim.rotate_right);
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
                        ((TextView) view.findViewById(R.id.txtTotalAttendance)).setText(String.valueOf(count+totalCount));

                        lstAttendance.setAdapter(new AdapterListAttendance(date, present, ActivityTestMarksAndAttendance.this));

                        scrollAttendance.removeAllViews();
                        scrollAttendance.addView(view);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(ActivityTestMarksAndAttendance.this, "No result found\nInvalid ID or Date", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(ActivityTestMarksAndAttendance.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }
    }

}
