package com.example.libraryrentalapp.Database.Model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_resources_table")
@Dao
class BookResources (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title :String,
    var img:String,
    var img_type:String,
    var category:String,
    var description:String,
    var isAvailable:Boolean,
)