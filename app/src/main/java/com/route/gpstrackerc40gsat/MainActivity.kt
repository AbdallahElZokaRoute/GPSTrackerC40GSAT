package com.route.gpstrackerc40gsat

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    val requestGPSPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            if (map[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                // User Grant Fine Location
                getUserLocation()
            } else {
                // User Didn't grant Fine Location

            }
            if (map[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                getUserLocation()
            } else {

            }
        }
    var userLatLng: LatLng? = null
    var googleMap: GoogleMap? = null
    val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    var marker: Marker? = null
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {

            locationResult.locations.forEach { location ->
                userLatLng = LatLng(location.latitude, location.longitude)
                Log.e("TAG", "getUserLocation: longitude  ${location.longitude}")
                Log.e("TAG", "getUserLocation: latitude  ${location.latitude}")
                drawMarkerOnMap()
            }
        }
    }

    fun drawMarkerOnMap() {
        if (marker == null) {
            val markerOptions = MarkerOptions()
            markerOptions.title("User Location")
            if (userLatLng != null)
                markerOptions.position(userLatLng!!)
            marker = googleMap?.addMarker(markerOptions)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng!!, 16F))
        } else {
            if (userLatLng != null)
                marker?.position = userLatLng!!
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng!!, 16F))
        }

    }

    @SuppressLint("MissingPermission")
    fun getUserLocation() {
        val currentLocationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 8_000)
            .build()
        // Create The variable first  then use it
        fusedLocationProviderClient.requestLocationUpdates(
            currentLocationRequest, locationCallback,
            Looper.getMainLooper()
        )
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun isGPSPermissionAllowed(requestedPermission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            requestedPermission
        ) == PackageManager.PERMISSION_GRANTED
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isGPSPermissionAllowed(Manifest.permission.ACCESS_COARSE_LOCATION) || isGPSPermissionAllowed(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            getUserLocation()
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ||
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            showDialog(
                title = "We need to Access location to get nearest drivers in area",
                positiveText = "I Understand",
                onPositiveClickListener = { dialog, which ->
                    dialog?.dismiss()
                    requestGPSPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                },
                negativeText = "No",
                onNegativeClickListener = { dialog, which ->
                    dialog?.dismiss()
                }
            )

        } else {
            requestGPSPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }
}

// 1- Runtime Permissions  (User -> Voice <-> Camera <-> Location )
// Android 6.0 ->  Runtime Permissions


// 2- Location of the user
// 3- Integrate with google maps