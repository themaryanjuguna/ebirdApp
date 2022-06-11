package com.maryannenjuguna.thecatapi.firetest;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmployee {
// CRUD Insert, Read, Update & Delete Data in the database. Define Business Logic DAO class [Insert]

    //Connect to Database
    private DatabaseReference databaseReference;
    public DAOEmployee() {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Employee.class.getSimpleName());
    }
//insert Value returntype
    public Task<Void> add(Employee emp) {

        //if(emp == null)//throw exception
       return databaseReference.push().setValue(emp);
    }
// update String
    public Task<Void> update(
            String key,
            HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

//Delete String
    public Task<Void> delete(String key){
        return databaseReference.child(key).removeValue();

    }

    //Get info from database
    public Query get(String key) {
        if(key == null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

}
