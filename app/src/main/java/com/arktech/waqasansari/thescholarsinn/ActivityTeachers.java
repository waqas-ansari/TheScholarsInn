package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ActivityTeachers extends AppCompatActivity {

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.teacherCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        List<ClassTeachers> teachersList = new ArrayList<>();
        ClassTeachers teachers = new ClassTeachers();
        teachers.setTeacherName("Syed Atif Ata");
        teachers.setQualification("M.S. (Computer Science)");
        teachers.setSubject("Physics");
        teachers.setInstitute("SZABIST");
        teachers.setEmail("atifata@hotmail.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Rahila Asghar");
        teachers.setQualification("M.Sc (Micro Biology)");
        teachers.setSubject("Zoology");
        teachers.setInstitute("Karachi University");
        teachers.setEmail("rahila-ali2011@hotmail.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Syed Yousuf Ata");
        teachers.setQualification("B.E. (Electronics)");
        teachers.setSubject("Mathematics");
        teachers.setInstitute("Sir Syed University of Engineering and Technology");
        teachers.setEmail("yousufata@hotmail.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Hussain ALi");
        teachers.setQualification("B.Sc. (Chemistry)");
        teachers.setSubject("Chemistry");
        teachers.setInstitute("Karachi University");
        teachers.setEmail("syedhussain_a@yahoo.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Waqas Ahmed Ansari");
        teachers.setQualification("B.E. (Systems Engineering)");
        teachers.setSubject("Physics");
        teachers.setInstitute("NED University of Engineering and Technology");
        teachers.setEmail("waqasahmedansari@hotmail.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Fahad Nadeem");
        teachers.setQualification("B.S. (Computer Science)");
        teachers.setSubject("Computer");
        teachers.setInstitute("Iqra University");
        teachers.setEmail("fahadibnenadeem@hotmail.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Waqas Abdul Malik");
        teachers.setQualification("B.Sc. (Physics)");
        teachers.setSubject("English");
        teachers.setInstitute("Karachi University");
        teachers.setEmail("waqasabdulmalik@hotmail.com");
        teachersList.add(teachers);

        teachers = new ClassTeachers();
        teachers.setTeacherName("Tooba Khalil");
        teachers.setQualification("B.Sc. (Chemistry)");
        teachers.setSubject("Chemistry");
        teachers.setInstitute("Karachi University");
        teachers.setEmail("toobakhalil@hotmail.com");
        teachersList.add(teachers);

        recyclerView.setAdapter(new AdapterTeachers(teachersList));


    }
}
