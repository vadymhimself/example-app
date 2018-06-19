package ru.sberleasing.model.persistance

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Entity(tableName = "cities")
data class City(
        @field:PrimaryKey val name: String,
        val countryCode: String
)

@Dao
interface CityDao {
    @Query("SELECT * from cities")
    fun getAllCities() : Flowable<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: Collection<City>)

    @Query("DELETE FROM cities")
    fun nukeTable()
}

internal fun seedCities() = arrayListOf(
        City("Chicago", "US"),
        City("New York", "US"),
        City("Moscow", "RU"),
        City("Kiev", "UK"),
        City("Amsterdam", "NE"),
        City("Tokyo", "JA")
)