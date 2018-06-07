package ru.sberleasing.model.persistance

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import ru.sberleasing.model.data.User

@Dao
interface UserDao {

    @get:Query("SELECT * from users LIMIT 1")
    val user: User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("DELETE FROM users")
    fun nukeTable()
}
