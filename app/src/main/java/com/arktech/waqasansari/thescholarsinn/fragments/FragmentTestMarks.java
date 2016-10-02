package com.arktech.waqasansari.thescholarsinn.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arktech.waqasansari.thescholarsinn.R;
import com.arktech.waqasansari.thescholarsinn.adapters.AdapterListTestMarks;
import com.arktech.waqasansari.thescholarsinn.http_requests.ApiCalls;
import com.arktech.waqasansari.thescholarsinn.support_classes.ClassDetailedMarks;
import com.arktech.waqasansari.thescholarsinn.support_classes.ClassSubjectWiseMarks;
import com.arktech.waqasansari.thescholarsinn.support_classes.ClassTestMarks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTestMarks extends Fragment {
    LayoutInflater inflater;
    ListView lstTestMarks;

    TextView txtClassName;
    TextView txtStudentName;
    TextView txtFatherName;

    ProgressBar progressBar;

    public FragmentTestMarks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_test_marks, container, false);
        lstTestMarks = (ListView) view.findViewById(R.id.lstMarks);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);

        new FetchTestMarks(getArguments().getString("date"), getArguments().getString("id")).execute();

        return view;
    }

    private class FetchTestMarks extends AsyncTask<Void, Void, String> {
        String date;
        String studentId;


        public FetchTestMarks(String date, String studentId){
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
                return calls.sendGet("TestsMarks", date, studentId);
            } catch (final Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "There is something wrong with your Internet Connection", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(s);
            if(! s.equals("error")){
                List<ClassTestMarks> testMarksList = new ArrayList<>();
                ClassTestMarks testMark;


                List<ClassSubjectWiseMarks> subjectWiseResults = new ArrayList<>();
                ClassSubjectWiseMarks result;

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
                            result = new ClassSubjectWiseMarks();
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
                        int obt=0, max=0;
                        for (ClassSubjectWiseMarks subjectWiseResult : subjectWiseResults) {
                            obt = obt + subjectWiseResult.getObtMarks();
                            max = max + subjectWiseResult.getMaxMarks();
                        }

                        float per = ( (float)obt / (float)max ) * 100.0f;
                        double roundOff = Math.round(per * 100.0) / 100.0;


                        ((TextView) view.findViewById(R.id.txtObtOutOfTotal)).setText(String.valueOf(obt) + "/" + String.valueOf(max));
                        ((TextView) view.findViewById(R.id.txtPercentage)).setText(String.valueOf(roundOff) + "%");


                        setTexts(testMarksList.get(0).getClassName(), testMarksList.get(0).getStudentName(),
                                testMarksList.get(0).getFatherName());

                        lstTestMarks.addHeaderView(view);

                        lstTestMarks.setAdapter(new AdapterListTestMarks(subjectWiseResults, detailedLists, getContext()));


                    } else Toast.makeText(getContext(), "No result available for selected Date/Student ID", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Something went wrong.\nTry again", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    public void setTexts(String className, String studentName, String fatherName){
        txtClassName.setText(className);
        txtStudentName.setText(studentName);
        txtFatherName.setText(fatherName);
    }

}
