package com.example.androidquizz.database;


import com.example.androidquizz.database.dao.UserDao;
import com.example.androidquizz.models.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
