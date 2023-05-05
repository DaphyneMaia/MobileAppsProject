package com.example.ecommerceapp.fragment

import android.app.AppComponentFactory
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.AddressActivity
import com.example.ecommerceapp.AppDatabase
import com.example.ecommerceapp.CartAdapter
import com.example.ecommerceapp.CategoryActivity
import com.example.ecommerceapp.ProductModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCartBinding.inflate(layoutInflater)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart", false)
        editor.apply()

        val dao = AppDatabase.getInstance(requireContext()).productDao()

        dao.getAllProducts().observe(requireActivity()){
            binding.cartRecycler.adapter = CartAdapter(requireContext(), it)

            totalCost(it)
        }
        return binding.root
    }

    private fun totalCost(data: List<ProductModel>?) {

        var total = 0
        for(item in data!!){
            total += item.productSp!!.toInt()
        }

        binding.textView12.text = "Total item cart is ${data.size}"
        binding.textView13.text = "Total cost is : $total"

        binding.checkout.setOnClickListener {
            val intent = Intent(context, AddressActivity::class.java)
            intent.putExtra("totalCost", total)
            startActivity(intent)
        }


    }

}