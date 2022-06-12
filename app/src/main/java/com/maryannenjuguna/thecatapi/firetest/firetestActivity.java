package com.maryannenjuguna.thecatapi.firetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        Button btnOpen = findViewById(R.id.openBtn);

        btnOpen.setOnClickListener(V->      {
            Intent intent = new Intent(firetestActivity.this, RVActivity.class);
            startActivity(intent);

        });

        DAOEmployee dao = new DAOEmployee();
        Employee emp_edit = (Employee)getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null)
        {
            btn.setText("UPDATE");
            editName.setText(emp_edit.getName());
            editPosition.setText(emp_edit.getPosition());
            btnOpen.setVisibility(View.GONE);
        } else{
            btn.setText("SUBMIT");
            btnOpen.setVisibility(View.VISIBLE);
        }

        //send event to firebase
        btn.setOnClickListener(v -> {

               Employee emp = new Employee(editName.getText().toString(),
                    editPosition.getText().toString());

               if(emp_edit==null) {
                   dao.add(emp).addOnSuccessListener(suc -> {

                       Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
                   }).addOnFailureListener(er -> {

                       Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                   });
               }
               else {

                   // Update Record
                   HashMap<String, Object> hashMap = new HashMap<>();
                   hashMap.put("name", editName.getText().toString());
                   hashMap.put("position", editPosition.getText().toString());
                   dao.update(emp_edit.getKey(), hashMap).addOnSuccessListener(suc -> {

                       Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
                       finish();
                   }).addOnFailureListener(er -> {

                       Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                   });

               }
      /*             dao.delete(emp_edit.getKey(), hashMap).addOnSuccessListener(suc ->{

                       Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();

                   }).addOnFailureListener(er ->{

                       Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                   });
*/
               });

        };

}