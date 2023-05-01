package com.example.libraryrentalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        println(R.drawable.book1)
        println(R.drawable.book2)
        println(R.drawable.book3)
        println(R.drawable.book4)
        println(R.drawable.book5)


        viewModel.getAllBookResources().observe(this, Observer{
            it?.let {
                for (book in it){
                    println(book.title)
                }
            }
        })




    }
}