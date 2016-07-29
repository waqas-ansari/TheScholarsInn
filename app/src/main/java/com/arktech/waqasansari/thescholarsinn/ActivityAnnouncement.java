package com.arktech.waqasansari.thescholarsinn;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ActivityAnnouncement extends AppCompatActivity {
    NotificationCompat.Builder notification;
    PendingIntent pIntent;
    NotificationManager manager;
    Intent resultIntent;
    TaskStackBuilder stackBuilder;

    RecyclerView recyclerView;

    ProgressBar progressBar;

    //Firebase
    Firebase reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        if(progressBar != null)
            progressBar.setVisibility(View.VISIBLE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }


        recyclerView = (RecyclerView) findViewById(R.id.annCardList);
        if(recyclerView != null)
            recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityAnnouncement.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        String firebaseUrl = "https://thescholarsinn.firebaseio.com/";
        reference = new Firebase(firebaseUrl).child("notification");


        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<ClassAnnouncement> announcementList = new ArrayList<>();
                ClassAnnouncement announcement;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    announcement = snapshot.getValue(ClassAnnouncement.class);
                    if(announcement.getIsPushed().equals("true")){
                        if(announcement.getNotified().equals("false")){
                            announcement.setNotified("true");
                            showNotification(announcement.getTitle(), announcement.getMessage());
                            Map<String, Object> param = new HashMap<>();
                            param.put("notified", "true");
                            reference.child(snapshot.getKey()).updateChildren(param);
                        }
                        announcementList.add(announcement);
                    }

                }

                Collections.sort(announcementList, new Comparator<ClassAnnouncement>() {
                    @Override
                    public int compare(ClassAnnouncement lhs, ClassAnnouncement rhs) {

                        if(lhs.getDate() != null || rhs.getDate() != null) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
                            Date lhsDate=null, rhsDate=null;
                            try {
                                lhsDate = dateFormat.parse(lhs.getDate());
                                rhsDate = dateFormat.parse(rhs.getDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return rhsDate.compareTo(lhsDate);

                        } else return 0;

                    }
                });
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(new AdapterAnnouncement(announcementList));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityAnnouncement.this, "There is something wrong with your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showNotification(String title, String message){
        //Creating Notification Builder
        notification = new NotificationCompat.Builder(ActivityAnnouncement.this)
                .setContentTitle(title)
                .setContentText(message)
                .setTicker("New Notification Alert!")
                .setSmallIcon(R.drawable.ic_stat_coaching_logo)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true);

        //Creating new Stack Builder
        stackBuilder = TaskStackBuilder.create(ActivityAnnouncement.this);
        stackBuilder.addParentStack(ActivityAnnouncement.class);
        //Intent which is opened when notification is clicked
        resultIntent = new Intent(ActivityAnnouncement.this, ActivityAnnouncement.class);
        stackBuilder.addNextIntent(resultIntent);
        pIntent =  stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pIntent);
        manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification.build());
    }
}
