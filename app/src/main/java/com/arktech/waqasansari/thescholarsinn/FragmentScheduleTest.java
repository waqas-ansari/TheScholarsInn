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
public class FragmentScheduleTest extends Fragment {


    public FragmentScheduleTest() {
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
        View view = inflater.inflate(R.layout.fragment_schedule_test, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.schTestCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ClassAnnAndSchTest> announcementList = new ArrayList<>();
        ClassAnnAndSchTest announcement = new ClassAnnAndSchTest();
        announcement.setHeading("physics");
        announcement.setText("Topic: Addition of Vectors.");
        announcement.setDay("June 1, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("chemistry");
        announcement.setText("Topic: Elements of group IV");
        announcement.setDay("May 29, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("urdu");
        announcement.setText("Topic: Ghalib ki Shairi pe tabsara");
        announcement.setDay("June 11, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("islamiat");
        announcement.setText("Topic: Aqida e Tauheed");
        announcement.setDay("June 7, 2016");
        announcementList.add(announcement);

        announcement = new ClassAnnAndSchTest();
        announcement.setHeading("mathematics");
        announcement.setText("Topic: Simultaneous solution of equation");
        announcement.setDay("April 23, 2016");
        announcementList.add(announcement);

        recyclerView.setAdapter(new AdapterAnnAndSchTest(announcementList));

        return view;
    }

}
