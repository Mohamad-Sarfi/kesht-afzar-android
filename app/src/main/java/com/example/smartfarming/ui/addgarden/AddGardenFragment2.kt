package com.example.smartfarming.ui.addgarden

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.smartfarming.R
import com.example.smartfarming.databinding.FragmentAddGarden2Binding

class AddGardenFragment2 : Fragment() {

    private lateinit var binding : FragmentAddGarden2Binding
    private val viewModel : AddGardenViewModel by activityViewModels()
    private lateinit var arrayOfTypes: MutableList<String>

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGarden2Binding.inflate(inflater, container, false)




        //spinner populate
        val spinner : Spinner = binding.plantTypeSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.pistachio_types,
            R.layout.my_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val res : Resources = resources
        val arrayOfPlantTypes = res.getStringArray(R.array.pistachio_types)

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateTypeArray(arrayOfPlantTypes[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        // Manage type TVs
        viewModel.getTypeArray().observe(viewLifecycleOwner, Observer {
            binding.addedTypesLO.removeAllViews()
            for (e in it){
                addTypeTV(e)
            }
            arrayOfTypes = it
        })

        return binding.root
    }

    private fun addTypeTV(value: String){

        val newTV = TextView(context, null, 0, R.style.planttype)
        newTV.setBackgroundResource(R.drawable.et_back)
        newTV.text = value

        newTV.setOnClickListener{
            it.visibility = View.GONE
            viewModel.removeFromTypeArray(newTV.text.toString())

        }
        binding.addedTypesLO.addView(newTV)


    }

    private fun updateTypeArray(value: String){
        //val tempArray = viewModel.getTypeArray().value
        Log.i("Types: ", "$arrayOfTypes")
        if (value !in arrayOfTypes){
            viewModel.addType(value)
        }

    }

}