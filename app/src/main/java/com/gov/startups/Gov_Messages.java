package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Gov_Messages extends AppCompatActivity {
    int type = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov__messages);
        type = getIntent().getIntExtra("type",-1);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference data = db.collection("Messages");
        data.whereEqualTo("Type",type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()){
                        arrayList.add(document.getData().get("Message").toString());
                    }
                    ArrayAdapter ad = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                    ListView lv = findViewById(R.id.messages_list);
                    lv.setAdapter(ad);
                }
                else{
                    Toast.makeText(Gov_Messages.this, "Please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
