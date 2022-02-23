package com.example.smartfarming.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.smartfarming.FarmApplication
import com.example.smartfarming.databinding.FragmentHomeBinding
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.addgarden.AddGarden
import com.example.smartfarming.ui.home.composables.HomeCompose

class HomeFragment : Fragment() {

    val viewModel : HomeViewModel by activityViewModels {
        HomeViewModelFactory((activity?.application as FarmApplication).repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return ComposeView(requireContext()).apply {
            setContent {
                SmartFarmingTheme() {
                    HomeCompose(viewModel)
                }
            }
        }
    }
}