package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View view) {
        view.setBackgroundResource(R.drawable.btn_design_clicked);
        Intent lgn = new Intent(getApplicationContext(), Login.class);
        if (view.getTag().toString().equals("company")) {
            lgn.putExtra("type", 0);
            startActivity(lgn);
        } else if (view.getTag().toString().equals("gov")) {
            lgn.putExtra("type", 1);
            startActivity(lgn);
        } else if (view.getTag().toString().equals("sponsor")) {
            lgn.putExtra("type", 2);
            startActivity(lgn);
        }
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
