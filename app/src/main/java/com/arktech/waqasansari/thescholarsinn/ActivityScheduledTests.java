package com.arktech.waqasansari.thescholarsinn;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityScheduledTests extends AppCompatActivity {
    Calendar calendar;
    ListView lstTests;
    TextView txtDay;
    TextView txtTestDate;

    ProgressDialog dialog = null;

    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_tests);

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


        lstTests = (ListView) findViewById(R.id.lstTests);

        txtDay = (TextView) findViewById(R.id.txtDay);


        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myCalendar.add(Calendar.DATE, 1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String temp = sdf.format(myCalendar.getTime());
                try {
                    if(checkIfGreater(temp))
                        Toast.makeText(ActivityScheduledTests.this, "Wrong Date", Toast.LENGTH_SHORT).show();
                    else {
                        dialog = new ProgressDialog(ActivityScheduledTests.this);
                        dialog.setTitle("Please wait...");
                        dialog.setMessage("Fetching scheduled tests");
                        dialog.show();
                        myCalendar.add(Calendar.DATE, -1);
                        updateLabel(myCalendar);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        };
        txtTestDate = (TextView) findViewById(R.id.txtTestDate);
        txtTestDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivityScheduledTests.this, datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String date = format.format(calendar.getTime());



        new FetchScheduledTests(date).execute();

    }




    //Class Functions
    private void updateLabel(Calendar myCalendar) {
        SimpleDateFormat newFormat = new SimpleDateFormat("EEE, MMMM dd, yyyy", Locale.getDefault());
        txtTestDate.setText(newFormat.format(myCalendar.getTime()));
        newFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        new FetchScheduledTests(newFormat.format(myCalendar.getTime())).execute();
    }

    public boolean checkIfGreater(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        Date checkDate = dateFormat.parse(date);
        return currentDate.getTime() < checkDate.getTime();
    }

    public class FetchScheduledTests extends AsyncTask<Void, Void, String> {
        String date;

        public FetchScheduledTests(String date){
            this.date = date;
        }

        @Override
        protected String doInBackground(Void... params) {
            String response = "[]";

            ApiCalls apiCalls = ApiCalls.initializeAPI();

            while (response.equals("[]")) {
                try {
                    response = apiCalls.sendGet("ScheduledTests", date);
                    if(response.equals("[]")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ActivityScheduledTests.this, "No test on " + date, Toast.LENGTH_SHORT).show();
                            }
                        });
                        Date tmp = format.parse(date);
                        calendar.setTime(tmp);
                        calendar.add(Calendar.DATE, -1);
                        date = format.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(dialog != null)
                        dialog.dismiss();
                    return "error";
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SimpleDateFormat tempFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
            calendar = Calendar.getInstance();
            if(date.equals(format.format(calendar.getTime()))) {
                txtDay.setText("Today");
                txtTestDate.setText(tempFormat.format(calendar.getTime()));
            }
            calendar.add(Calendar.DATE, 1);
            if(date.equals(format.format(calendar.getTime()))) {
                txtDay.setText("Tomorrow");
                txtTestDate.setText(tempFormat.format(calendar.getTime()));
            } else {
                try {
                    tempFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date d = tempFormat.parse(date);
                    tempFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                    String dd = tempFormat.format(d);
                    txtDay.setText(dd);
                    tempFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
                    dd = tempFormat.format(d);
                    txtTestDate.setText(dd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            List<ClassScheduledTests> testsList = new ArrayList<>();
            ClassScheduledTests tests;

            try {
                JSONArray jsonArray = new JSONArray(s);
                if(jsonArray.length() > 1){
                    JSONObject object;
                    for(int i=0; i<jsonArray.length(); i++){
                        object = jsonArray.getJSONObject(i);
                        tests = new ClassScheduledTests();
                        tests.setClassName(object.getString("ClassName"));
                        tests.setSubject(object.getString("Subject"));
                        tests.setTeacher(object.getString("Teacher"));
                        tests.setTopic(object.getString("Topic"));
                        testsList.add(tests);
                    }

                    lstTests.setAdapter(new AdapterListScheduledTests(testsList, ActivityScheduledTests.this));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(dialog != null)
                dialog.dismiss();
        }
    }
}
