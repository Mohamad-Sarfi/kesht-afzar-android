package com.example.smartfarming.addgarden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.smartfarming.R
import com.example.smartfarming.databinding.FragmentAddGarden3Binding


class AddGardenFragment3 : Fragment() {
    private lateinit var binding: FragmentAddGarden3Binding
    private val viewModel : AddGardenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddGarden3Binding.inflate(inflater, container, false)


        return binding.root
    }

}