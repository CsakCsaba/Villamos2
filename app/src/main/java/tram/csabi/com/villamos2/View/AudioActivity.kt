package tram.csabi.com.villamos2.View

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_audio.*
import tram.csabi.com.villamos2.Logic.LogicHandler
import tram.csabi.com.villamos2.R
import java.util.*

@Suppress("DEPRECATION")
open class AudioActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var textToSpeech : TextToSpeech
    var timer: Timer?=null
    val handler = Handler()
    lateinit var timerTask: TimerTask

    private var  elsoValtas: Int =0
    private var  masodik : Int =0
    private var  szin: Int  = 0


    protected lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var locationManager: LocationManager
    lateinit var mContext: Context
    protected open var locationListenerGPS: LocationListener = object : LocationListener {
        @SuppressLint("NewApi")
        override fun onLocationChanged(location: android.location.Location) {
            if(LogicHandler.follow) {
                if(timer!=null)
                    timer?.cancel()
                val latitude = location.latitude
                val longitude = location.longitude
                checkCoordinates(latitude,longitude)
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    @SuppressLint("NewApi")
    protected open fun checkCoordinates(latitude: Double, longitude: Double) {
        for(cross in LogicHandler.chosen.crossroads){
            if (cross.Warning(latitude,longitude)){

                val warning = "300 méteren belül {${cross.name}}"
                warnUser(warning)
            }
        }
        for(stop in LogicHandler.chosen.lineStops){
            if (stop.lamp.Warning(latitude,longitude)){
                val warning = "A ${stop.name} megállóban vagy"
                warnUser(warning)
                if(stop.lamp.vanLampa()){
                    val ciklus = stop.lamp.timeLeft()
                    elsoValtas = ciklus[0]
                    masodik = ciklus[1]
                    szin=ciklus[3]
                    val toast = Toast.makeText(mContext,"Most : " + if(szin==0)"ZÖLD" else "PIROS", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                    timerStart()
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_audio)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mContext = this
        this.locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10f, this.locationListenerGPS)

        textToSpeech = TextToSpeech(this,this)

        button.setOnClickListener{
            t2tFunction()
        }
        button2.setOnClickListener{
            tryIt()
        }
    }
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS ){
            val res: Int = textToSpeech.setLanguage(Locale("hu","HU"))
            if(res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED){}
        }
    }

    fun t2tFunction(){
        val text = "Hang teszt. 10 másodpercen belül vált a lámpa!\n 300 méteren belül veszélyes útszakasz!\n Bécsi út vörösvári út következik"
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null)
    }
    fun tryIt()
    {
        val text = editText.text.toString()
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null)
    }

    override fun onDestroy() {
        if(textToSpeech!=null){
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        if(timer!=null){
            timer!!.cancel()
        }
        locationManager.removeUpdates(locationListenerGPS)
        super.onDestroy()
    }
    fun timerStart()
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
    }

    fun lampCountdown()
    {
        val lampaszin : String = if(szin==0) "tiltást" else "szabadot"
        val toast3 = Toast.makeText(mContext, "Első: $elsoValtas, Második: $masodik $lampaszin", Toast.LENGTH_SHORT)
        /*toast3.setGravity(Gravity.CENTER,0,0)
        toast3.show()*/
        if(elsoValtas==10)
        {
            //textToSpeech.speak("10 másodpercen belül a lámpa $lampaszin ad",TextToSpeech.QUEUE_FLUSH,null)
            val warning : String = "10 másodpercen belül a lámpa $lampaszin ad"
            warnUser(warning)
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

    }

    open fun warnUser(warning: String) {
        textToSpeech.speak(warning,TextToSpeech.QUEUE_FLUSH,null)
    }



    //window

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
