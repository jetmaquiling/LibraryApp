package com.example.libraryrentalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel
import com.example.libraryrentalapp.databinding.FragmentSplashScreenPageBinding
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.timerTask

class SplashScreenPage : Fragment() {

    lateinit var navController: NavController

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(requireParentFragment())

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getAllUserData().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it.isEmpty()){
                navController.navigate(R.id.action_splashScreenPage_to_homepage)
            }else{
                navController.navigate(R.id.action_splashScreenPage_to_dashboardPage)
            }

        })


    }

}