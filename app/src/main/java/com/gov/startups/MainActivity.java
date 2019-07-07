package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View view) {
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
    }
}
