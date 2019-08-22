package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainSponsor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sponsor);

        Button btn = findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSponsor.this, searchSponsor.class));
            }
        });


        Button btn1 = findViewById(R.id.button6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSponsor.this,Spon_Messages.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.button7);

         btn2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainSponsor.this,Spon_Messages.class);
                 intent.putExtra("name",getIntent().getStringExtra("name"));
                 intent.putExtra("type",3);
                 startActivity(intent);
             }
         });
    }
}
