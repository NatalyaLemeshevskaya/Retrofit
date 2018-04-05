package com.example.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.data.entity.User;

@Database(entities = {User.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
