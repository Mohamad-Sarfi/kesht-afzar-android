package com.example.smartfarming.ui.gardens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.smartfarming.FarmApplication
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.gardens.composables.GardenCompose

class GardensFragment : Fragment() {


    val viewModel : GardensViewModel by activityViewModels {
        GardensViewModelFactory((activity?.application as FarmApplication).repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gardens, container, false)

        view.findViewById<ComposeView>(R.id.gardensCompose).setContent {
            SmartFarmingTheme() {
                GardenCompose(viewModel)
            }
        }

        return view
    }

}

