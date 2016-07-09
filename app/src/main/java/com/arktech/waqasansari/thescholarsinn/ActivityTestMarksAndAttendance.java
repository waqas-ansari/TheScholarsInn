package com.arktech.waqasansari.thescholarsinn;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    Spinner spnDate;
    EditText edtStudentId;

    ImageButton btnShow;

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



        spnDate = (Spinner) findViewById(R.id.spnDate);
        edtStudentId = (EditText) findViewById(R.id.edtStudentId);

        btnShow = (ImageButton) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtStudentId.getText().toString().isEmpty() || spnDate.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(ActivityTestMarksAndAttendance.this, "Enter Date/Student ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                edtStudentId.clearFocus();

                String selectedText = spnDate.getSelectedItem().toString();
                String date = selectedText.substring(selectedText.indexOf(' ') + 1) +
                        "-" + getMonthInt(selectedText.substring(0, selectedText.indexOf(' ')))
                        + "-" + "01";
                String month = getMonthString(Integer.valueOf(getMonthInt(selectedText.substring(0, selectedText.indexOf(' ')))));
                Bundle bundle = new Bundle();
                bundle.putString("date", date);
                bundle.putString("id", edtStudentId.getText().toString());
                bundle.putString("month", month);


                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
                tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

                ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
                PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), bundle);

                viewPager.setAdapter(pagerAdapter);

                tabLayout.setupWithViewPager(viewPager);
            }
        });



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


    }

    public static String getMonthString(int month){
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

}
