package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

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
    }
}
