package com.example.androidquizz.database;

import android.content.ContentValues;
import android.content.Context;

import com.example.androidquizz.database.dao.StatisticsDao;
import com.example.androidquizz.database.dao.UserDao;
import com.example.androidquizz.models.Statistics;
import com.example.androidquizz.models.User;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Statistics.class, User.class}, version = 1, exportSchema = false)
public abstract class SaveMyDatabase extends RoomDatabase {

    //Singleton: create ONCE ONLY the class responsible for our database and get only one and only instance of reference
    private static volatile SaveMyDatabase INSTANCE;

    //Dao
    public abstract StatisticsDao statisticsDao();
    public abstract UserDao userDao();

    //Instance
    public static SaveMyDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SaveMyDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase()).build();
                }
            }
        }
        return INSTANCE;
    }


    private static Callback prepopulateDatabase(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("login", "Benjamin");

                db.insert("User", OnConflictStrategy.IGNORE, contentValues);
            }


        };
    }



}
