package com.example.androidquizz.database.dao;

import com.example.androidquizz.models.Statistics;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StatisticsDao {

    @Query("SELECT * FROM Statistics WHERE userId = :userId")
    Statistics getStatistics(long userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStatistics(Statistics statistics);

    @Update
    int updateStatistics(Statistics statistics);

    @Query("DELETE FROM Statistics Where id = :statisticsId")
    int deleteStatisctics(long statisticsId);
}
