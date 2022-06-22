package com.maryannenjuguna.thecatapi.firetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.maryannenjuguna.thecatapi.R;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DAOEmployee dao;
   RVAdapter adapter;
   String key = null;
   boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvactivity);

      /*  swipeRefreshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.viewRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        dao = new DAOEmployee();

        FirebaseRecyclerOptions<Employee> option = new FirebaseRecyclerOptions.Builder<Employee>()
                .setQuery(dao.get(), new SnapshotParser<Employee>());
*/
       swipeRefreshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.viewRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOEmployee();

       loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy); {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int totalItem = linearLayoutManager.getItemCount();
                    int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    if(totalItem<lastVisible+3){
                        isLoading=true;
                        loadData();
                    }
                }
            }
        });
    }

    private void loadData() {

        swipeRefreshLayout.setRefreshing((true));
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Employee> emps = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()) {
                    Employee emp = data.getValue(Employee.class);
                    emp.setKey(data.getKey());
                    emps.add(emp);
                    key = data.getKey();
                }
                adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                isLoading =false;
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
    }
