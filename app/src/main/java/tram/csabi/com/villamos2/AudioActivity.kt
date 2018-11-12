package tram.csabi.com.villamos2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeechService
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_audio.*
import org.w3c.dom.Text
import java.util.*

@Suppress("DEPRECATION")
class AudioActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var textToSpeech : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        textToSpeech = TextToSpeech(this,this)
        var t = 1

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
            if(res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED)
                Toast.makeText(applicationContext,"Nem lesz jó", Toast.LENGTH_SHORT).show()
            else
            {

            }
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
        super.onDestroy()
    }
}
