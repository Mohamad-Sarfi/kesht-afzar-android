package com.example.smartfarming.ui.addgarden

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.example.smartfarming.databinding.FragmentAddGarden3Binding
import com.google.android.gms.location.*


// This fragment gets user location.

class AddGardenFragment3 : Fragment() {
    private lateinit var binding: FragmentAddGarden3Binding
    private val viewModel : AddGardenViewModel by activityViewModels()

    private var latitude : String = ""
    private var longitude : String = ""
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest : LocationRequest
    private lateinit var latTV : TextView
    private lateinit var longTV: TextView

    val PERMISSION_ID = 1000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddGarden3Binding.inflate(inflater, container, false)
        latTV = binding.lat
        longTV = binding.lon


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())


        binding.locationButton.setOnClickListener {
            getLocation()
        }

        return binding.root
    }

    private fun getLocation(){

        if (checkPermission()){
            if (isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location : Location? = task.result
                    if (location == null){
                        getNewLocation()
                    }
                    else {
                        latTV.text = location.latitude.toString()
                        latitude = location.latitude.toString()
                        longTV.text = location.longitude.toString()
                        longitude = location.latitude.toString()

                        updateViewModel(latitude, longitude)
                    }
                }
            }
            else{
                Toast.makeText(activity, "مکان یاب موبایل خود را روشن کنید", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            requestUserPermission()
        }

    }

    private fun checkPermission() : Boolean {
        if ((ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) ||
                ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestUserPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_ID
        )
    }

    private fun isLocationEnabled() : Boolean {
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Debug", "You have the permission")
            }
        }
    }

    private fun getNewLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2

        if (checkPermission()){
            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()!!
            )
        }
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation
            latTV.text = lastLocation.latitude.toString()
            longTV.text = lastLocation.longitude.toString()

            latitude = lastLocation.latitude.toString()
            longitude = lastLocation.longitude.toString()

            updateViewModel(latitude, longitude)

        }
    }

    private fun updateViewModel(lat : String, lon: String) {
        viewModel.setLocation(lat, lon)
    }

}