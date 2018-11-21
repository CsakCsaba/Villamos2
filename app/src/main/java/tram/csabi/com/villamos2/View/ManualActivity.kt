package tram.csabi.com.villamos2.View

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import kotlinx.android.synthetic.main.activity_manual.*
import tram.csabi.com.villamos2.Logic.LogicHandler
import tram.csabi.com.villamos2.R

class ManualActivity : AppCompatActivity() {

    var h = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual)

        changeText()

        j.setOnClickListener{
            var h2 = h
            if(h+1== LogicHandler.chosen.instruments.size)
                h=0
            else
                h++
            changeText()
        }
        b.setOnClickListener{
            var h2 = h
            if(h-1 == -1)
                h= LogicHandler.chosen.instruments.keys.size-1
            else
                h--
            changeText()
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    fun changeText()
    {
        hibafajta.justificationMode=JUSTIFICATION_MODE_INTER_WORD
        hibafajta.text = LogicHandler.chosen.instruments.keys.elementAt(h)
        manualText.text = LogicHandler.chosen.instrumentDescription(hibafajta.text as String)
    }
}

