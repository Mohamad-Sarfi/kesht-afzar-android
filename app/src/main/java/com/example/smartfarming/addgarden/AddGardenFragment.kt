package com.example.smartfarming.addgarden

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
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

        gardenNameET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("before", "$s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("on change", "$s")
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length < 3){
                    Toast.makeText(activity, "نام باغ را بصورت کامل وارد کنید", Toast.LENGTH_SHORT).show()
                }
                else {
                    viewModel.setGardenName(s.toString())
                }
            }



        })

        gardenAgeET.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("before", "$s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("before", "$s")
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()){
                    Toast.makeText(activity, "سن باغ را وارد کنید", Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.setGardenAge(s.toString())
                }
            }

        })

        return binding.root

    }




}