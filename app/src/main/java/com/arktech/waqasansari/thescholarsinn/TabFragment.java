package com.arktech.waqasansari.thescholarsinn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WaqasAhmed on 6/13/2016.
 */
public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 5 ;


    private int[] tabWhiteIcons = {
            R.drawable.ic_announcement_white_24dp,
            R.drawable.ic_assignment_white_24dp,
            R.drawable.ic_assessment_white_24dp,
            R.drawable.ic_twitter_white_24dp,
            R.drawable.ic_facebook_white_24dp
    };
    private int[] tabBlackIcons = {
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assignment_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_twitter_black_24dp,
            R.drawable.ic_facebook_black_24dp
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 5; i++) {
                    if (i == position)
                        tabLayout.getTabAt(i).setIcon(tabWhiteIcons[i]);
                    else tabLayout.getTabAt(i).setIcon(tabBlackIcons[i]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return view;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabWhiteIcons[0]);
        for(int i=1; i<5; i++)
            if(tabLayout.getTabAt(i) != null)
                tabLayout.getTabAt(i).setIcon(tabBlackIcons[i]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new FragmentAnnouncements(), "Announcements");
        adapter.addFragment(new FragmentScheduleTest(), "Schedule Test");
        adapter.addFragment(new FragmentTestMarks(), "Test Marks");
        adapter.addFragment(new FragmentTweets(), "Twitter");
        adapter.addFragment(new FragmentFacebook(), "Facebook");
        viewPager.setAdapter(adapter);
    }
}
