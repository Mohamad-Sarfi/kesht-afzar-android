package com.example.smartfarming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.smartfarming.databinding.ActivityAddGardenBinding
import kotlin.math.log

class AddGardenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGardenBinding
    private var registerStage = 1
    private var finishFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGardenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //next btn click handler
        binding.gardenRigisterNextBtn.setOnClickListener{
            if (registerStage >= 4) finishFlag = true
            else registerStage++

            manageStages()
            finishBtnVisible(binding.gardenRigisterFinishBtn, binding.gardenRigisterNextBtn)

        }



    }

    private fun finishBtnVisible(view1 : Button, view2 : Button){
        Log.i("registerFlag", "$finishFlag")
        if (finishFlag) {
            view1.visibility = View.VISIBLE
            view2.visibility = View.GONE
        }
    }

    private fun manageStages(){
        when(registerStage){
            1 -> stage1()
            2 -> stage2()
            3 -> stage3()
            4 -> stage4()
        }
    }

    private fun stage4() {
        TODO("Not yet implemented")
    }

    private fun stage3() {
        TODO("Not yet implemented")
    }

    private fun stage2() {
        binding.gardenRegister1.visibility = View.GONE
        binding.gardenRegister2.visibility = View.VISIBLE
        setTypeSpinner()
    }

    private fun stage1(){
        binding.gardenRegister1.visibility = View.VISIBLE
    }

    private fun setTypeSpinner(){
        val spinner = binding.spinner
        ArrayAdapter.createFromResource(this,
            R.array.pistachio_types,
            android.R.layout.simple_spinner_item)
            .also{ adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_2)
                spinner.adapter = adapter
        }

    }


}
