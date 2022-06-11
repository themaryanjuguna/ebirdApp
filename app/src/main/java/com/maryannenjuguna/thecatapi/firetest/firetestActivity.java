package com.maryannenjuguna.thecatapi.firetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maryannenjuguna.thecatapi.R;

import java.util.HashMap;

public class firetestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firetest);

        final EditText editName = findViewById(R.id.editName);
        final EditText editPosition = findViewById(R.id.editPosition);
        Button btn = findViewById(R.id.submitBtn);

    DAOEmployee dao = new DAOEmployee();

        //send event to firebase
        btn.setOnClickListener(v -> {

               Employee emp = new Employee(editName.getText().toString(),
                    editPosition.getText().toString());
            dao.add(emp).addOnSuccessListener(suc ->{

                        Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er ->{

                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });


        // Update Record
        /*HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name", editName.getText().toString());
        hashMap.put("position",editPosition.getText().toString());
        dao.update("-N4HsdYKmB5Uv-X1Ju7Q", hashMap).addOnSuccessListener(suc ->{

                Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->{

                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });*/


            dao.delete("-N4HsdYKmB5Uv-X1Ju7Q").addOnSuccessListener(suc ->{

                Toast.makeText(this, "Record Removed", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->{

                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        Button btnOpen = findViewById(R.id.openBtn);
        btnOpen.setOnClickListener(V->
        {
            Intent intent = new Intent(firetestActivity.this, RVActivity.class);
            startActivity(intent);

        });

    }
}