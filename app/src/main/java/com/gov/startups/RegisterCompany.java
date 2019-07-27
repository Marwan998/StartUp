package com.gov.startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class RegisterCompany extends AppCompatActivity implements OnClickListener {

    private Button submitbut;
    private Button cancelbut;
    private EditText comtxt;
    private EditText parttxt;
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

        submitbut = (Button)findViewById(R.id.submit);
        submitbut.setOnClickListener(this);

        cancelbut = (Button)findViewById(R.id.cancel);
        cancelbut.setOnClickListener(this);

        comtxt = (EditText)findViewById(R.id.comptxt);
        parttxt = (EditText)findViewById(R.id.partxt);
        loctxt = (EditText)findViewById(R.id.loctxt);
        equttxt = (EditText)findViewById(R.id.equtxt);
        goaltxt = (EditText)findViewById(R.id.goaltxt);
        passtxt = (EditText)findViewById(R.id.passtxt);
        emailtxt = (EditText)findViewById(R.id.emailtxt);
        phonetxt = (EditText)findViewById(R.id.phonetxt);



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


                String companyN = comtxt.getText().toString();
                String partN = parttxt.getText().toString();
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
                else if(partN.equals(""))
                {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your parteners name", Toast.LENGTH_SHORT).show();
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

                break;
        }
    }
    
    public void onDestroy()
    {
        super.onDestroy();

    }


}