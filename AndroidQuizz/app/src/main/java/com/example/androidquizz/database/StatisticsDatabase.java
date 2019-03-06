package com.example.androidquizz.database;


import com.example.androidquizz.database.dao.StatisticsDao;
import com.example.androidquizz.models.Statistics;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Statistics.class}, version = 1)
public abstract class StatisticsDatabase extends RoomDatabase {
    public abstract StatisticsDao statisticsDao();
}
