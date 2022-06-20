package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.ui.loginsignup.LoginActivity;
import com.maryannenjuguna.thecatapi.ui.loginsignup.RegisterActivity;

public class SplashActivity extends AppCompatActivity {

    boolean islottie = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LottieAnimationView lottie = findViewById(R.id.lottie);
        lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(islottie){
                    lottie.setSpeed(-1);
                    lottie.playAnimation();
                    islottie = false;
                } else{

                    lottie.setSpeed(2);
                    lottie.playAnimation();
                    islottie = true;
                }
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //calling main activity to load after loading screen
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish(); //finish loading screen activity
            }
            //call mainActivity after 1 seconds
        }, 5000);

    }
    }
