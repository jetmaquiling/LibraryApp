package com.example.libraryrentalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.libraryrentalapp.Database.Model.User
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel
import com.example.libraryrentalapp.databinding.FragmentRegistrationPageBinding


class RegistrationPage : Fragment() {


    lateinit var binding:FragmentRegistrationPageBinding

    lateinit var navController: NavController

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.proceedBtn.setOnClickListener{
            viewModel.insert(User(1, binding.editTextTextPersonName.text.toString()))
            navController.navigate(R.id.action_registrationPage_to_dashboardPage)
        }

    }


}