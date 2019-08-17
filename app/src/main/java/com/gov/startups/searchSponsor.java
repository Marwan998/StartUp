package com.gov.startups;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class searchSponsor extends AppCompatActivity {
    ListView list;

    String[] name ={
            //company name
    };

    String[] contactInfo ={
            //contact info for the company
    };

    Integer[] icon={
            //company logo picture
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_company_spons);

        MyListAdapter adapter=new MyListAdapter(this, name, contactInfo,icon);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // TODO Auto-generated method stub
              //contact copmany based on which one the sponsor choose

            }
        });
    }
}