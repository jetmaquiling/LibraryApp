package com.example.libraryrentalapp.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.libraryrentalapp.Database.Model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table where id is :id")
    fun getUserByID(id:Int): LiveData<User>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllEntries()

}