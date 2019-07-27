package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GovMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_main);
    }

    public void announce(View view) {
        AnnounceDialog ad = new AnnounceDialog(GovMain.this);
        ad.show();
    }
}
