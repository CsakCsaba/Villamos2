package tram.csabi.com.villamos2.View

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.login_window.*
import tram.csabi.com.villamos2.Logic.LogicHandler
import tram.csabi.com.villamos2.R

class Login : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_window)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width : Int = dm.widthPixels
        val height : Int = dm.heightPixels

        window.setLayout((width*.8).toInt(), (height*.6).toInt())

        login.setOnClickListener {
            LogicHandler.loginSuccess = true
            onBackPressed()
        }

    }
}
