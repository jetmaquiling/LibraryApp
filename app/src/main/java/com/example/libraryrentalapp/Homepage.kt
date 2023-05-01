package com.example.libraryrentalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.libraryrentalapp.databinding.FragmentHomepageBinding


class Homepage : Fragment() {


    lateinit var binding:FragmentHomepageBinding
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)

        println("Home Page Running")

        binding.getStartedBtn.setOnClickListener{
            navController.navigate(R.id.action_homepage_to_registrationPage)
        }
    }


}