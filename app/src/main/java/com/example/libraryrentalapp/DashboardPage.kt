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
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel
import com.example.libraryrentalapp.databinding.FragmentDashboardPageBinding

class DashboardPage : Fragment() {

    lateinit var navController: NavController

    lateinit var binding: FragmentDashboardPageBinding

    lateinit var viewModel: MainViewModel

    lateinit var adapter: BookCardDashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        adapter = BookCardDashboardAdapter(navController)
        binding.dashboardRecycler.setHasFixedSize(true)
        binding.dashboardRecycler.adapter = adapter
        binding.dashboardRecycler.layoutManager = GridLayoutManager(context,2)


        viewModel.getAllBookResources().observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter.setItems(it)
            }
        })


        viewModel.getAllUserData().observe(viewLifecycleOwner, Observer {
            for (user in it){
                binding.textView.text = "Welcome ${user.name}"
            }
        })


        binding.goToRent.setOnClickListener{
            navController.navigate(R.id.action_dashboardPage_to_rentedBooksPage)
        }

        binding.goToAdd.setOnClickListener {
            navController.navigate(R.id.action_dashboardPage_to_createBookPage)
        }



    }

}