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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class supportRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_request);
    }
    public void back(View view) {
        finish();
    }

    public void submit(View view) {
        EditText budget = findViewById(R.id.budget);
        EditText employees = findViewById(R.id.empl);
        EditText why = findViewById(R.id.why);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Map<Object, Object> request = new HashMap<>();
        request.put("ID",getIntent().getStringExtra("name"));
        request.put("budget",budget.getText().toString());
        request.put("employees",employees.getText().toString());
        request.put("why",why.getText().toString());
        db.collection("Companies")
                .whereEqualTo("name", getIntent().getStringExtra("name"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                request.put("phone",document.get("Phone").toString());
                                request.put("email",document.get("email").toString());
                            }
                        } else {
                        }
                    }
                });
        db.collection("Support_Requests").document().set(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(supportRequest.this, "Application submitted", Toast.LENGTH_SHORT).show();
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(supportRequest.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void cancel(View view) {
        EditText budget = findViewById(R.id.budget);
        EditText employees = findViewById(R.id.empl);
        EditText why = findViewById(R.id.why);
        budget.setText("");
        employees.setText("");
        why.setText("");
    }

    public void next(View view) {
    }
}
