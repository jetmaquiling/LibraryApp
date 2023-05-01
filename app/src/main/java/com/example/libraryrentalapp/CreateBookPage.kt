package com.example.libraryrentalapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel
import com.example.libraryrentalapp.databinding.FragmentCreateBookPageBinding


class CreateBookPage : Fragment() {

    val categoryArray = arrayOf("Romance", "Horror", "Life and People", "History", "Action", "Adventure", "Science", "Comedy")

    lateinit var binding: FragmentCreateBookPageBinding

    lateinit var navController: NavController

    lateinit var viewModel: MainViewModel


    var bookResources:BookResources = BookResources(0,"","","empty","Romance","",true)

    var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentCreateBookPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val id: Int = (0..10000000).random()
        bookResources.id = id

        binding.editImageBtn.setOnClickListener {
            selectImage()
        }

        binding.backButton.setOnClickListener {
            navController.navigateUp()
        }


        binding.createNewBookBtn.setOnClickListener {
            createBook()
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.categorySpinner.adapter = adapter
        }

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    bookResources.category = categoryArray.get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }


    }

    fun createBook(){
        bookResources.title = binding.titleEt.text.toString()
        bookResources.description = binding.descriptionEt.text.toString()
        viewModel.insert(bookResources)


        val bundle = Bundle()
        bundle.putInt("id", bookResources.id)
        navController.navigate(R.id.action_createBookPage_to_bookPage, bundle)
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }
        imageUri = data.data
        //Insert code to store URI in db
        val path: String = imageUri.toString()
         bookResources.img = path
         bookResources.img_type = "local"
         binding.editImageBtn.setImageURI(imageUri)
    }
}