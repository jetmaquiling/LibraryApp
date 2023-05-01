package com.example.libraryrentalapp.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.libraryrentalapp.Database.Model.BookResources

@Dao
interface BookResourcesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookResource: BookResources)

    @Delete
    suspend fun delete(bookResource: BookResources)

    @Update
    suspend fun update(bookResource: BookResources)

    @Query("SELECT * FROM book_resources_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BookResources>>

    @Query("SELECT * FROM book_resources_table where id is :id")
    fun getBookResourceById(id: Int): LiveData<BookResources>

    @Query("DELETE FROM book_resources_table")
    suspend fun deleteAllEntries()
}