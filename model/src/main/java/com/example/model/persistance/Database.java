package com.example.model.persistance;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@android.arch.persistence.room.Database(
        entities = {City.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract CityDao cityDao();
}
