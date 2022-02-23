package com.example.smartfarming.ui.gardens

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.smartfarming.FarmApplication
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.gardens.composables.MainCompose

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
                MainCompose(viewModel)
            }
        }

        return view
    }

}

