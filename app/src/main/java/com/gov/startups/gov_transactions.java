package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class gov_transactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_transactions);

        Button btn = findViewById(R.id.button14);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundResource(R.drawable.btn_design_clicked);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jordan.gov.jo/wps/portal/Home/Investor/InvestorLifeEvent?catId=5a50bf95-55ef-4524-8f51-055c5a1d2c3f"));
                startActivity(browserIntent);
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
