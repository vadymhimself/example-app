package com.example.model.persistance

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "maindb")
                .fallbackToDestructiveMigration()
                .addCallback(object: RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        Executors.newSingleThreadExecutor().execute({
                            provideDatabase(context).cityDao().apply {
                                insertAll(seedCities()) // TODO seeding done wrong
                            }
                        })
                    }
                })
                .build()
    }

    @Provides
    @Singleton
    internal fun provideCityDao(database: Database): CityDao {
        return database.cityDao()
    }

}
