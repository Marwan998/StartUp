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

public class Company_Requests extends AppCompatActivity {
    ArrayList<ArrayList> list;
    int idx=0;
    int responded = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__requests);
        list = new ArrayList<>();
        CollectionReference cr = db.collection("Company_Requests");
        cr.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot doc = task.getResult();
                    if(doc.isEmpty()){
                        Toast.makeText(Company_Requests.this, "No new applications", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for(QueryDocumentSnapshot document: task.getResult()){
                            ArrayList<String> data = new ArrayList<>();
                            data.add(document.getId()+"");
                            data.add(document.getData().get("Name").toString());
                            data.add(document.getData().get("ID").toString());
                            data.add(document.getData().get("location").toString());
                            data.add(document.getData().get("equity").toString());
                            data.add(document.getData().get("goal").toString());
                            data.add(document.getData().get("Password").toString());
                            data.add(document.getData().get("email").toString());
                            data.add(document.getData().get("phone").toString());
                            list.add(data);
                        }
                        EditText comtxt = (EditText)findViewById(R.id.rcomptxt);
                        EditText loctxt = (EditText)findViewById(R.id.rloctxt);
                        EditText equttxt = (EditText)findViewById(R.id.requtxt);
                        EditText goaltxt = (EditText)findViewById(R.id.rgoaltxt);
                        EditText utxt = (EditText)findViewById(R.id.rutext);
                        EditText emailtxt = (EditText)findViewById(R.id.remailtxt);
                        EditText phonetxt = (EditText)findViewById(R.id.rphonetxt);
                        EditText passtxt = findViewById(R.id.rpasstxt);

                        comtxt.setText(list.get(idx).get(1).toString());
                        utxt.setText(list.get(idx).get(2).toString());
                        loctxt.setText(list.get(idx).get(3).toString());
                        equttxt.setText(list.get(idx).get(4).toString());
                        goaltxt.setText(list.get(idx).get(5).toString());
                        passtxt.setText(list.get(idx).get(6).toString());
                        emailtxt.setText(list.get(idx).get(7).toString());
                        phonetxt.setText(list.get(idx).get(8).toString());
                        
                    }
                }
                else{
                    Toast.makeText(Company_Requests.this, "Connection failed check your internet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void next(View view) {
        EditText comtxt = (EditText)findViewById(R.id.rcomptxt);
        EditText loctxt = (EditText)findViewById(R.id.rloctxt);
        EditText equttxt = (EditText)findViewById(R.id.requtxt);
        EditText goaltxt = (EditText)findViewById(R.id.rgoaltxt);
        EditText utxt = (EditText)findViewById(R.id.rutext);
        EditText emailtxt = (EditText)findViewById(R.id.remailtxt);
        EditText phonetxt = (EditText)findViewById(R.id.rphonetxt);
        EditText passtxt = findViewById(R.id.rpasstxt);
        if(idx+1 < list.size()){
            idx++;
            comtxt.setText(list.get(idx).get(1).toString());
            utxt.setText(list.get(idx).get(2).toString());
            loctxt.setText(list.get(idx).get(3).toString());
            equttxt.setText(list.get(idx).get(4).toString());
            goaltxt.setText(list.get(idx).get(5).toString());
            passtxt.setText(list.get(idx).get(6).toString());
            emailtxt.setText(list.get(idx).get(7).toString());
            phonetxt.setText(list.get(idx).get(8).toString());
        }
        else{
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
        }
    }

    public void refuse(View view) {
        db.collection("Company_Requests").document(list.get(idx).get(0).toString()).delete();
        EditText comtxt = (EditText)findViewById(R.id.rcomptxt);
        EditText loctxt = (EditText)findViewById(R.id.rloctxt);
        EditText equttxt = (EditText)findViewById(R.id.requtxt);
        EditText goaltxt = (EditText)findViewById(R.id.rgoaltxt);
        EditText utxt = (EditText)findViewById(R.id.rutext);
        EditText emailtxt = (EditText)findViewById(R.id.remailtxt);
        EditText phonetxt = (EditText)findViewById(R.id.rphonetxt);
        EditText passtxt = findViewById(R.id.rpasstxt);
        if(idx+1 < list.size()-responded){
            next(new View(getApplicationContext()));
        }
        else{
            list = new ArrayList<>();
            idx=0;
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
            comtxt.setText("");
            utxt.setText("");
            loctxt.setText("");
            equttxt.setText("");
            goaltxt.setText("");
            passtxt.setText("");
            emailtxt.setText("");
            phonetxt.setText("");
        }
    }

    public void accept(View view) {
        Map<String, Object> startup = new HashMap<>();
        startup.put("ID",list.get(idx).get(2).toString());
        startup.put("Name",list.get(idx).get(1).toString());
        startup.put("Password",list.get(idx).get(6).toString());
        startup.put("email",list.get(idx).get(7).toString());
        startup.put("location",list.get(idx).get(3).toString());
        startup.put("equity",list.get(idx).get(4).toString());
        startup.put("goal",list.get(idx).get(5).toString());
        startup.put("phone",list.get(idx).get(8).toString());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Companies").document().set(startup)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Company_Requests.this, "Application accepted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Company_Requests.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
        db.collection("Company_Requests").document(list.get(idx).get(0).toString()).delete();
        EditText comtxt = (EditText)findViewById(R.id.rcomptxt);
        EditText loctxt = (EditText)findViewById(R.id.rloctxt);
        EditText equttxt = (EditText)findViewById(R.id.requtxt);
        EditText goaltxt = (EditText)findViewById(R.id.rgoaltxt);
        EditText utxt = (EditText)findViewById(R.id.rutext);
        EditText emailtxt = (EditText)findViewById(R.id.remailtxt);
        EditText phonetxt = (EditText)findViewById(R.id.rphonetxt);
        EditText passtxt = findViewById(R.id.rpasstxt);
        if(idx+1 < list.size()-responded){
            next(new View(getApplicationContext()));
        }
        else{
            list = new ArrayList<>();
            idx=0;
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
            comtxt.setText("");
            utxt.setText("");
            loctxt.setText("");
            equttxt.setText("");
            goaltxt.setText("");
            passtxt.setText("");
            emailtxt.setText("");
            phonetxt.setText("");
        }

    }

    public void back(View view) {
        finish();
    }
}
