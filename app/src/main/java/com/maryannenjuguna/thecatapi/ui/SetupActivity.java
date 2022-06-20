package com.maryannenjuguna.thecatapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.adapters.UserDetails;

public class SetupActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView welcomeTV, showFullNameTV, showEmailTV, showDOBTV, showGenderTV, showPhoneNoTV;
    private ProgressBar progressBar;
    private String fullName, email,dob, gender, phoneNo;
    private Button mSaveBtn;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        getSupportActionBar().setTitle("Feral Profile");

        welcomeTV = findViewById(R.id.welcome);
        showFullNameTV = findViewById(R.id.showFullName);
        showEmailTV = findViewById(R.id.showEmail);
        showDOBTV = findViewById(R.id.showDOB);
        showGenderTV = findViewById(R.id.showGender);
        showPhoneNoTV= findViewById(R.id.showPhoneNo);
        progressBar = findViewById(R.id.progressBar);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser(); //getcureentuser who is logged in
        if(firebaseUser == null){
//check if the user is null or not
            Toast.makeText(SetupActivity.this,"Something went wrong, user details are not available at the moment", Toast.LENGTH_LONG).show();
        }else {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }


    }

    private void showUserProfile(FirebaseUser firebaseUser) {

        String userId = firebaseUser.getUid();

        //Extracting  User Referrence from Database for "RegisteredUsers"

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserDetails readUserDetails = snapshot.getValue(UserDetails.class);
                if(readUserDetails != null){

                    fullName = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();
                    dob = readUserDetails.dob;
                    gender = readUserDetails.gender;
                    phoneNo = readUserDetails.phoneNo;

                    welcomeTV.setText("Welcome," + fullName + "!");
                    showFullNameTV.setText(fullName);
                    showEmailTV.setText(email);
                    showDOBTV.setText(dob);
                    showGenderTV.setText(gender);
                    showPhoneNoTV.setText(phoneNo);

                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(SetupActivity.this,"Something Went wrong,", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}




