package com.arktech.waqasansari.thescholarsinn;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class ActivityTestMarksAndAttendance extends AppCompatActivity {
    Spinner spnDate;
    Spinner spnYear;
    EditText edtStudentId;

    FloatingActionButton btnShow;

    String monthStrings[] = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

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
        spnYear = (Spinner) findViewById(R.id.spnYear);
        edtStudentId = (EditText) findViewById(R.id.edtStudentId);

        btnShow = (FloatingActionButton) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtStudentId.getText().toString().isEmpty() || spnDate.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(ActivityTestMarksAndAttendance.this, "Enter Date/Student ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                edtStudentId.clearFocus();

                String date = spnYear.getSelectedItem().toString() + "-" + getMonthInt(spnDate.getSelectedItem().toString()) + "-01";
                String month = spnDate.getSelectedItem().toString();
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




        ArrayAdapter adapter = new ArrayAdapter<>(ActivityTestMarksAndAttendance.this,
                android.R.layout.simple_spinner_item,
                monthStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDate.setAdapter(adapter);
        spnDate.setSelection(Calendar.getInstance().get(Calendar.MONTH));

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String year[] = {String.valueOf(currentYear-1), String.valueOf(currentYear), String.valueOf(currentYear+1)};
        adapter = new ArrayAdapter<>(ActivityTestMarksAndAttendance.this,
                android.R.layout.simple_spinner_item,
                year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnYear.setAdapter(adapter);
        spnYear.setSelection(1);
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
