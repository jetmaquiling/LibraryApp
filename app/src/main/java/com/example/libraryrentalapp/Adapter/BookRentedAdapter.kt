package com.example.libraryrentalapp.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryrentalapp.Database.Model.BookRental
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.R
import com.example.libraryrentalapp.databinding.BookCardLayoutBinding
import com.example.libraryrentalapp.databinding.RentedBookCardLayoutBinding

class BookRentedAdapter(navController: NavController): RecyclerView.Adapter<BookRentedAdapter.ViewHolder>() {

    var listOfItems: List<BookRental> = ArrayList()

    lateinit var navController: NavController

    init {
        this.navController = navController
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookRentedAdapter.ViewHolder {
        val binding: RentedBookCardLayoutBinding = RentedBookCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding, navController)

    }

    override fun onBindViewHolder(holder: BookRentedAdapter.ViewHolder, position: Int) {
        val currentItem: BookRental = listOfItems[position]
        holder.setData(currentItem.img, currentItem.title, currentItem.description, currentItem.img ,currentItem.img_type, currentItem.date_rented, currentItem.date_to_return , currentItem.rent_id , currentItem.id)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    fun setItems(booksList: List<BookRental>){
        this.listOfItems = booksList
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: RentedBookCardLayoutBinding, val navController: NavController) : RecyclerView.ViewHolder(binding.root) {

        fun setData(image: String ,title: String, description: String, img: String, img_type: String,date_rented:String, date_to_return: String, rent_id: String, id: Int ) {
            binding.titleTv.text = title
            binding.descriptionTv.text = description
            binding.dateRented.text = date_rented
            binding.dateToReturn.text = date_to_return
            binding.rentIdTv.text = rent_id

            if (img_type.equals("resource")){
                binding.image.setImageResource(img.toInt())
            } else if (img_type.equals("local")){
                binding.image.setImageURI(img.toUri())
            }else{
                binding.image.setImageResource(R.drawable.default_image)
            }

            binding.root.setOnClickListener{
                val bundle = Bundle()
                bundle.putInt("id", rent_id.toInt())
                navController.navigate(R.id.action_rentedBooksPage_to_bookPage, bundle)
            }
        }
    }


}