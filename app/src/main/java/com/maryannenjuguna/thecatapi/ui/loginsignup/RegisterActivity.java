package com.maryannenjuguna.thecatapi.ui.loginsignup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.ui.BreedsActivity;
import com.maryannenjuguna.thecatapi.ui.MainActivity;

import java.util.Arrays;
import java.util.List;


public class RegisterActivity extends AppCompatActivity {

    EditText registerEmail, passwordRegister, confirmPasswordReg;
    TextView loginTV;
    Button registerBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginTV=findViewById(R.id.loginTV);

        registerEmail = findViewById(R.id.registerEmail);
        passwordRegister = findViewById(R.id.passwordRegister);
        confirmPasswordReg = findViewById(R.id.confirmPasswordReg);
        registerBtn = findViewById(R.id.registerBtn);
        progressDialog= new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();


        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {

        String email = registerEmail.getText().toString();
        String password = passwordRegister.getText().toString();
        String confirmPassword = confirmPasswordReg.getText().toString();


        if(!email.matches(emailPattern)){
            registerEmail.setError("Enter a valid email address");
        }else if(password.isEmpty() || password.length()<6){

            passwordRegister.setError("Enter more than 6 characters");
        }else if(!password.equals(confirmPassword)) {

            confirmPasswordReg.setError("Password does not match");
        } else{

            progressDialog.setMessage("Please what while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }else{

                       progressDialog.dismiss();
                       Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {

        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}