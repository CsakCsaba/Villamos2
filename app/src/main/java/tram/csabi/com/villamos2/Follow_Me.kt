package tram.csabi.com.villamos2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class Follow_Me : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var locationManager: LocationManager
    lateinit var mContext: Context
    private var locationListenerGPS: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: android.location.Location) {
           if(LogicHandler.follow) {
               val latitude = location.latitude
               val longitude = location.longitude

               val msg = "New Latitude: " + latitude + "\nNew Longitude: " + longitude
               val toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
               toast.setGravity(Gravity.TOP, 0, 0)
               toast.show()

           }
            /* val currentLatLng = LatLng(latitude, longitude)
             map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18.5f))*/
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow__me)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mContext = this
        this.locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10f, this.locationListenerGPS)



    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        /*val test1 = MarkerOptions().position(LatLng(47.589092, 19.114647))
        val test2 = MarkerOptions().position(LatLng(47.591574, 19.118126))
        map.addMarker(test1)
        map.addMarker(test2)*/

        for (i in 0..2){ //megállók, lámpák, kereszteződések lepakolása
            for (j in LogicHandler.chosen.coordinateList(i)){
                val tempMarkerOpt = MarkerOptions().position(LatLng(j[0],j[1] ))
                when(i) {
                    0 ->
                        tempMarkerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    1->
                        tempMarkerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    2->
                        tempMarkerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                }
                map.addMarker(tempMarkerOpt)
            }
        }
        var line = PolylineOptions()
        for (i in LogicHandler.chosen.lineTrack)
            line.add(LatLng(i[0],i[1]))

        map.addPolyline(line.zIndex(1000f).width(8f).color(Color.argb(255,255,204,0)))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(47.589092, 19.114647), 18.5f))


        // Én helyzetem + gombok
        if(LogicHandler.follow) {
            map.isMyLocationEnabled = true // kék pötty
            map.uiSettings.isScrollGesturesEnabled = false
            map.uiSettings.isZoomControlsEnabled = false
            map.uiSettings.isZoomGesturesEnabled = false
            map.uiSettings.isRotateGesturesEnabled = false
            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                if (location != null) {
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18f))

                }
            }
        }else{
            map.uiSettings.isZoomControlsEnabled = true // +/- gombok implementálása
            map.uiSettings.isScrollGesturesEnabled = true
        }

    }


}
