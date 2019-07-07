package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = getIntent();
        if(i.getIntExtra("type",0)==0){
            TextView tv = findViewById(R.id.register);
            tv.setVisibility(View.VISIBLE);
            tv.setTag(1);
        }
        else if(i.getIntExtra("type",0)==2){
            TextView tv = findViewById(R.id.register);
            tv.setText("Register a new sponsor.");
            tv.setVisibility(View.VISIBLE);
            tv.setTag(2);
        }
    }

    public void login(View view) {
        EditText et = findViewById(R.id.editText);
        final EditText et2 = findViewById(R.id.editText2);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String path="Companies";
        switch (getIntent().getIntExtra("type",0)){
            case 0:
                path="Companies";
                break;
            case 1:
                path="Government";
                break;
            case 2:
                path="Sponsors";
                break;

        }
        CollectionReference cr = db.collection(path);
        cr.whereEqualTo("ID",et.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot doc = task.getResult();
                    if(doc.isEmpty()){
                        Toast.makeText(Login.this, "User Not Found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String pass = document.getData().get("password")+"";
                            if(et2.getText().toString().equals(pass)){
                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                } else {
                    Toast.makeText(Login.this, "Connection failed check your internet", Toast.LENGTH_SHORT).show();
                    Log.d("firebase","connection Failed");
                }
            }
        });
    }

    public void register(View view) {
        switch((int)view.getTag()){
            case 1:
                Intent intent = new Intent(getApplicationContext(),RegisterCompany.class);
                startActivity(intent);
                break;
            case 2:
        }
    }
}
