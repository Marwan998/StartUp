package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class View_Requests extends AppCompatActivity {
    ArrayList<ArrayList> list;
    int idx=0;
    String phone;
    String email;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__requests);
        list = new ArrayList<>();
        CollectionReference cr = db.collection("Support_Requests");
        cr.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot doc = task.getResult();
                    if(doc.isEmpty()){
                        Toast.makeText(View_Requests.this, "No new applications", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for(QueryDocumentSnapshot document: task.getResult()){
                            ArrayList<String> data = new ArrayList<>();
                            data.add(document.getData().get("ID").toString());
                            data.add(document.getData().get("budget").toString());
                            data.add(document.getData().get("employees").toString());
                            data.add(document.getData().get("why").toString());
                            data.add(document.getData().get("phone").toString());
                            data.add(document.getData().get("email").toString());
                            list.add(data);
                        }



                        EditText name = (EditText)findViewById(R.id.name);
                        EditText budget = (EditText)findViewById(R.id.budget);
                        EditText employees = (EditText)findViewById(R.id.empl);
                        EditText why = (EditText)findViewById(R.id.why);
                        EditText phoneno = (EditText)findViewById(R.id.phone);
                        EditText mail = (EditText)findViewById(R.id.mail);

                        name.setText(list.get(idx).get(0).toString());
                        budget.setText(list.get(idx).get(1).toString());
                        employees.setText(list.get(idx).get(2).toString());
                        why.setText(list.get(idx).get(3).toString());
                        phoneno.setText(list.get(idx).get(4).toString());
                        mail.setText(list.get(idx).get(5).toString());
                    }
                }
                else{
                    Toast.makeText(View_Requests.this, "Connection failed check your internet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void back(View view) {
        finish();
    }

    public void next(View view) {
        EditText name = (EditText)findViewById(R.id.name);
        EditText budget = (EditText)findViewById(R.id.budget);
        EditText employees = (EditText)findViewById(R.id.empl);
        EditText why = (EditText)findViewById(R.id.why);
        EditText phoneno = (EditText)findViewById(R.id.phone);
        EditText mail = (EditText)findViewById(R.id.mail);
        if(idx+1 < list.size()){
            idx++;
            name.setText(list.get(idx).get(0).toString());
            budget.setText(list.get(idx).get(1).toString());
            employees.setText(list.get(idx).get(2).toString());
            why.setText(list.get(idx).get(3).toString());
            phoneno.setText(list.get(idx).get(4).toString());
            mail.setText(list.get(idx).get(5).toString());
        }
        else{
            Toast.makeText(this, "No new applications", Toast.LENGTH_SHORT).show();
        }
    }
}
