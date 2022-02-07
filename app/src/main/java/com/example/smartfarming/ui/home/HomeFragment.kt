package com.example.smartfarming.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartfarming.ui.addgarden.AddGardenActivity
import com.example.smartfarming.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        visiblityOfAddFirstGarden()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun visiblityOfAddFirstGarden(){
        if (homeViewModel.gardensNumber != 0){
            binding.addFirstGardenLayout.visibility = View.INVISIBLE;
        }
        else {
            binding.addFirstGardenLayout.setOnClickListener{
                val intent = Intent(this@HomeFragment.context, AddGardenActivity::class.java)
                startActivity(intent)
            }
        }
    }



}