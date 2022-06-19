package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.appcompat.widget.Toolbar;
import android.os.Build;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.maryannenjuguna.thecatapi.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private CircleImageView circleImageView;
    private EditText mProfileName;
    private Button mSaveBtn;
    private FirebaseAuth auth;
    private Uri mImageUri;
    private Object toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Toolbar setUpToolbar =findViewById(R.id.toolbar);
        setSupportActionBar(setUpToolbar);
        getSupportActionBar().setTitle("Feral Profile");

        circleImageView = findViewById(R.id.profileImage);
        mProfileName =  findViewById(R.id.profileName);
        mSaveBtn = findViewById(R.id.saveBtn);

        auth = FirebaseAuth.getInstance();

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


}