package com.example.libraryrentalapp.Database

import androidx.lifecycle.LiveData
import com.example.libraryrentalapp.Database.Dao.BookRentalDao
import com.example.libraryrentalapp.Database.Dao.BookResourcesDao
import com.example.libraryrentalapp.Database.Dao.UserDao
import com.example.libraryrentalapp.Database.Model.BookRental
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.Database.Model.User

class MainRepository(private val userDao: UserDao, private val bookResourcesDao: BookResourcesDao, private val bookRentalDao: BookRentalDao) {
    val readAllUserData: LiveData<List<User>> = userDao.readAllData()

    val readAllBookResourcesData: LiveData<List<BookResources>> = bookResourcesDao.readAllData()

    val readAllBookRentalDao: LiveData<List<BookRental>> = bookRentalDao.readAllData()


    //User

    suspend fun insert(user: User){
        println("Insert in process")
        userDao.insert(user)
    }

    suspend fun delete(user: User){
        println("Delete in process")
        userDao.delete(user)
    }

    suspend fun update(user: User) {
        println("Update in process")
        userDao.update(user)
    }

    suspend fun deleteAllEntries(){
        userDao.deleteAllEntries()
    }

    fun getUserByID(id:Int):LiveData<User>{
        return userDao.getUserByID(id)
    }



    //BookRental

    suspend fun insert(bookRental: BookRental){
        bookRentalDao.insert(bookRental)
    }

    suspend fun delete(bookRental: BookRental){
        println("Delete in process")
        bookRentalDao.delete(bookRental)
    }

    suspend fun update(bookRental: BookRental) {
        println("Update in process")
        bookRentalDao.update(bookRental)
    }

    suspend fun deleteAllBookRental(){
        bookRentalDao.deleteAllEntries()
    }

    fun getBookRentalByID(id:Int):LiveData<BookRental>{
        return bookRentalDao.getRentedBookById(id)
    }

    fun getBookRentalByRentID(id:String):LiveData<BookRental>{
        return bookRentalDao.getRentedBookByRentedId(id)
    }

    //BookResources

    suspend fun insert(bookResources: BookResources){
        bookResourcesDao.insert(bookResources)
    }

    suspend fun delete(bookResources: BookResources){
        println("Delete in process")
        bookResourcesDao.delete(bookResources)
    }

    suspend fun update(bookResources: BookResources) {
        println("Update in process")
        bookResourcesDao.update(bookResources)
    }

    suspend fun deleteAllBookResources(){
        bookResourcesDao.deleteAllEntries()
    }

    fun getBookResourcesByID(id:Int):LiveData<BookResources>{
        return bookResourcesDao.getBookResourceById(id)
    }





}