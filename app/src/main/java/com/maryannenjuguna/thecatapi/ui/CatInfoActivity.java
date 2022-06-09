package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.models.Image;
import com.squareup.picasso.Picasso;

import java.util.jar.Attributes;

public class CatInfoActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, description, temperament, life_span;
    Button moreFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_info);

        Intent  data = getIntent();

        imageView = findViewById(R.id.imageView);
        name= findViewById(R.id.name);
        description = findViewById(R.id.description);
        temperament = findViewById(R.id.temperament);
        life_span = findViewById(R.id.life_span);

        moreFacts= findViewById(R.id.moreFacts);


        //set data
        name.setText(data.getStringExtra("name"));
        description.setText(data.getStringExtra("description"));
        temperament.setText(data.getStringExtra("temperament"));
        life_span.setText(data.getStringExtra("life_span"));


        Picasso.get().load(data.getStringExtra("imageUrl")).into(imageView);

        moreFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri catUri = Uri.parse(data.getStringExtra("wikipedia_url"));
                Intent browser = new Intent(Intent.ACTION_VIEW,catUri);
                startActivity(browser);
            }
        });


    }
}