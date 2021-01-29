package com.example.firebasestart2.screens.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebasestart2.R;
import com.example.firebasestart2.adapters.MainAdapter;
import com.example.firebasestart2.pojo.Person;
import com.example.firebasestart2.screens.addition.AddPersonActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewPersons);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

         database = FirebaseFirestore.getInstance();
         adapter = new MainAdapter();
         recyclerView.setAdapter(adapter);




    }

    public void onClickFloatingButtonPressed(View view) {
        Intent intent = new Intent(this, AddPersonActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Person> people= new ArrayList<>();

        database.collection("people").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        Person person = doc.toObject(Person.class);
                        people.add(person);
                    }
                    adapter.setPeople(people);
                }else {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}