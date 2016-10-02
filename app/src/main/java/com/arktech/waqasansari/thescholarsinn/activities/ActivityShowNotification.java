package com.arktech.waqasansari.thescholarsinn.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.arktech.waqasansari.thescholarsinn.R;

public class ActivityShowNotification extends AppCompatActivity {
    String title, message, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_show_notification);

        TextView txtHeading = (TextView) findViewById(R.id.txtHeading);
        TextView txtText = (TextView) findViewById(R.id.txtDescription);
        TextView txtDay = (TextView) findViewById(R.id.txtDay);

        if(getIntent().getExtras() != null) {
            title = getIntent().getStringExtra("title");
            message = getIntent().getStringExtra("message");
            date = getIntent().getStringExtra("date");
            txtHeading.setText(title);
            txtText.setText(message);
            txtDay.setText(date);

            findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }


    }
}
