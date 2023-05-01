package com.example.libraryrentalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryrentalapp.Adapter.BookCardDashboardAdapter
import com.example.libraryrentalapp.Adapter.BookRentedAdapter
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel
import com.example.libraryrentalapp.databinding.FragmentDashboardPageBinding
import com.example.libraryrentalapp.databinding.FragmentRentedBooksPageBinding

class RentedBooksPage : Fragment() {

    lateinit var navController: NavController

    lateinit var binding: FragmentRentedBooksPageBinding

    lateinit var viewModel: MainViewModel

    lateinit var adapter: BookRentedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRentedBooksPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        adapter = BookRentedAdapter(navController)
        binding.recyclerRentedBooks.setHasFixedSize(true)
        binding.recyclerRentedBooks.adapter = adapter
        binding.recyclerRentedBooks.layoutManager = LinearLayoutManager(context)

        binding.backButton.setOnClickListener{
            navController.navigate(R.id.action_rentedBooksPage_to_dashboardPage)
        }

        viewModel.getAllBookRental().observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
            binding.titleBarTv.text = "Books Rented (${it.size})"

        })
    }

}