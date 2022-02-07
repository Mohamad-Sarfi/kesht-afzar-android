package com.example.smartfarming.ui.addgarden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.smartfarming.R
import com.example.smartfarming.databinding.FragmentAddGardenBinding


class AddGardenFragment : Fragment() {

    private  lateinit var binding : FragmentAddGardenBinding
    private val viewModel : AddGardenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGardenBinding.inflate(inflater, container, false)

        val gardenNameET = binding.gardenNameET
        val gardenAgeET = binding.gardenAgeET
        val spinner = binding.gardenAreaSpinner

        gardenAgeET.setOnFocusChangeListener { v, hasFocus ->
            updateViewModel(gardenNameET.text.toString(), gardenAgeET.text.toString())
        }


        // Spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.garden_area,
            R.layout.my_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val arrayOfAreas = resources.getStringArray(R.array.garden_area_num)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        return binding.root

    }

    private fun updateViewModel(name : String, age: String){
        viewModel.setGardenName(name)
        viewModel.setGardenAge(age)
    }


}