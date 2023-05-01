package com.example.libraryrentalapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.libraryrentalapp.Database.Model.BookRental
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.Database.ViewModel.MainViewModel
import com.example.libraryrentalapp.Dialog.CalendarDialog
import com.example.libraryrentalapp.Interface.CalendarInterface
import com.example.libraryrentalapp.databinding.FragmentBookPageBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class BookPage : Fragment(),CalendarInterface {

    lateinit var navController: NavController

    lateinit var binding: FragmentBookPageBinding

    lateinit var viewModel: MainViewModel

    lateinit var bookResources: BookResources

    var bookRental: BookRental = BookRental(0,"","","","","","","","")

    lateinit var calendarDialog:CalendarDialog

    override lateinit var date_to_return: LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        date_to_return = LocalDate.now().plusDays(3)

        bookResources = BookResources(0,"","","","","",true)

        binding = FragmentBookPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        calendarDialog = CalendarDialog(this);

        binding.dateToReturn.text = "Return by ${date_to_return.toString()}"

        binding.backButton.setOnClickListener{
            navController.navigate(R.id.action_bookPage_to_dashboardPage)
        }


        binding.changeDate.setOnClickListener {
            calendarDialog.show(parentFragmentManager, "Date picker")
        }

        binding.rentButton.setOnClickListener{
            rentBook()
        }

        binding.returnButton.setOnClickListener{
            returnBook()
        }

        arguments?.let { viewModel.getBookResourceById(it.getInt("id")).observe(viewLifecycleOwner, Observer { item ->
            bookResources.title = item.title
            bookResources.category = item.category
            bookResources.img = item.img
            bookResources.description = item.description
            bookResources.isAvailable = item.isAvailable
            bookResources.img_type = item.img_type
            bookResources.id = item.id

            if (item.img_type.equals("resource")){
                binding.image.setImageResource(item.img.toInt())
            } else if (item.img_type.equals("local")){

                var newPath = "${item.img}?time=${Date()}"
                binding.image.setImageURI(newPath.toUri())
            }else{
                binding.image.setImageResource(R.drawable.default_image)
            }


            binding.title.text = item.title
            binding.description.text = item.description
            binding.category.text = item.category

            if(item.isAvailable){
                binding.returnContainer.visibility = View.GONE
            }else{
                viewModel.getBookRentalByRentId(item.id.toString()).observe(viewLifecycleOwner, Observer { rental ->
                    if (rental == null){
                        println("HOY Dont WORK")
                    }else{
                        println("HOY WORK")
                        binding.toRentContainer.visibility = View.GONE
                        bookRental.id = rental.id
                        bookRental.date_rented = rental.date_rented
                        bookRental.date_to_return = rental.date_to_return
                        binding.toReturnDate.text = "Return Book By ${rental.date_to_return}"
                    }
                })
            }
        }) }




    }


    fun returnBook(){
        Toast.makeText(context,"Thanks For Returning The Book", Toast.LENGTH_LONG).show()
        bookResources.isAvailable = true
        viewModel.update(bookResources)
        binding.toRentContainer.visibility = View.VISIBLE
        viewModel.delete(bookRental)
    }

    fun rentBook(){
        Toast.makeText(context,"Congrats on renting the Book", Toast.LENGTH_LONG).show()
        val id = (0..10000000).random()
        var date_rented: LocalDate = LocalDate.now()
        viewModel.insert(BookRental(id,bookResources.title, bookResources.img, bookResources.img_type, bookResources.category, bookResources.description, date_rented.toString(), date_to_return.toString(), bookResources.id.toString()))
        bookResources.isAvailable = false
        viewModel.update(bookResources)
        navController.navigate(R.id.action_bookPage_to_rentedBooksPage)

    }


    override fun setDateRent(year: Int, month:Int, day:Int) {
        var myCalendar =  Calendar.getInstance()
        myCalendar.set(year,month,day)
        val tz: TimeZone = myCalendar.getTimeZone()
        val zid: ZoneId = if (tz == null) ZoneId.systemDefault() else tz.toZoneId()
        date_to_return = LocalDateTime.ofInstant(myCalendar.toInstant(),zid).toLocalDate()
        binding.dateToReturn.text = "Return by ${date_to_return.toString()}"
    }

}