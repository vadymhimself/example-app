package ru.sberleasing.model.persistance;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import ru.sberleasing.model.data.User;

@android.arch.persistence.room.Database(
        entities = {User.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
}
