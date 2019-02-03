package tram.csabi.com.villamos2.View

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_menu.*
import tram.csabi.com.villamos2.Logic.LogicHandler
import tram.csabi.com.villamos2.R


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        LogicHandler.fillTrams()

        numberOfCurrentTram.text = LogicHandler.currentNumber().toString()
        rightTram.setOnClickListener{
            LogicHandler.increment()
            numberOfCurrentTram.text = LogicHandler.currentNumber().toString()

        }
        leftTram.setOnClickListener{
            LogicHandler.decrement()
            numberOfCurrentTram.text = LogicHandler.currentNumber().toString()

        }





        followme.setOnClickListener{
            LogicHandler.follow = true
            startActivity(Intent(this, Follow_Me::class.java))
        }
        maponly.setOnClickListener{
            LogicHandler.follow = false
            startActivity(Intent(this, Follow_Me::class.java))
        }
        speak2me.setOnClickListener{
            startActivity(Intent(this, AudioActivity::class.java))
        }
        manual.setOnClickListener{
            startActivity(Intent(this, ManualActivity::class.java))
        }
        downloadmanager.setOnClickListener {
            startActivity(Intent(this, DataBaseHandler::class.java))
        }
        compass.setOnCheckedChangeListener{ buttonView, isChecked->
            LogicHandler.changeCompass(compass.isChecked)
        }


        setUpMap()
    }
    private fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(
                this, //itt ellenőrzi
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,  //itt kéri el
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                Follow_Me.LOCATION_PERMISSION_REQUEST_CODE
            )
            return   //A kód ellenőrzi hogy az ACCES_FINE_LOCATION engedély adva van-e, ha nem akkor elkéri a felhasználótol
        }
    }
}
