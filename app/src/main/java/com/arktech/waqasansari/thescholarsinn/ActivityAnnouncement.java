package com.arktech.waqasansari.thescholarsinn;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
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
import java.util.Calendar;
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

        Firebase.setAndroidContext(this);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        if(progressBar != null)
            progressBar.setVisibility(View.VISIBLE);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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


        String firebaseUrl = "https://the-scholars-inn.firebaseio.com/";
        reference = new Firebase(firebaseUrl).child("notification");

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ClassAnnouncement> announcementList = new ArrayList<>();
                ClassAnnouncement announcement;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    announcement = snapshot.getValue(ClassAnnouncement.class);
                    announcementList.add(announcement);
                    if(announcement.getNotified().equals("false")) {
                        sendNotification(announcement.getTitle(), announcement.getMessage());
                        Map<String, Object> map = new HashMap<>();
                        map.put("notified", "true");
                        reference.child(snapshot.getKey()).updateChildren(map);
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

    private void sendNotification(String title, String message) {
        Intent resultIntent = new Intent(ActivityAnnouncement.this, ActivityShowNotification.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.putExtra("title", title);
        resultIntent.putExtra("message", message);
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());
        String date = format.format(Calendar.getInstance().getTime());
        resultIntent.putExtra("date", date);
        PendingIntent pendingIntent = PendingIntent.getActivity(ActivityAnnouncement.this, 0, resultIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ActivityAnnouncement.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setTicker("New Notification Alert!")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
