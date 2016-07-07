package com.arktech.waqasansari.thescholarsinn;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Linta Ansari on 7/5/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    String[] textToBeDisplayed;
    Context context;

    public CustomPagerAdapter(String[] textToBeDisplayed, Context context){
        this.textToBeDisplayed = textToBeDisplayed;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.pager_text_item, null);
        ((TextView) view.findViewById(R.id.txtToBeDisplayed)).setText(textToBeDisplayed[position]);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return textToBeDisplayed.length;
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
