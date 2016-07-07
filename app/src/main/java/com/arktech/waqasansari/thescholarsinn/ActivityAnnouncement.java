package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ActivityAnnouncement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

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


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.annCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityAnnouncement.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ClassAnnouncement> announcementList = new ArrayList<>();
        ClassAnnouncement announcement = new ClassAnnouncement();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnouncement();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnouncement();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnouncement();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnouncement();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        recyclerView.setAdapter(new AdapterAnnouncement(announcementList));


    }
}
