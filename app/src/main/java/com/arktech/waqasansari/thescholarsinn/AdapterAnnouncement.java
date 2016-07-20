package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WaqasAhmed on 6/15/2016.
 */
public class AdapterAnnouncement extends RecyclerView.Adapter<AdapterAnnouncement.AnnouncementViewHolder> {
    private List<ClassAnnouncement> announcementList;

    public AdapterAnnouncement(List<ClassAnnouncement> announcementList) {
        this.announcementList = announcementList;
    }

    @Override
    public AnnouncementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_announcement, parent, false);

        return new AnnouncementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnnouncementViewHolder holder, int position) {
        ClassAnnouncement announcement = announcementList.get(position);
        holder.txtHeading.setText(announcement.getTitle());
        holder.txtText.setText(announcement.getMessage());
        holder.txtDay.setText(announcement.getDate());
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }


    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtHeading;
        protected TextView txtText;
        protected TextView txtDay;

        public AnnouncementViewHolder(View v){
            super(v);
            txtHeading = (TextView) v.findViewById(R.id.txtHeading);
            txtText = (TextView) v.findViewById(R.id.txtDescription);
            txtDay = (TextView) v.findViewById(R.id.txtDay);
        }

    }
}

