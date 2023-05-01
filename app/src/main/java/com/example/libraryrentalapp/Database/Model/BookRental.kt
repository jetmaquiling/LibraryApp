package com.example.libraryrentalapp.Database.Model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_rental_table")
class BookRental (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title :String,
    var img:String,
    var img_type:String,
    var category:String,
    var description:String,
    var date_rented:String,
    var date_to_return:String,
    var rent_id:String,
)