package com.arktech.waqasansari.thescholarsinn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAnnouncements extends Fragment {


    public FragmentAnnouncements() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_announcements, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.annCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ClassAnnAndSchTest> announcementList = new ArrayList<>();
        ClassAnnAndSchTest announcement = new ClassAnnAndSchTest();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("closed");
        announcement.setText("Scholars' Inn will remain closed today due to city situation.");
        announcement.setDay("June 14, 2016");
        announcementList.add(announcement);

        recyclerView.setAdapter(new AdapterAnnAndSchTest(announcementList));

        return view;
    }

}
