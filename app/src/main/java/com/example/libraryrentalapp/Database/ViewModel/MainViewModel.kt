package com.example.libraryrentalapp.Database.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.libraryrentalapp.Database.MainDatabase
import com.example.libraryrentalapp.Database.MainRepository
import com.example.libraryrentalapp.Database.Model.BookRental
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.Database.Model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val readAllUserData: LiveData<List<User>>

    private val readAllBookRentalData: LiveData<List<BookRental>>

    private val readAllBookResourcesData: LiveData<List<BookResources>>

    private val repository: MainRepository


    init {
        val userDao = MainDatabase.getDatabase(application)!!.userDao()
        val bookResources = MainDatabase.getDatabase(application)!!.bookResourcesDao()
        val bookRental = MainDatabase.getDatabase(application)!!.bookRentalDao()
        repository = MainRepository(userDao,bookResources,bookRental)
        readAllUserData = repository.readAllUserData
        readAllBookRentalData = repository.readAllBookRentalDao
        readAllBookResourcesData = repository.readAllBookResourcesData
    }

    fun getAllUserData(): LiveData<List<User>> {
        return readAllUserData
    }

    fun getAllBookResources(): LiveData<List<BookResources>>{
        return readAllBookResourcesData
    }

    fun getAllBookRental(): LiveData<List<BookRental>>{
        return readAllBookRentalData
    }


    fun getBookResourceById(id:Int): LiveData<BookResources> {
        return repository.getBookResourcesByID(id)
    }

    fun getBookRentalById(id:Int): LiveData<BookRental> {
        return repository.getBookRentalByID(id)
    }

    fun getBookRentalByRentId(id:String): LiveData<BookRental> {
        return repository.getBookRentalByRentID(id)
    }







    //User

    fun insert(user:User) {
        viewModelScope.launch(Dispatchers.IO) {
            println("User added")
            repository.insert(user)
        }
    }

    fun delete(user:User) {
        viewModelScope.launch(Dispatchers.IO){
            println("Attempted to delete ${user.name} @id number: ${user.id}")
            repository.delete(user)
        }
    }


    fun update(user:User) {
        viewModelScope.launch(Dispatchers.IO){
            println("Attempted to update ${user.name} @id number: ${user.id}")
            repository.update(user)
        }
    }


    //Book Resources

    fun insert(bookResources: BookResources) {
        viewModelScope.launch(Dispatchers.IO) {
            println("Book Resources added")
            repository.insert(bookResources)
        }
    }

    fun delete(bookResources:BookResources) {
        viewModelScope.launch(Dispatchers.IO){
            println("BookResources Attempted to delete ${bookResources.title} @id number: ${bookResources.id}")
            repository.delete(bookResources)
        }
    }


    fun update(bookResources:BookResources) {
        viewModelScope.launch(Dispatchers.IO){
            println("BookResources Attempted to update ${bookResources.title} @id number: ${bookResources.title}")
            repository.update(bookResources)
        }

    }


    //Book Rental

    fun insert(bookRental: BookRental) {
        viewModelScope.launch(Dispatchers.IO) {
            println("bookRental added")
            repository.insert(bookRental)
        }
    }

    fun delete(bookRental:BookRental) {
        viewModelScope.launch(Dispatchers.IO){
            println("bookRental Attempted to delete ${bookRental.title} @id number: ${bookRental.id}")
            repository.delete(bookRental)
        }
    }


    fun update(bookRental:BookRental) {
        viewModelScope.launch(Dispatchers.IO){
            println("bookRental Attempted to update ${bookRental.title} @id number: ${bookRental.title}")
            repository.update(bookRental)
        }
    }




    fun deleteAllUserEntries(){
        viewModelScope.launch(Dispatchers.IO) {
            println("Deleting all entries in db deleteAllUserEntries")
            repository.deleteAllEntries()
        }
    }

    fun deleteAllBookResourcesEntries(){
        viewModelScope.launch(Dispatchers.IO) {
            println("Deleting all entries in db deleteAllBookResourcesEntries")
            repository.deleteAllBookResources()
        }
    }

    fun deleteAllBookRentalEntries(){
        viewModelScope.launch(Dispatchers.IO) {
            println("Deleting all entries in db deleteAllBookRentalEntries")
            repository.deleteAllBookRental()
        }
    }



}