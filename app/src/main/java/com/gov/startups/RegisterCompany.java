package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterCompany extends AppCompatActivity implements OnClickListener {

    private Button submitbut;
    private Button cancelbut;
    private EditText comtxt;
    private EditText utxt;
    private EditText loctxt;
    private EditText equttxt;
    private EditText goaltxt;
    private EditText passtxt;
    private EditText emailtxt;
    private EditText phonetxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        submitbut = (Button)findViewById(R.id.csubmit);
        submitbut.setOnClickListener(this);

        cancelbut = (Button)findViewById(R.id.ccancel);
        cancelbut.setOnClickListener(this);

        comtxt = (EditText)findViewById(R.id.comptxt);
        loctxt = (EditText)findViewById(R.id.loctxt);
        equttxt = (EditText)findViewById(R.id.equtxt);
        goaltxt = (EditText)findViewById(R.id.goaltxt);
        utxt = (EditText)findViewById(R.id.utext);
        emailtxt = (EditText)findViewById(R.id.emailtxt);
        phonetxt = (EditText)findViewById(R.id.phonetxt);
        passtxt = findViewById(R.id.passtxt);



    }
    public void onClick(View v)
    {

        switch(v.getId()){

            case R.id.ccancel:
                Intent i = new Intent(getBaseContext(), Login.class);
                startActivity(i);
                //finish();
                break;

            case R.id.csubmit:


                String companyN = comtxt.getText().toString();
                String userN = utxt.getText().toString();
                String locN = loctxt.getText().toString();
                String equN = equttxt.getText().toString();
                String goalN = goaltxt.getText().toString();
                String passN = passtxt.getText().toString();
                String emailN = emailtxt.getText().toString();
                String phoneN = phonetxt.getText().toString();



                boolean invalid = false;

                if(companyN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your Company name", Toast.LENGTH_SHORT).show();
                }
                else if(userN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your user name", Toast.LENGTH_SHORT).show();
                }
                else if(locN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Company Location", Toast.LENGTH_SHORT).show();

                }
                else if(equN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter Equity capital", Toast.LENGTH_SHORT).show();
                }

                else if(goalN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter Company goals", Toast.LENGTH_SHORT).show();
                }

                else if(passN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                }

                else if(emailN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
                }

                else if(phoneN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }
                if(!invalid){
                    Map<String, Object> startup = new HashMap<>();
                    startup.put("ID",userN);
                    startup.put("Name",companyN);
                    startup.put("Password",passN);
                    startup.put("email",emailN);
                    startup.put("location",locN);
                    startup.put("equity",equN+"");
                    startup.put("goal",goalN);
                    startup.put("phone",phoneN);
                    startup.put("Logo","https://logosbynick.com/wp-content/uploads/2018/03/final-logo-example.png");
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Company_Requests").document().set(startup)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterCompany.this, "Application submitted", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterCompany.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
    public void onDestroy()
    {
        super.onDestroy();

    }


}