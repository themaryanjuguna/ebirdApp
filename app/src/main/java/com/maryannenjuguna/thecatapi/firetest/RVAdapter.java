package com.maryannenjuguna.thecatapi.firetest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

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
        vh.txtOption.setOnClickListener(v -> {

            PopupMenu popupMenu = new PopupMenu(context,vh.txtOption);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item -> {

                switch (item.getItemId()) {


                    case R.id.menuEdit:
                        Intent intent = new Intent(context, firetestActivity.class);
                        intent.putExtra("EDIT", emp);
                        context.startActivity(intent);

                        break;

                    case R.id.menuRemove:
                        DAOEmployee dao = new DAOEmployee();
                        dao.delete(emp.getKey()).addOnSuccessListener(suc->{

                        Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                        notifyItemRemoved(position);

                    }).addOnFailureListener(er ->

                    {
                        Toast.makeText(context,""+er.getMessage(), Toast.LENGTH_SHORT).show();
                    });

                        break;
                }
                return false;
            });
            popupMenu.show();
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
