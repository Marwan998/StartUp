package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class StartupMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statrtup_main);

        ImageView iv = findViewById(R.id.imageView2);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        iv.getLayoutParams().height=size.y/2;
    }

    public void contact(View view) {
        view.setBackgroundResource(R.drawable.btn_design_clicked);
        final Button temp = (Button)view;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                temp.setBackgroundResource(R.drawable.btn_design);
            }
        },500);
    }
    public void back(View view) {
        finish();
    }

    public void ads(View view) {
        view.setBackgroundResource(R.drawable.btn_design_clicked);
        startActivity(new Intent(StartupMain.this,seeGovermentAds.class));
        final Button temp = (Button)view;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                temp.setBackgroundResource(R.drawable.btn_design);
            }
        },500);
    }

    public void transactions(View view) {
        view.setBackgroundResource(R.drawable.btn_design_clicked);
        startActivity(new Intent(StartupMain.this,gov_transactions.class));
        final Button temp = (Button)view;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                temp.setBackgroundResource(R.drawable.btn_design);
            }
        },500);
    }
}
