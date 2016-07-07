package com.arktech.waqasansari.thescholarsinn;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityHome extends AppCompatActivity {
    String[] text = {"Text to be displayed", "Text to be displayed", "Text to be displayed"};
    View[] indicators = new View[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomPagerAdapter(text, ActivityHome.this));

        indicators[0] = findViewById(R.id.im1);
        indicators[1] = findViewById(R.id.im2);
        indicators[2] = findViewById(R.id.im3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0; i<indicators.length; i++){
                    if(i == position)
                        indicators[i].setBackground(ContextCompat.getDrawable(ActivityHome.this, R.drawable.filled_circle));
                    else indicators[i].setBackground(ContextCompat.getDrawable(ActivityHome.this, R.drawable.holo_circle));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        findViewById(R.id.btnAnn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityAnnouncement.class));
            }
        });

        findViewById(R.id.btnScheduledTests).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityScheduledTests.class));
            }
        });

        findViewById(R.id.btnTestAndAttendance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityTestMarksAndAttendance.class));
            }
        });

        findViewById(R.id.btnTeachers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityTeachers.class));
            }
        });

        findViewById(R.id.btnTwitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityTweets.class));
            }
        });

        findViewById(R.id.btnFacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityFacebook.class));
            }
        });

    }
}
