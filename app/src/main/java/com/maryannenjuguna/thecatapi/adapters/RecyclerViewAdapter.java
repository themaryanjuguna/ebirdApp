package com.maryannenjuguna.thecatapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.models.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Datum> cats;
    Context context;

    public RecyclerViewAdapter(List<Datum> cats, Context context) {
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.catData(cats.get(position));

    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView catImage;
        TextView catName, catDescription, catTemperament, catLifeSpan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catImage = itemView.findViewById(R.id.imageView);
            catName = itemView.findViewById(R.id.name);
          //  catDescription = itemView.findViewById(R.id.description);
            //catTemperament = itemView.findViewById(R.id.temperament);
            //catLifeSpan = itemView.findViewById(R.id.life_span);

        }

        public void catData(Datum maryanne){

            Picasso.get().load(maryanne.getAnimeImg()).into(catImage);
            catName.setText(maryanne.getAnimeName());

        }
    }
}
