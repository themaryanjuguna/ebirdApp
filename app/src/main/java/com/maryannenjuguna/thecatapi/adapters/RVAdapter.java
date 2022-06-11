package com.maryannenjuguna.thecatapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.firetest.Employee;
import com.maryannenjuguna.thecatapi.firetest.EmployeeVH;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    //Display info
    private Context context;
    ArrayList<Employee> list = new ArrayList<>();
    public RVAdapter(Context ctx){

        this.context = ctx;
    }

    public void setItems(ArrayList<Employee> emp){

        list.addAll(emp);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fireitem,parent, false);
        return new EmployeeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EmployeeVH vh = (EmployeeVH) holder;
        Employee emp = list.get(position);
        vh.txtName.setText(emp.getName());
        vh.txtPosition.setText(emp.getPosition());


    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
