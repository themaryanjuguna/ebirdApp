package com.maryannenjuguna.thecatapi.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;
import com.maryannenjuguna.thecatapi.ui.BreedsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<TheCatBreedSearchResponse> cats;
    Context context;

    public RecyclerViewAdapter(List<TheCatBreedSearchResponse> cats, Context context) {
        this.cats = cats;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.catData(cats.get(position));


       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BreedsActivity.class);
                if(intent !=null)
                {
                    try {
                        Bundle bundle = new Bundle();

                        bundle.putString("id", cats.get(position).getId());
                        bundle.putString("name", cats.get(position).getName());
                        bundle.putString("description", cats.get(position).getDescription());
                        bundle.putString("image", cats.get(position).getImage().getUrl());
                        bundle.putString("wikipedia", cats.get(position).getWikipediaUrl());

                        Picasso.get().load(cats.get(position).getImage().getUrl()).into(holder.kittyImageView);

                        intent.putExtras(bundle);
                        context.startActivity(intent);


                    }catch (NullPointerException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        Button myLikeBtn;
        ImageView catImage, kittyImageView;
        TextView catName, catDescription, catTemperament, catLifeSpan, kittyId, kittyName, kittyDescription, kittyWikipedia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catImage = itemView.findViewById(R.id.catImage);
            catName = itemView.findViewById(R.id.name);
            catDescription = itemView.findViewById(R.id.description);
            catTemperament = itemView.findViewById(R.id.temperament);
            catLifeSpan = itemView.findViewById(R.id.life_span);
            linearLayout = itemView.findViewById(R.id.mainLayout);

        }

        public void catData(TheCatBreedSearchResponse maryanne){

            catName.setText(maryanne.getName());
            catDescription.setText(maryanne.getDescription());
            catTemperament.setText(maryanne.getTemperament());
            catLifeSpan.setText(maryanne.getLifeSpan());
            Picasso.get().load(maryanne.getImage().getUrl()).into(catImage);


        }
    }
}