package com.gov.startups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    int type = -1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        type = getIntent().getIntExtra("type",-1);
        final EditText editText = findViewById(R.id.search_et);
        listView = findViewById(R.id.listView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                CollectionReference data = db.collection("Companies");
                data.orderBy("ID").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            String query = charSequence.toString().toLowerCase();
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int c = 0;
                                        ArrayList<String> arrayList = new ArrayList<>();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String result = document.getData().get("ID").toString().toLowerCase();
                                            if (result.contains(query) && !(query.equals("") || query.equals(" "))) {
                                                c++;
                                                arrayList.add(result);
                                                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                                                listView.setAdapter(arrayAdapter);
                                                Log.d("database_result", document.getId() + " => " + result);
                                            }
                                        }
                                        if(c==0){
                                            ArrayList<String> ar = new ArrayList<>();
                                            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ar);
                                            listView.setAdapter(arrayAdapter);}


                                }
                                else {
                                    Log.d("database_result", "Error getting documents: ", task.getException());
                                }
                            }
                        });

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}
