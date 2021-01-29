package com.example.firebasestart2.screens.addition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.firebasestart2.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddPersonActivity extends AppCompatActivity {
    private FirebaseFirestore database;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        editTextName = findViewById(R.id.editTextTextName);
        editTextLastName = findViewById(R.id.editTextTextLastName);
        editTextAge = findViewById(R.id.editTextTextAge);


        database = FirebaseFirestore.getInstance();

    }

    public void onClickAddButtonPressed(View view) {


    }
    private boolean isCorrect(){
        if(!editTextName.getText().toString().equals("") &&
                !editTextLastName.getText().toString().equals("") &&
                !editTextAge.getText().toString().equals("")){
            return true;
        }
        return false;

    }
}