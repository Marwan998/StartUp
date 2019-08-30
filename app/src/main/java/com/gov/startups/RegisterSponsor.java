package com.gov.startups;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterSponsor extends AppCompatActivity implements OnClickListener{

    // Variable Declaration should be in onCreate()
    private Button mSubmit;
    private Button mCancel;

    private EditText mFname;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    private EditText mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sponsor);

        //Assignment of UI fields to the variables
        mSubmit = (Button)findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);

        mCancel = (Button)findViewById(R.id.cancel);
        mCancel.setOnClickListener(this);

        mFname = (EditText)findViewById(R.id.efname);

        mUsername = (EditText)findViewById(R.id.reuname);
        mPassword = (EditText)findViewById(R.id.repass);
        mEmail = (EditText)findViewById(R.id.eemail);
        mPhone = findViewById(R.id.ephone);

    }



    public void onClick(View v)
    {

        switch(v.getId()){

            case R.id.cancel:
                Intent i = new Intent(getBaseContext(), Login.class);
                startActivity(i);
                //finish();
                break;

            case R.id.submit:


                String fname = mFname.getText().toString();

                String uname = mUsername.getText().toString();
                String pass = mPassword.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();

                boolean invalid = false;

                if(fname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your Firstname", Toast.LENGTH_SHORT).show();
                }
                else

                if(uname.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Username", Toast.LENGTH_SHORT).show();
                }
                else


                if(pass.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();

                }
                else
                if(email.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
                }
                if(phone.equals("") || phone.length() <10)
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }
                if(!invalid){
                    Map<String, Object> sponsor = new HashMap<>();
                    sponsor.put("ID",uname);
                    sponsor.put("password",pass);
                    sponsor.put("name",fname);
                    sponsor.put("email",email);
                    sponsor.put("phone",phone);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Sponsor_Requests").document().set(sponsor)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterSponsor.this, "Application submitted", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterSponsor.this, "Registration failed", Toast.LENGTH_SHORT).show();
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