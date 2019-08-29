package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.editText).setZ(2);
        findViewById(R.id.editText2).setZ(2);
        findViewById(R.id.register).setZ(2);
        findViewById(R.id.textView).setZ(2);

        ImageView iv = findViewById(R.id.imageView4);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        iv.getLayoutParams().height=size.y/2;

        Intent i = getIntent();
        if(i.getIntExtra("type",0)==0){
            TextView tv = findViewById(R.id.register);
            tv.setVisibility(View.VISIBLE);
            tv.setTag(1);
            TextView head =findViewById(R.id.head);
            head.setText("StartUp");
        }
        else if(i.getIntExtra("type",0)==2){
            TextView tv = findViewById(R.id.register);
            tv.setText("Register Sponsor");
            tv.setVisibility(View.VISIBLE);
            tv.setTag(2);
            TextView head =findViewById(R.id.head);
            head.setText("Sponsor");
        }
    }
    String path="Companies";
    public void login(View view) {
        EditText et = findViewById(R.id.editText);
        final String name = et.getText().toString();
        final EditText et2 = findViewById(R.id.editText2);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        cr.whereEqualTo("ID",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot doc = task.getResult();
                    if(doc.isEmpty()){
                        Toast.makeText(Login.this, "User Not Found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String pass = document.getData().get("Password")+"";
                            if(et2.getText().toString().equals(pass)){
                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                switch (path){
                                    case "Companies":
                                        Intent x0 = new Intent(getApplicationContext(), StartupMain.class);
                                        x0.putExtra("name",name);
                                        startActivity(x0);
                                        break;
                                    case "Government":
                                        Intent x1 = new Intent(getApplicationContext(),GovMain.class);
                                        startActivity(x1);
                                        break;
                                    case "Sponsors":
                                        Intent x2 = new Intent(getApplicationContext(), SponsorMain.class);
                                        x2.putExtra("name",name);
                                        startActivity(x2);
                                        break;
                                }
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
                Intent intent2 = new Intent(getApplicationContext(),RegisterSponsor.class);
                startActivity(intent2);
                break;
        }
    }

    public void back(View view) {
        finish();
    }
}