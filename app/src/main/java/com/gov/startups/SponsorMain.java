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

public class SponsorMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sponsor);

        ImageView iv = findViewById(R.id.imageView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        iv.getLayoutParams().height=size.y/2;

        Button btn = findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.btn_design_clicked);
                startActivity(new Intent(SponsorMain.this, searchSponsor.class));
                final Button temp = (Button)v;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        temp.setBackgroundResource(R.drawable.btn_design);
                    }
                },500);
            }
        });


        Button btn1 = findViewById(R.id.button6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.btn_design_clicked);
                Intent intent = new Intent(SponsorMain.this,Spon_Messages.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("type",1);
                startActivity(intent);
                final Button temp = (Button)v;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        temp.setBackgroundResource(R.drawable.btn_design);
                    }
                },500);
            }
        });

        Button btn2 = findViewById(R.id.button7);

         btn2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 view.setBackgroundResource(R.drawable.btn_design_clicked);
                 Intent intent = new Intent(SponsorMain.this,Spon_Messages.class);
                 intent.putExtra("name",getIntent().getStringExtra("name"));
                 intent.putExtra("type",3);
                 startActivity(intent);
                 final Button temp = (Button)view;
                 Timer timer = new Timer();
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                         temp.setBackgroundResource(R.drawable.btn_design);
                     }
                 },500);
             }
         });

         Button btn3 = findViewById(R.id.button8);

         btn3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 view.setBackgroundResource(R.drawable.btn_design_clicked);
                 startActivity(new Intent(SponsorMain.this,seeGovermentAds.class));
                 final Button temp = (Button)view;
                 Timer timer = new Timer();
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                         temp.setBackgroundResource(R.drawable.btn_design);
                     }
                 },500);
             }
         });
    }
    public void back(View view) {
        finish();
    }
}
