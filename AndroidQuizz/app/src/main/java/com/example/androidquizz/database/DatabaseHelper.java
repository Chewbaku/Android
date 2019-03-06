package com.example.androidquizz.database;

import com.example.androidquizz.QuizApplication;
import com.example.androidquizz.database.dao.StatisticsDao;
import com.example.androidquizz.database.dao.UserDao;

import androidx.room.Room;

public class DatabaseHelper {

    static DatabaseHelper instance = null;
    private final UserDatabase userDatabase;
    private final StatisticsDatabase statisticsDatabase;

    public static DatabaseHelper getInstance(){
        if (instance == null){
            instance = new DatabaseHelper();
        }
        return instance;
    }

    public UserDao getUserDao(){
        return userDatabase.userDao();
    }

    public StatisticsDao getStatisticsDao(){
        return statisticsDatabase.statisticsDao();
    }

    public DatabaseHelper(){
        this.userDatabase = Room.databaseBuilder(QuizApplication.getContext(), UserDatabase.class,"users").fallbackToDestructiveMigration().build();
        this.statisticsDatabase = Room.databaseBuilder(QuizApplication.getContext(), StatisticsDatabase.class,"statistics").build();
    }
}
