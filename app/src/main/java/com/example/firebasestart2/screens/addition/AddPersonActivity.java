package com.example.firebasestart2.screens.addition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasestart2.R;
import com.example.firebasestart2.pojo.Person;
import com.example.firebasestart2.screens.start.MainActivity;
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
        if(!isCorrect()){
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }
            Person person = new Person();
            person.setName(editTextName.getText().toString());
            person.setLastName(editTextLastName.getText().toString());
            int age;
            try {
                 age = Integer.parseInt(editTextAge.getText().toString());
            }catch (NumberFormatException e){
                Toast.makeText(this, "Введите возраст целым числом", Toast.LENGTH_SHORT).show();
                editTextAge.getText().clear();
                return;
            }
            person.setAge(age);
            database.collection("people").add(person);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


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