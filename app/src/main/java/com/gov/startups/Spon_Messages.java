package com.gov.startups;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Spon_Messages extends AppCompatActivity {

    int type =-1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spon_messages);
        String name = getIntent().getStringExtra("name");
        type = getIntent().getIntExtra("type",-1);
        Toast.makeText(this, name+" "+type, Toast.LENGTH_SHORT).show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference data = db.collection("Spr_Messages");
        data.whereEqualTo("Type",type).whereEqualTo("Receiver",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()){
                        arrayList.add(document.getData().get("Message").toString());
                    }
                    ArrayAdapter ad = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                    ListView lv = findViewById(R.id.list);
                    lv.setAdapter(ad);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}