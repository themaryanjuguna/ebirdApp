package com.maryannenjuguna.thecatapi.firetest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maryannenjuguna.thecatapi.R;

public class EmployeeVH extends RecyclerView.ViewHolder {

    public TextView txtName, txtPosition;
    public EmployeeVH(@NonNull View itemView) {

        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtPosition = itemView.findViewById(R.id.txtPosition);


    }
}
