package com.arktech.waqasansari.thescholarsinn;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityTeachers extends AppCompatActivity {
    ListView lstTeacher;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

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


        progressBar = (ProgressBar) findViewById(R.id.progress);
        lstTeacher = (ListView) findViewById(R.id.lstTeachers);

        new FetchTeachers().execute();


    }

    private class FetchTeachers extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            ApiCalls apiCalls = ApiCalls.initializeAPI();
            try {
                return apiCalls.sendGet("Teachers");
            } catch (final Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityTeachers.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<ClassTeachers> teachersList = new ArrayList<>();
            ClassTeachers teacher;

            try {
                JSONArray  jsonArray = new JSONArray(s);
                JSONObject object;
                for(int i=0; i<jsonArray.length(); i++){
                    object = jsonArray.getJSONObject(i);
                    teacher = new ClassTeachers();
                    teacher.setTeacherName(object.getString("TeacherName"));
                    teacher.setSubject(object.getString("MainSubject"));
                    teacher.setQualification(object.getString("Qualification"));
                    teachersList.add(teacher);
                }

                lstTeacher.setAdapter(new AdapterListTeachers(teachersList, ActivityTeachers.this));
                progressBar.setVisibility(View.GONE);

            } catch (JSONException e) {
                e.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
