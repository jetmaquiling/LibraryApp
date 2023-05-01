package com.example.libraryrentalapp.Database

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.libraryrentalapp.Database.Dao.BookRentalDao
import com.example.libraryrentalapp.Database.Dao.BookResourcesDao
import com.example.libraryrentalapp.Database.Dao.UserDao
import com.example.libraryrentalapp.Database.Model.BookRental
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.Database.Model.User
import com.example.libraryrentalapp.R
import kotlinx.coroutines.*;

@Database(entities = [User::class, BookRental::class, BookResources::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun bookRentalDao(): BookRentalDao
    abstract fun bookResourcesDao(): BookResourcesDao

    companion object{
        @Volatile
        public var INSTANCE: MainDatabase?= null

        //Singleton pattern
        fun getDatabase(context: Context): MainDatabase? {
            val tempInstance = INSTANCE
            if(tempInstance == null){
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "TheLibraryAppDataBase4").fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            GlobalScope.launch {
                                INSTANCE?.let { prepopulate(it) }
                            }

                            /*
                            WHAT GOES HERE?
                            */

                        }
                    }).build()
                    INSTANCE = instance
                    return instance
                }
            }else{
                return tempInstance
            }
        }



        suspend fun prepopulate(db: MainDatabase){
            var bookRentalDao:BookRentalDao = db.bookRentalDao()
            var bookResourcesDao:BookResourcesDao = db.bookResourcesDao()

            bookRentalDao.insert(BookRental(223,"How To become Love",R.drawable.book1.toString(), "resource", "business", "Find Ways To become rich in five ways.", "April 23, 2023", "April 30, 2023","16"))


            bookResourcesDao.insert(BookResources(16,"How To become Love",R.drawable.book1.toString(),"resource","business", "Find Ways To become rich in five ways.",false))
            bookResourcesDao.insert(BookResources(15,"Virgin Islands Discovery",R.drawable.book2.toString(),"resource","business", "Find Ways To become handsome in five ways.",true))
            bookResourcesDao.insert(BookResources(14,"What is Kilig?",R.drawable.book3.toString(),"resource","business", "Find Ways To become rich in five ways.",true))
            bookResourcesDao.insert(BookResources(13,"Violets R Blue Roses Means I Love You",R.drawable.book4.toString(),"resource","business", "Find Ways To become handsome in five ways.",true))
            bookResourcesDao.insert(BookResources(12,"Inside the Mind of Mas Ter",R.drawable.book5.toString(),"resource","business", "Find Ways To become better in five ways.",true))
        }
         }
}