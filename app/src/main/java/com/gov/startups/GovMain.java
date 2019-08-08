package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public void search(View view) {
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
    }

    public void contact(View view) {
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
    }
}
