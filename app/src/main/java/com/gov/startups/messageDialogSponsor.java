package com.gov.startups;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class messageDialogSponsor extends Dialog implements View.OnClickListener  {
    public messageDialogSponsor(Activity a, int type,String name) {
        super(a);
        this.c = a;
        this.type = type;
        this.name = name;
    }

    public Activity c;
    public Dialog d;
    public Button send, cancel;
    int type;
    String name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message_dialog);
        send = findViewById(R.id.send);
        cancel = findViewById(R.id.cancel);
        send.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }
    String path;
    @Override
    public void onClick(View v) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        switch (type){
            case 1:
                path="Cmp_Messages";
                break;
            case 3:
                path ="Gov_Messages";
                break;
        }
        switch (v.getId()) {
            case R.id.send:
                EditText title = findViewById(R.id.title);
                EditText text = findViewById(R.id.text);
                EditText receiver = findViewById(R.id.receiver);
                Map<String, Object> data = new HashMap<>();
                data.put("Title", title.getText().toString());
                data.put("Message", text.getText().toString());
                data.put("Receiver",receiver.getText().toString());
                data.put("Type",2);
                data.put("Sender",name);
                data.put("Answered",false);

                db.collection(path)
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


