package com.example.smartfarming.ui.addgarden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.smartfarming.databinding.FragmentAddGarden4Binding

class AddGardenFragment4 : Fragment() {

    private lateinit var binding : FragmentAddGarden4Binding
    private val viewModel : AddGardenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddGarden4Binding.inflate(inflater, container, false)

        return binding.root
    }

}