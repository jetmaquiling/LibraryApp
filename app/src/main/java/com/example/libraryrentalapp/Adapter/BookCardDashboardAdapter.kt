package com.example.libraryrentalapp.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryrentalapp.Database.Model.BookResources
import com.example.libraryrentalapp.R
import com.example.libraryrentalapp.databinding.BookCardLayoutBinding


class BookCardDashboardAdapter(navController: NavController): RecyclerView.Adapter<BookCardDashboardAdapter.ViewHolder>() {


    var listOfItems: List<BookResources> = ArrayList()

    lateinit var navController: NavController

    init {
        this.navController = navController
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookCardDashboardAdapter.ViewHolder {
        val binding: BookCardLayoutBinding = BookCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding, navController)

    }

    override fun onBindViewHolder(holder: BookCardDashboardAdapter.ViewHolder, position: Int) {
        val currentItem: BookResources = listOfItems[position]
        holder.setData(currentItem.img, currentItem.title,currentItem.category, currentItem.description,currentItem.img , currentItem.img_type, currentItem.isAvailable, currentItem.id)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


    fun setItems(booksList: List<BookResources>){
        this.listOfItems = booksList
        notifyDataSetChanged()
    }




    class ViewHolder(val binding: BookCardLayoutBinding, val navController: NavController) : RecyclerView.ViewHolder(binding.root) {

        fun setData(image: String ,title: String, category: String, description: String, img: String, img_type: String, is_available:Boolean, id: Int) {
            binding.titleTv.text = title
            binding.descriptionTv.text = description
            binding.categoryTv.text = category


            if (img_type.equals("resource")){
                binding.imageTv.setImageResource(img.toInt())
            } else if (img_type.equals("local")){
                binding.imageTv.setImageURI(img.toUri())
            }else{
                binding.imageTv.setImageResource(R.drawable.default_image)
            }


            if (is_available){
                binding.availabilitTv.text = "Available"
            }else{
                binding.availabilitTv.text = "Unavailable"
            }


            binding.root.setOnClickListener{
                    val bundle = Bundle()
                    bundle.putInt("id", id)
                    navController.navigate(R.id.action_dashboardPage_to_bookPage, bundle)
            }
        }
    }


}