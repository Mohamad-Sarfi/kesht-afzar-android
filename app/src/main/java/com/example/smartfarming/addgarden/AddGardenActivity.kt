package com.example.smartfarming.addgarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.smartfarming.R
import com.example.smartfarming.databinding.ActivityAddGardenBinding

private const val  NUM_PAGES = 4

class AddGardenActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAddGardenBinding
    private lateinit var viewpager : ViewPager2
    private lateinit var viewModel: AddGardenViewModel

    private lateinit var counterTV : TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGardenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddGardenViewModel::class.java)

        viewpager = binding.addGardenPager
        val pagerAdapter =  AddGardenPagerAdapter(this)
        viewpager.adapter = pagerAdapter
        viewpager.isUserInputEnabled = false

        val nextButton = binding.nextButton
        counterTV = binding.counterTV
        //imageView = binding.backgroundImage


        //Next-button click handler
        nextButton.setOnClickListener{
            if (viewpager.currentItem == NUM_PAGES - 1) {
                nextButton.text = "ثبت"

            }
            else if (viewpager.currentItem == 0 && viewModel.getGardenName()!!.length < 3) {

                var name = viewModel.getGardenName()

                Toast.makeText(this, "نام و متوسط سن درختان باغ را وارد کنید", Toast.LENGTH_SHORT ).show()
                Log.i("GName", "")
            }
            else {
                viewpager.currentItem = viewpager.currentItem + 1

            }
        }



    }


    override fun onBackPressed() {
        if (viewpager.currentItem == 0){
            super.onBackPressed()
        }
        else {
            viewpager.currentItem = viewpager.currentItem - 1
        }
    }

    private inner class AddGardenPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun getItemId(position: Int): Long {
            val textValue = position + 1
            counterTV.text = textValue.toString()

            return super.getItemId(position)
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> AddGardenFragment()
                1 -> AddGardenFragment2()
                2 -> AddGardenFragment3()
                3 -> AddGardenFragment4()
                else -> AddGardenFragment4()
            }
        }
    }

}


