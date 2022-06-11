package com.maryannenjuguna.thecatapi;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.maryannenjuguna.thecatapi.models.Image;
import com.squareup.picasso.Picasso;

public class TheCatApiAdapter {

    private Context context;
    private String[] mNames;
    private String[] description;
    private String[] temperament;
    private String[] life_span;
    private String imageView;
    private ImageView url;


    public void bindBreeds(Image breeds) {
        Picasso.get().load(breeds.getUrl()).into(url);

    }
}
