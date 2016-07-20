package com.arktech.waqasansari.thescholarsinn;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Linta Ansari on 7/5/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    String textToBeDisplayed;
    Context context;
    ClassAnnouncement announcement;
    ViewPager viewPager;

    public CustomPagerAdapter(String textToBeDisplayed, ClassAnnouncement announcement, Context context, ViewPager viewPager){
        this.textToBeDisplayed = textToBeDisplayed;
        this.context = context;
        this.announcement = announcement;
        this.viewPager = viewPager;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View view = inflater.inflate(R.layout.pager_text_item, null);


        LinearLayout mainLayout = (LinearLayout) view.findViewById(R.id.mainLayout);

        if (position == 0) {
            View tempView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_announcement, null);

            TextView txtHeading = (TextView) tempView.findViewById(R.id.txtHeading);
            TextView txtText = (TextView) tempView.findViewById(R.id.txtDescription);
            TextView txtDay = (TextView) tempView.findViewById(R.id.txtDay);

            if(announcement != null) {
                txtHeading.setText(announcement.getTitle());
                txtText.setText(announcement.getMessage());
                txtDay.setText(announcement.getDate());

                mainLayout.removeAllViews();
                mainLayout.addView(tempView);
                viewPager.setCurrentItem(0, true);
            }

        } else if (position == 1) {
            View tempView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_advertisement, null);
            ImageView imageView = (ImageView) tempView.findViewById(R.id.imgAdvertisement);

            new GetPicture(imageView).execute("http://thescholarsinn.com/img/Mobile.jpg");

            mainLayout.removeAllViews();
            mainLayout.addView(tempView);

        } else if (position == 2) {
            View tempView = ((Activity) context).getLayoutInflater().inflate(R.layout.contact_info, null);
            mainLayout.removeAllViews();
            mainLayout.addView(tempView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
