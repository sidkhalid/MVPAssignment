package com.example.sidkh.myapplication;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@android.arch.persistence.room.Dao
public interface Dao {

    @Query("Select * from User WHERE name LIKE :Name AND User_Password LIKE :password")
    User getUser(String Name, String password);
    @Query("Select name from User WHERE name LIKE :Name")
    String getUserStatus(String Name);
    @Insert
    void Insert(User u);
    @Delete
    void Delete(User u);
}
