package com.example.ecommerceapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ecommerceapp.AddProductModel
import com.example.ecommerceapp.CategoryAdapter
import com.example.ecommerceapp.CategoryAdapter.*
import com.example.ecommerceapp.CategoryModel
import com.example.ecommerceapp.ProductAdapter
import com.example.ecommerceapp.ProductDetailActivity
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.ecommerceapp.databinding.LayoutProductItemBinding.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)

       /* binding.button2.setOnclickListener{
            val intent = Intent(requireContext(), ProductDetailActivity::class.java)
            startActivity(intent)
        }*/

        if (preference.getBoolean("isCart", false))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)

        getCategories()

        getSliderImages()
        getProducts()
        return binding.root
    }

    private fun getSliderImages() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImg)
            }
    }

    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.productRecycler.adapter = ProductAdapter(requireContext(), list)
            }
    }

    private fun getCategories() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.productRecycler.adapter = CategoryAdapter(requireContext(), list)
            }
    }
}