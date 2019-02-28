package com.example.androidquizz.database.dao;

import com.example.androidquizz.models.Statistics;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StatisticsDao {

    @Query("SELECT * FROM Statistics WHERE userId = :userId")
    LiveData<List<Statistics>> getItems(long userId);

    @Insert
    long insertStatistics(Statistics statistics);

    @Update
    int update(Statistics statistics);

    @Query("DELETE FROM Statistics Where id = :statisticsId")
    int deleteStatisctics(long statisticsId);
}
