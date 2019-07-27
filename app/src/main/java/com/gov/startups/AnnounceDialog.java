package com.gov.startups;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AnnounceDialog extends Dialog implements View.OnClickListener {
    public AnnounceDialog(Activity a) {
        super(a);
        this.c = a;
    }

    public Activity c;
    public Dialog d;
    public Button yes, no;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.announcement_dialog);
        yes = findViewById(R.id.announce);
        no = findViewById(R.id.cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        switch (v.getId()) {
            case R.id.announce:
                EditText title = findViewById(R.id.title);
                EditText text = findViewById(R.id.text);
                Map<String, Object> data = new HashMap<>();
                data.put("Title", title.getText().toString());
                data.put("Text", text.getText().toString());
                data.put("Date",new Timestamp(new Date()));

                db.collection("Announcements")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(c, "Success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                dismiss();
                break;
            case R.id.cancel:
                Toast.makeText(c, "Cancelled", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
