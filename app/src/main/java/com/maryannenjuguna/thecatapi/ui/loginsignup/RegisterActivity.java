package com.maryannenjuguna.thecatapi.ui.loginsignup;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.ui.BreedsActivity;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn = findViewById(R.id.loginTV);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, BreedsActivity.class));
            }
        });

    }
}