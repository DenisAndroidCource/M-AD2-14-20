package com.example.mapsexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapFragment
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.Marker
import com.google.android.libraries.maps.model.MarkerOptions


// AIzaSyB0miTKA2jS5j5rr7-dFE86ZLrPGdIv3UM

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null

    private lateinit var locationManager: LocationManager

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                100f,
                object: LocationListener{
                    override fun onLocationChanged(location: Location) {
                        addMarker(LatLng(location.latitude, location.longitude))
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                        super.onStatusChanged(provider, status, extras)
                    }

                    override fun onProviderEnabled(provider: String) {
                        super.onProviderEnabled(provider)
                    }

                    override fun onProviderDisabled(provider: String) {
                        super.onProviderDisabled(provider)
                    }
                }
        )

//        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapContainer) as MapFragment
//        mapFragment.getMapAsync()

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(null)
        mapView.getMapAsync(object: OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap?) {
                this@MainActivity.googleMap = googleMap
                if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    setLocation()
                } else {
                    ActivityCompat.requestPermissions(
                            this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            1000
                    )
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun setLocation(){
        Log.d("GPS", googleMap?.isMyLocationEnabled.toString())
        googleMap?.isMyLocationEnabled = true
        Log.d("GPS", googleMap?.isMyLocationEnabled.toString())
        addMarker(LatLng(53.900361, 27.551816))

    }

    private fun addMarker(latLon: LatLng){
        val markerOption = MarkerOptions().position(latLon)
        markerOption.title("TITLE")
        val marker = googleMap?.addMarker(markerOption)
    }
}