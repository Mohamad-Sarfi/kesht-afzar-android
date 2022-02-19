package com.example.smartfarming.ui.addgarden

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smartfarming.ui.addactivities.ActivitiesScreen
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.borderGray
import com.google.android.gms.maps.model.Marker
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.Polygon
import com.google.android.libraries.maps.model.PolygonOptions
import com.google.maps.android.ktx.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

val REQUEST_LOCATION_PERMISSION = 1

@Composable
fun MapCompose(
    viewModel: AddGardenViewModel,
    navController: NavHostController
){
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "موقعیت حدودی مزرعه خود را روی نقشه مشخص کنید",
                style = MaterialTheme.typography.subtitle1,
                color = borderGray
            )
        }
        Box(
            modifier = Modifier
                .size(400.dp)
                .clip(RoundedCornerShape(20.dp))
                .padding(30.dp)
                .shadow(3.dp, shape = RoundedCornerShape(25.dp), clip = true),
            contentAlignment = Alignment.Center
        ) {
            GoogleMap(
                viewModel.gardenName.value!!,
                Modifier.clip(RoundedCornerShape(20.dp))
                    ,
                viewModel){

            }
        }
        val location by viewModel.location.observeAsState()
        Row() {
            Text(
                text = if (location!!["lat"]!!.length >1 ) location!!["lat"]!!.substring(0, 5) else location!!["lat"]!!,
                modifier = Modifier.padding(12
                    .dp),
                style = MaterialTheme.typography.body1,
                color = borderGray
            )
            Text(
                text = if (location!!["long"]!!.length >1 ) location!!["long"]!!.substring(0, 5) else location!!["long"]!!,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.body1,
                color = borderGray
            )
        }
        Button(
            onClick = {
                viewModel.isLocationSet.value = true
                navController.navigate(ActivitiesScreen.AddGardenScreen.name){
                    popUpTo(0)
                }
                      },
            modifier = Modifier
                .padding(20.dp)
                .clip(MaterialTheme.shapes.large)
        ) {
            Text(
                text = "تایید",
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 40.dp)
            )
        }
    }
}

@Composable
fun GoogleMap(
    gardenName : String,
    modifier: Modifier = Modifier,
    viewModel: AddGardenViewModel,
    onReady : (GoogleMap) -> Unit
){
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map_frame
        }
    }
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    lifeCycle.addObserver(rememberMapLifeCycle(mapView = mapView))
    val REQUEST_LOCATION_PERMISSION = 1




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AndroidView(
           factory = {
               mapView.apply {
                   mapView.getMapAsync{googleMap ->
                       googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                       googleMap.uiSettings.isZoomControlsEnabled = true
                       googleMap.uiSettings.setAllGesturesEnabled(isEnabled)
                       val startPoint = LatLng(35.0, 54.0)
                       googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 5f))

                       val points = arrayListOf<LatLng>()
                       var markers = arrayListOf<com.google.android.libraries.maps.model.Marker>()
                       var polygon : Polygon? = null
                       val polygonOptions = PolygonOptions()

                       // User location
                       enableMyLocation(activity, googleMap, context)


                       googleMap.setOnMapClickListener { latLng ->
                           points.add(latLng)
                           val mark = googleMap.addMarker(
                               MarkerOptions()
                                   .position(latLng)
                                   .title("باغ شما")
                           )
                           markers.add(mark)

                           polygonOptions
                               .add(
                                   points[points.size - 1]
                               )
                               .fillColor(0x0E9145)

                           if (polygon != null) polygon!!.remove()
                           polygon = googleMap.addPolygon(polygonOptions)
                           viewModel.setLocationList(points)
                       }


                       // Clear selections
                       googleMap.setOnMapLongClickListener {
                           points.removeAll(points)
                           markers.removeAll(markers)
                           polygonOptions.addAll(
                               points
                           )
                           polygon!!.remove()
                           polygon = googleMap.addPolygon(polygonOptions)
                           googleMap.clear()
                       }

                       googleMap.setOnMarkerClickListener {
                           markers.remove(it)
                       }

                       /*googleMap.setOnCameraIdleListener {
                           googleMap.clear()
                           googleMap.addMarker(MarkerOptions()
                               .title(gardenName)
                               .position(googleMap.cameraPosition.target)
                           )
                           Log.i("GPS", "${googleMap.cameraPosition.target.latitude}")
                           viewModel.setLocation(
                               googleMap.cameraPosition.target.latitude.toString(),
                               googleMap.cameraPosition.target.longitude.toString()
                           )
                       }*/

                       onReady(googleMap)
                   }
               }
           }
        )
        }
    }


@Composable
fun rememberMapLifeCycle(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver{ _, event ->
            when(event){
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }


fun isPermissionGranted(context : Context) : Boolean{
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("MissingPermission")
fun enableMyLocation(activity : Activity, googleMap: GoogleMap, context: Context){
    if (isPermissionGranted(context)){
        googleMap.isMyLocationEnabled = true
    }
    else {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_PERMISSION
        )
    }
}

fun calculateArea(points : List<LatLng>) : Double{
    var area = 0.0
    var sum = 0.0

    for (i in 0..points.size-1){
        Log.i("Area", "$i")
        Log.i("Area2", "${points.size}")
        if (i == 0)
            sum += points[i].latitude * (points[i+1].longitude - points.last().longitude)
        else
            sum += points[i].latitude * (points[i+1].longitude - points[i -1].longitude)
    }
    area = 0.5 * Math.abs(sum) * 1000
    return area
}
