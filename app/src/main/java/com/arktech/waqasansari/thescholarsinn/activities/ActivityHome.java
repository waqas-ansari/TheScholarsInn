package com.arktech.waqasansari.thescholarsinn.activities;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.arktech.waqasansari.thescholarsinn.support_classes.ClassAnnouncement;
import com.arktech.waqasansari.thescholarsinn.adapters.CustomPagerAdapter;
import com.arktech.waqasansari.thescholarsinn.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityHome extends AppCompatActivity {
    ClassAnnouncement announcement;
    View[] indicators = new View[3];

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if (getIntent().getExtras() != null) {
            Intent intent = new Intent(ActivityHome.this, ActivityShowNotification.class);
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                intent.putExtra(key, value);
            }
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());
            String date = format.format(Calendar.getInstance().getTime());
            intent.putExtra("date", date);
            if(intent.hasExtra("title") && intent.hasExtra("message"))
                startActivity(intent);
        }

        Firebase.setAndroidContext(this);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        if (viewPager != null)
            viewPager.setAdapter(new CustomPagerAdapter(announcement, ActivityHome.this));

        Firebase reference = new Firebase("https://the-scholars-inn.firebaseio.com/notification");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    announcement = snapshot.getValue(ClassAnnouncement.class);
                    if (announcement.getDisplayOnMain().equals("Yes")) {
                        viewPager.setAdapter(new CustomPagerAdapter(announcement, ActivityHome.this));
                        return;
                    } else announcement = null;
                }
                viewPager.setAdapter(new CustomPagerAdapter(announcement, ActivityHome.this));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ImageView imgCoachingLogo = (ImageView) findViewById(R.id.imgCoachingLogo);
        if(imgCoachingLogo != null) {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                setAlphaForView(imgCoachingLogo, 0.1f);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navItems) ;

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                //click events of navigation view

                if (menuItem.getItemId() == R.id.nav_contact_us)
                    startActivity(new Intent(ActivityHome.this, ActivityContactUs.class));
                else if (menuItem.getItemId() == R.id.nav_about_us)
                    startActivity(new Intent(ActivityHome.this, ActivityAboutUs.class));

                return false;
            }

        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();



        indicators[0] = findViewById(R.id.im1);
        indicators[1] = findViewById(R.id.im2);
        indicators[2] = findViewById(R.id.im3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < indicators.length; i++) {
                    if (i == position)
                        if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
                            indicators[i].setBackgroundDrawable(ContextCompat.getDrawable(ActivityHome.this, R.drawable.filled_circle));
                        else indicators[i].setBackground(ContextCompat.getDrawable(ActivityHome.this, R.drawable.filled_circle));
                    else {
                        if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
                            indicators[i].setBackgroundDrawable(ContextCompat.getDrawable(ActivityHome.this, R.drawable.holo_circle));
                        else indicators[i].setBackground(ContextCompat.getDrawable(ActivityHome.this, R.drawable.holo_circle));
                    }
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

        findViewById(R.id.btnTimeTable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, ActivityTimeTable.class));
            }
        });

    }


    private void setAlphaForView(View v, float alpha) {
        AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
        animation.setDuration(0);
        animation.setFillAfter(true);
        v.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

}
