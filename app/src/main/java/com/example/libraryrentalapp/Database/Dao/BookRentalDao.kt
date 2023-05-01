package com.example.libraryrentalapp.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.libraryrentalapp.Database.Model.BookRental


@Dao
interface BookRentalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookRental: BookRental)

    @Delete
    suspend fun delete(bookRental: BookRental)

    @Update
    suspend fun update(bookRental: BookRental)

    @Query("SELECT * FROM book_rental_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BookRental>>

    @Query("SELECT * FROM book_rental_table where id is :id")
    fun getRentedBookById(id:Int): LiveData<BookRental>

    @Query("SELECT * FROM book_rental_table where rent_id is :id")
    fun getRentedBookByRentedId(id:String): LiveData<BookRental>

    @Query("DELETE FROM book_rental_table")
    suspend fun deleteAllEntries()

}