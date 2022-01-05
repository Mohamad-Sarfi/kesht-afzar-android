package com.example.smartfarming.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.smartfarming.R
import com.example.smartfarming.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    private lateinit var shopViewModel : ShopViewModel
    private var _binding : FragmentShopBinding? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}