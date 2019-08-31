package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sponsor_Requests extends AppCompatActivity {
    ArrayList<ArrayList> list;
    int idx=0;
    int responded = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor__requests);
        list = new ArrayList<>();
        CollectionReference cr = db.collection("Sponsor_Requests");
        cr.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot doc = task.getResult();
                    if(doc.isEmpty()){
                        Toast.makeText(Sponsor_Requests.this, "No new applications", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for(QueryDocumentSnapshot document: task.getResult()){
                            ArrayList<String> data = new ArrayList<>();
                            data.add(document.getId()+"");
                            data.add(document.getData().get("name").toString());
                            data.add(document.getData().get("ID").toString());
                            data.add(document.getData().get("email").toString());
                            data.add(document.getData().get("Password").toString());
                            data.add(document.getData().get("phone").toString());
                            list.add(data);
                        }
                        EditText name = findViewById(R.id.sname);
                        EditText id = findViewById(R.id.suname);
                        EditText email = findViewById(R.id.semail);
                        EditText pass = findViewById(R.id.spass);
                        EditText phone = findViewById(R.id.sphone);

                        name.setText(list.get(idx).get(1).toString());
                        id.setText(list.get(idx).get(2).toString());
                        email.setText(list.get(idx).get(3).toString());
                        pass.setText(list.get(idx).get(4).toString());
                        phone.setText(list.get(idx).get(5).toString());

                    }
                }
                else{
                    Toast.makeText(Sponsor_Requests.this, "Connection failed check your internet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void nexts(View view) {
        EditText name = findViewById(R.id.sname);
        EditText id = findViewById(R.id.suname);
        EditText email = findViewById(R.id.semail);
        EditText pass = findViewById(R.id.spass);
        EditText phone = findViewById(R.id.sphone);
        if(idx+1 < list.size()){
            idx++;
            name.setText(list.get(idx).get(1).toString());
            id.setText(list.get(idx).get(2).toString());
            email.setText(list.get(idx).get(3).toString());
            pass.setText(list.get(idx).get(4).toString());
            phone.setText(list.get(idx).get(5).toString());
        }
        else{
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
        }
    }

    public void refuses(View view) {
        db.collection("Sponsor_Requests").document(list.get(idx).get(0).toString()).delete();
        EditText name = findViewById(R.id.sname);
        EditText id = findViewById(R.id.suname);
        EditText email = findViewById(R.id.semail);
        EditText pass = findViewById(R.id.spass);
        EditText phone = findViewById(R.id.sphone);
        if(idx+1 < list.size()-responded){
            nexts(new View(getApplicationContext()));
        }
        else{
            list = new ArrayList<>();
            idx=0;
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
            name.setText("");
            id.setText("");
            email.setText("");
            pass.setText("");
            phone.setText("");
        }
    }

    public void accepts(View view) {
        Map<String, Object> sponsor = new HashMap<>();
        sponsor.put("ID",list.get(idx).get(2).toString());
        sponsor.put("name",list.get(idx).get(1).toString());
        sponsor.put("Password",list.get(idx).get(4).toString());
        sponsor.put("email",list.get(idx).get(3).toString());
        sponsor.put("phone",list.get(idx).get(5).toString());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Sponsors").document().set(sponsor)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Sponsor_Requests.this, "Application accepted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Sponsor_Requests.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
        db.collection("Sponsor_Requests").document(list.get(idx).get(0).toString()).delete();
        EditText name = findViewById(R.id.sname);
        EditText id = findViewById(R.id.suname);
        EditText email = findViewById(R.id.semail);
        EditText pass = findViewById(R.id.spass);
        EditText phone = findViewById(R.id.sphone);
        if(idx+1 < list.size()-responded){
            nexts(new View(getApplicationContext()));
        }
        else{
            list = new ArrayList<>();
            idx=0;
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
            name.setText("");
            id.setText("");
            email.setText("");
            pass.setText("");
            phone.setText("");
        }

    }

    public void back(View view) {
        finish();
    }
}


