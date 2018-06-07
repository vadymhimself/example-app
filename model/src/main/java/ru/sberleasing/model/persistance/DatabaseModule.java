package ru.sberleasing.model.persistance;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    Database provideDatabase(Context context) {
        return Room.databaseBuilder(context, Database.class, "maindb")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(Database database) {
        return database.userDao();
    }

}
