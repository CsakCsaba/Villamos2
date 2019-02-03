package tram.csabi.com.villamos2.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.hardware.*
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import tram.csabi.com.villamos2.Logic.LogicHandler
import tram.csabi.com.villamos2.R
import com.google.android.gms.maps.model.CameraPosition




@Suppress("DEPRECATION")
class Follow_Me : /* AppCompatActivity(),*/ OnMapReadyCallback, AudioActivity() {

    //_________________________________________________
   /* var timer: Timer?=null
    val handler = Handler()
    lateinit var timerTask: TimerTask*/

    private var  elsoValtas: Int =0
    private var  masodik : Int =0
    private var  szin: Int  = 0
    //____________________________________________________
    private var latitude : Double = 47.588009
    private var longitude : Double =19.115006
    private var ollatitude : Double = 0.0
    private var ollongitude : Double = 0.0

    private lateinit var map: GoogleMap
    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var lastLocation: Location
    //private lateinit var fusedLocationClient: FusedLocationProviderClient
    private fun updateCamera() {

        if(isMap) {
            val currentLatLng = LatLng(latitude, longitude)

            if(!LogicHandler.compass)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18.5f))

            val msg = "New Latitude: " + latitude + "\nNew Longitude: " + longitude
            val toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()

            if(LogicHandler.compass) {
                //új bearing test

//               val oldPos = map.getCameraPosition()
//               val pos = CameraPosition.builder(oldPos).bearing(bearing).build()
//
//
//               map.animateCamera(CameraUpdateFactory.newCameraPosition(pos))
//               map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18.5f))
                //__________________
                val oldLoc = Location("service Provider")
                oldLoc.latitude=ollatitude
                oldLoc.longitude=ollongitude

                val newdLoc = Location("service Provider")
                newdLoc.latitude=latitude
                newdLoc.longitude=longitude


                val bearing = oldLoc.bearingTo(newdLoc)

                val camPos = CameraPosition
                    .builder(
                        map.cameraPosition // current Camera
                    )
                    .bearing(bearing)
                    .zoom(18.5f)
                    .target(currentLatLng)
                    .build()
                map.animateCamera(CameraUpdateFactory.newCameraPosition(camPos))
                //map.moveCamera(CameraUpdateFactory.zoomTo(18.5f))
                //map.animateCamera(CameraUpdateFactory.)
                //map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18.5f))
                val toast2 = Toast.makeText(mContext, "Kép elforgatva", Toast.LENGTH_SHORT)
                toast2.setGravity(Gravity.BOTTOM, 0, 0)
                toast2.show()
                //_________________


//               val oldPos = map.getCameraPosition()
//
//               val pos = CameraPosition.builder(oldPos).bearing(mbearing).build()
//
//               map.animateCamera(CameraUpdateFactory.newCameraPosition(pos))
            }
        }
    }


    //    override lateinit var locationManager: LocationManager
    //    override lateinit var mContext: Context
        override var locationListenerGPS: LocationListener = object : LocationListener {
            @SuppressLint("NewApi")
            override fun onLocationChanged(location: android.location.Location) {
               if(LogicHandler.follow) {
                   if(timer!=null)
                       timer?.cancel()
                   ollatitude = latitude
                   ollongitude=longitude
                   latitude = location.latitude
                   longitude = location.longitude
                   updateCamera()




//                   val currentLatLng = LatLng(latitude, longitude)
//                   map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18.5f))
//
//
//                   val oldPos = map.getCameraPosition()
//                   val pos = CameraPosition.builder(oldPos).bearing(mbearing).build()
//                   map.moveCamera(CameraUpdateFactory.newCameraPosition(pos))



//                   for(cross in LogicHandler.chosen.crossroads){
//                       if (cross.Warning(latitude,longitude)){
//                           val toast2 = Toast.makeText(mContext, "300 méteren belül veszélyes kereszteződés!", Toast.LENGTH_LONG)
//                           toast2.show()
//                       }
//                   }
//                   for(stop in LogicHandler.chosen.lineStops){
//                       if (stop.lamp.Warning(latitude,longitude)){
//                           val toast2 = Toast.makeText(mContext, "A ${stop.name} megállóban vagy", Toast.LENGTH_LONG)
//                           toast2.show()
//                           if(stop.lamp.vanLampa()){
//
//                               val ciklus = stop.lamp.timeLeft()
//                               elsoValtas = ciklus[0]
//                               masodik = ciklus[1]
//                               szin=ciklus[3]
//                                val toast = Toast.makeText(mContext,"Most : " + if(szin==0)"ZÖLD" else "PIROS", Toast.LENGTH_LONG)
//                                toast.setGravity(Gravity.TOP, 0, 0)
//                                toast.show()
//                               timerStart()
//                           }
//                       }
//                   }
               }
                // Sensor
//               var field  = GeomagneticField(
//                   location.latitude.toFloat(),
//                   location.longitude.toFloat(),
//                   location.altitude.toFloat(),
//                System.currentTimeMillis()
//
//               )
                //mDeclination = field.declination
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
        super<AudioActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow__me)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        // best?
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mContext = this
        val provider = locationManager.getBestProvider(criteria,true)
        this.locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.locationManager.requestLocationUpdates(provider/*LocationManager.GPS_PROVIDER*/, 100, 5f, this.locationListenerGPS)


        //Sensor testing


        if(LogicHandler.follow) {

            textToSpeech = TextToSpeech(this,this)

//            mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//            haveAccelerometer = mSensorManager!!.registerListener(
//                mSensorEventListener,
//                mAccelerometer,
//                SensorManager.SENSOR_DELAY_GAME
//            )
//            mMagnetometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
//            haveMagnetoMeter = mSensorManager!!.registerListener(
//                mSensorEventListener,
//                mMagnetometer,
//                SensorManager.SENSOR_DELAY_GAME
//            )
//            mRotationmeter = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
//            mGyro = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
//            if (haveAccelerometer && haveMagnetoMeter) {
//                //Toast.makeText(mContext, "Minden rendben kapitány!", Toast.LENGTH_LONG).show()
//            } else {
//                //Toast.makeText(mContext, "Baj van főnök", Toast.LENGTH_LONG).show()
//            }
        }
    }

    private var isMap = false
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        isMap = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if(location!=null){
                lastLocation = location
                val currentLatLng = LatLng(location.latitude,location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,18f))
                ollatitude= location.latitude
                ollongitude = location.longitude
            }
        }

        /*val test1 = MarkerOptions().position(LatLng(47.589092, 19.114647))
        val test2 = MarkerOptions().position(LatLng(47.591574, 19.118126))
        map.addMarker(test1)
        map.addMarker(test2)*/

        for (i in 0..1){ //megállók, lámpák, kereszteződések lepakolása
            for (j in LogicHandler.chosen.coordinateList(i)){
                val tempMarkerOpt = MarkerOptions().position(LatLng(j[0].toDouble(),j[1].toDouble() ))
                when(i) {
                    0 ->
                    {
                        tempMarkerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                        tempMarkerOpt.title(j[2])}
                    1->{
                        tempMarkerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        tempMarkerOpt.title(j[2])}
                }

                val marker = map.addMarker(tempMarkerOpt)
                marker.showInfoWindow()
            }
        }
        var line = PolylineOptions()
        for (i in LogicHandler.chosen.lineTrack)
            line.add(LatLng(i.lat,i.lon))

        map.addPolyline(line.zIndex(1000f).width(8f).color(Color.argb(255,255,204,0)))
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(47.589092, 19.114647), 18.5f))



        // Én helyzetem + gombok
        if(LogicHandler.follow) {
            map.isMyLocationEnabled = true // kék pötty
            map.uiSettings.isScrollGesturesEnabled = false
            map.uiSettings.isZoomControlsEnabled = false
            map.uiSettings.isZoomGesturesEnabled = false
            map.uiSettings.isRotateGesturesEnabled = true

        }else{
            map.uiSettings.isZoomControlsEnabled = true // +/- gombok implementálása
            map.uiSettings.isZoomGesturesEnabled = true
            map.uiSettings.isScrollGesturesEnabled = true
        }

    }

    /*fun timerStart()
    {
        if(timer!=null)
            timer?.cancel()
        timer = Timer(false)
        timerTask = object : TimerTask(){
            override fun run(){
                handler.post{lampCountdown()}
            }
        }
        timer?.scheduleAtFixedRate(timerTask,0,1000)
    }*/

    /*fun lampCountdown()
    {
        val lampaszin : String = if(szin==0) "tiltást" else "szabadot"
        val toast3 = Toast.makeText(mContext, "Első: $elsoValtas, Második: $masodik $lampaszin", Toast.LENGTH_SHORT)
        toast3.setGravity(Gravity.CENTER,0,0)
        toast3.show()
        if(elsoValtas==10)
        {
            val toast2 = Toast.makeText(mContext, "10 másodpercen belül a lámpa $lampaszin ad", Toast.LENGTH_LONG)
            toast2.show()
            elsoValtas = elsoValtas+masodik-1
            masodik = 0
            szin = 1-szin
            if(elsoValtas<10)
                timer!!.cancel()
        }
        else if(elsoValtas>10)
        {
            elsoValtas--
        }
        else if(elsoValtas<10)
        {
            elsoValtas = elsoValtas+masodik
            masodik = 0
            szin = 1-szin
        }

    }*/

    override fun warnUser(warning: String) {
        val toast2 = Toast.makeText(mContext, warning, Toast.LENGTH_LONG).show()
        super.warnUser(warning)
    }





//Sensor testing


//    private var mDeclination: Float = 0f
//
//    private var mRotationMatrix = FloatArray(16)
//
//
//
//    private var mAzimuth : Int = 0
//    private var mSensorManager: SensorManager? = null
//    private lateinit var mAccelerometer : Sensor
//    private lateinit var mMagnetometer : Sensor
//    private lateinit var mGyro : Sensor
//    private lateinit var mRotationmeter:Sensor
//
//    var haveAccelerometer = false
//    var haveMagnetoMeter = false
//    var mbearing : Float = 0f
//
//    private var mSensorEventListener: SensorEventListener = object : SensorEventListener{
//
//        var gData = FloatArray(3) // accelerometer
//        var mData = FloatArray(3) // magnetometer
//        var rMat = FloatArray(9)
//        var iMat = FloatArray(9)
//        var orientation = FloatArray(3)
//
//
//        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {          //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun onSensorChanged(event: SensorEvent?) {
//
//                var data: FloatArray
//                when (event!!.sensor.type) {
//                    Sensor.TYPE_ACCELEROMETER -> gData = event.values.clone()
//                    Sensor.TYPE_MAGNETIC_FIELD -> mData = event.values.clone()
//                    Sensor.TYPE_ROTATION_VECTOR -> {
//                        SensorManager.getRotationMatrixFromVector(
//                            mRotationMatrix, event.values
//                        )
//                        SensorManager.getOrientation(mRotationMatrix, orientation)
//                        mbearing = (Math.toDegrees(orientation[0].toDouble()) + mDeclination).toFloat()
//
//                        updateCamera()
//                    }
//                    else -> return
//                }
//                if (SensorManager.getRotationMatrix(rMat, iMat, gData, mData)) {
//                    mAzimuth = ((Math.toDegrees(
//                        SensorManager.getOrientation(
//                            rMat,
//                            orientation
//                        )[0].toDouble()
//                    ) + 360) % 360).toInt()
//                }
//
//
//
//        }
//
//    }


    override fun onBackPressed() {

        if (LogicHandler.follow) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Vezetés közben ne használja készülék többi funkcióját!")
            builder.setCancelable(true)
            builder.setNegativeButton("Megértettem!", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    finish()
                }

            })
            builder.setPositiveButton("Maradok", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0!!.cancel()
                }

            })
            val alertDialog = builder.create()
            alertDialog.show()
            //
        }
        else
            super.onBackPressed()
    }

}


