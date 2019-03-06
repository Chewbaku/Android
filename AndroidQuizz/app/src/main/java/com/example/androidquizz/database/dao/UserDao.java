package com.example.androidquizz.database.dao;

import com.example.androidquizz.models.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createUser(User user);

    @Query("Select * FROM User WHERE id = :userId")
    LiveData<User> getUser(long userId);
}
