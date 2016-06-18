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
public class FragmentTestMarks extends Fragment {


    public FragmentTestMarks() {
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
        View view = inflater.inflate(R.layout.fragment_test_marks, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.testMarksCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ClassTestMarks> testMarksList = new ArrayList<>();
        ClassTestMarks testMarks = new ClassTestMarks();
        testMarks.setSubject("Physics");
        testMarks.set_class("XI");
        testMarks.setDay("March 12, 2016");
        testMarksList.add(testMarks);

        testMarks = new ClassTestMarks();
        testMarks.setSubject("Chemistry");
        testMarks.set_class("XI");
        testMarks.setDay("April 12, 2016");
        testMarksList.add(testMarks);

        testMarks = new ClassTestMarks();
        testMarks.setSubject("Mathematics");
        testMarks.set_class("XI");
        testMarks.setDay("May 12, 2016");
        testMarksList.add(testMarks);

        testMarks = new ClassTestMarks();
        testMarks.setSubject("Computer");
        testMarks.set_class("XI");
        testMarks.setDay("March 30, 2016");
        testMarksList.add(testMarks);

        testMarks = new ClassTestMarks();
        testMarks.setSubject("Urdu");
        testMarks.set_class("XI");
        testMarks.setDay("March 12, 2015");
        testMarksList.add(testMarks);

        testMarks = new ClassTestMarks();
        testMarks.setSubject("Islamiat");
        testMarks.set_class("XI");
        testMarks.setDay("March 12, 2016");
        testMarksList.add(testMarks);

        testMarks = new ClassTestMarks();
        testMarks.setSubject("English");
        testMarks.set_class("XI");
        testMarks.setDay("May 31, 2016");
        testMarksList.add(testMarks);

        recyclerView.setAdapter(new AdapterTestMarks(testMarksList));

        return view;
    }

}
