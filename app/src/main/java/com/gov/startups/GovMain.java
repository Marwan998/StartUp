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

public class GovMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_main);

        ImageView iv = findViewById(R.id.imageView3);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        iv.getLayoutParams().height=size.y/2;
    }

    public void announce(View view) {
        AnnounceDialog ad = new AnnounceDialog(GovMain.this);
        ad.show();
    }

    public void search(View view) {
        view.setBackgroundResource(R.drawable.btn_design_clicked);
        Intent intent = new Intent(getApplicationContext(),Search.class);
        switch (view.getId()){
            case R.id.search_company:
            intent.putExtra("type",1);
            break;
            case R.id.search_sponsor:
            intent.putExtra("type",2);
            break;
        }
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

    public void contact(View view) {
        view.setBackgroundResource(R.drawable.btn_design_clicked);
        Intent intent = new Intent(getApplicationContext(),Gov_Messages.class);
        switch (view.getId()){
            case R.id.contact_company:
                intent.putExtra("type",1);
                break;
            case R.id.contact_sponsor:
                intent.putExtra("type",2);
                break;
        }
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
    public void back(View view) {
        finish();
    }
}
