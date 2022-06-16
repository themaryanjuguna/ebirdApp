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

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);

        progressDialog= new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        TextView btn = findViewById(R.id.signupTV);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performLogin();
            }
        });


      /*  if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
*/

    }

    private void performLogin() {

        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();


        if(!email.matches(emailPattern)){
            loginEmail.setError("Enter a valid email address");
        }else if(password.isEmpty() || password.length()<6){

            loginPassword.setError("Enter more than 6 characters");

        } else{

            progressDialog.setMessage("Please what while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    }else{

                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }

    private void sendUserToNextActivity() {

        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

 }
