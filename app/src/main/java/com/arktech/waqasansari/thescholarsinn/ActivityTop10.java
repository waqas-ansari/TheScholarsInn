package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ActivityTop10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.top10CardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ClassResult> resultList = new ArrayList<>();
        ClassResult result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(154);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(152);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(149);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(146);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(145);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(140);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(139);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(139);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(135);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        result = new ClassResult();
        result.setName("Umaima Fareed");
        result.setID("2031");
        result.set_class("XI");
        result.setGainedMarks(130);
        result.setTotalMarks(170);
        result.setPercentage();
        resultList.add(result);

        recyclerView.setAdapter(new AdapterResult(resultList));
    }
}
