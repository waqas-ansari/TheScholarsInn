package com.arktech.waqasansari.thescholarsinn.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arktech.waqasansari.thescholarsinn.http_requests.GetPicture;
import com.arktech.waqasansari.thescholarsinn.R;
import com.arktech.waqasansari.thescholarsinn.support_classes.ClassAnnouncement;

/**
 * Created by Linta Ansari on 7/5/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    Context context;
    ClassAnnouncement announcement;

    public CustomPagerAdapter(ClassAnnouncement announcement, Context context){
        this.context = context;
        this.announcement = announcement;
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

            if(announcement != null) {
                txtHeading.setText(announcement.getTitle());
                txtText.setText(announcement.getMessage());

                mainLayout.removeAllViews();
                mainLayout.addView(tempView);
            }

        } else if (position == 1) {
            View tempView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_advertisement, null);
            ImageView imageView = (ImageView) tempView.findViewById(R.id.imgAdvertisement);
            TextView textView = (TextView) tempView.findViewById(R.id.txtAdvertisement);

            new GetPicture(imageView, textView).execute("http://thescholarsinn.com/img/Mobile.jpg");

            mainLayout.removeAllViews();
            mainLayout.addView(tempView);

        } else if (position == 2) {
            View tempView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_features, null);
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
