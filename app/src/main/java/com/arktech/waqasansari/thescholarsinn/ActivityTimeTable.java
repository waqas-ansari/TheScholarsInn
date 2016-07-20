package com.arktech.waqasansari.thescholarsinn;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityTimeTable extends AppCompatActivity {
    ProgressBar progressBar;
    LinearLayout linearLayout;
    Spinner spnClass;

    List<String> classList;
    List<ClassTimeTable> timeTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

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


        linearLayout = (LinearLayout) findViewById(R.id.timeTableCardList);
        spnClass = (Spinner) findViewById(R.id.spnClass);

        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != 0) {

                    View itemView = getLayoutInflater().inflate(R.layout.card_time_table, null);

                    TextView txtClass = (TextView) itemView.findViewById(R.id.txtClass);
                    TextView txtTimeTableDays = (TextView) itemView.findViewById(R.id.txtTimeTableDays);
                    LinearLayout testDaysLayout = (LinearLayout) itemView.findViewById(R.id.testDayLayout);
                    LinearLayout timeTableLayout = (LinearLayout) itemView.findViewById(R.id.timeTableLayout);

                    ClassTimeTable timeTable = timeTableList.get(position-1);

                    txtClass.setText(timeTable.getClassName());

                    testDaysLayout.removeAllViews();
                    timeTableLayout.removeAllViews();

                    String[] strings = timeTable.getTestDays().trim().split("\n");

                    View tempView;
                    for(int i=1; i < strings.length; i++) {
                        tempView = getLayoutInflater().inflate(R.layout.view_time_table, null);
                        String temp[] = strings[i].trim().split("-");
                        if(temp.length > 1) {
                            ((TextView) tempView.findViewById(R.id.txtDay)).setText(getCompleteDay(temp[0]));
                            if(temp[1].charAt(0) == ' ')
                                temp[1] = temp[1].substring(1);
                            ((TextView) tempView.findViewById(R.id.txtSubject)).setText(temp[1]);
                            testDaysLayout.addView(tempView);
                        }
                    }
                    String[] timeTableText = timeTable.getTimeTable().trim().split("\n");
                    txtTimeTableDays.setText(timeTableText[2]);
                    for(int j=3; j < timeTableText.length; j++) {
                        tempView = getLayoutInflater().inflate(R.layout.view_time_table, null);
                        String[] temp = timeTableText[j].trim().split(" ");
                        if(temp.length > 1) {
                            ((TextView) tempView.findViewById(R.id.txtDay)).setText(temp[0] + temp[1] + temp[2] + " pm");
                            ((TextView) tempView.findViewById(R.id.txtSubject)).setText(temp[3]);
                            timeTableLayout.addView(tempView);
                        }
                    }

                    linearLayout.removeAllViews();
                    linearLayout.addView(itemView);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        classList = new ArrayList<>();
        classList.add("Select Class");

        progressBar = (ProgressBar) findViewById(R.id.progress);
        new FetchTimeTable().execute();
    }

    public class FetchTimeTable extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            ApiCalls apiCalls = ApiCalls.initializeAPI();
            try {
                return apiCalls.sendGet("TimeTable");
            } catch (final Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityTimeTable.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
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
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 0) {
                        JSONObject object;
                        timeTableList = new ArrayList<>();
                        ClassTimeTable timeTable;
                        for(int i=0; i<jsonArray.length(); i++) {
                            object = jsonArray.getJSONObject(i);
                            timeTable = new ClassTimeTable();
                            timeTable.setClassName(object.getString("cls") + "-" + object.getString("sec") + " (" + object.getString("grp") + ")");
                            timeTable.setTestDays(object.getString("testdays"));
                            timeTable.setTimeTable(object.getString("timetabletext"));
                            timeTableList.add(timeTable);
                            classList.add(timeTable.getClassName());
                        }

                        ArrayAdapter adapter = new ArrayAdapter<>(ActivityTimeTable.this,
                                android.R.layout.simple_spinner_item,
                                classList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnClass.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ActivityTimeTable.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String getCompleteDay(String day) {
        switch (day) {
            case "Mon":
                return "Monday";
            case "Tue":
                return "Tuesday";
            case "Wed":
                return "Wednesday";
            case "Thu":
                return "Thursday";
            case "Fri":
                return "Friday";
        }
        return null;
    }
}
