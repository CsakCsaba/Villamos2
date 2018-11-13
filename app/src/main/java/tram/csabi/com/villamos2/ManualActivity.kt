package tram.csabi.com.villamos2

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import kotlinx.android.synthetic.main.activity_manual.*
import kotlinx.android.synthetic.main.activity_menu.*

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
                h=LogicHandler.chosen.instruments.keys.size-1
            else
                h--
            changeText()
        }
       /* manualText.text = LogicHandler.t.toString()+ "\n\n\n"                +
                "Lorizzle go to hizzle dolor sizzle amizzle, dang adipiscing sizzle. Nullizzle daahng dawg velizzle, shizzle my nizzle crocodizzle volutpizzle, suscipizzle quis, funky fresh vel, arcu. Pellentesque funky fresh fizzle. Sizzle eros. Daahng dawg at dolor dapibus turpis tempizzle ass. Maurizzle pellentesque nibh shiznit turpizzle. For sure izzle tortizzle. Pellentesque eleifend rhoncus crunk. In fo shizzle mah nizzle fo rizzle, mah home g-dizzle mammasay mammasa mamma oo sa shizznit dictumst. Donec dapibus. Ghetto urna, pretizzle gangster, mattizzle uhuh ... yih!, eleifend own yo', shiz. Yo suscipizzle. Integizzle semper velit sizzle purus.\n" +
                "\n" +
                "Donizzle posuere shizzlin dizzle mauris. Phasellizzle shiz elizzle away shiz izzle tincidunt. Shut the shizzle up a cool. Gizzle in lacus sed mauris elementum boom shackalack. Nunc at dizzle sizzle its fo rizzle nizzle ultricizzle porta. Break it down velizzle tortor, shit i'm in the shizzle, hendrerit quis, adipiscing quis, dui. Etizzle velit sheezy, aliquizzle brizzle, pharetra non, dictizzle gangster, turpizzle. Fizzle sheezy. Cras lorizzle. Sure vitae erizzle ut libero commodo adipiscing. Fusce ac ass eu nibh i saw beyonces tizzles and my pizzle went crizzle mattizzle. Phasellus that's the shizzle rizzle nizzle erizzle. That's the shizzle lorem leo, sollicitudin sizzle, mattis izzle, commodo nec, shit. Donizzle faucibus ghetto ligula. feugiat, tellizzle dang my shizz boofron, sapizzle shut the shizzle up tincidunt ante, egizzle dapibus pede fo shizzle mah nizzle fo rizzle, mah home g-dizzle gizzle lorem. Phasellus quizzle shiznit, for sure id, tempus izzle, semper in, sapizzle. Ut crazy magna i'm in the shizzle ipsizzle. Sizzle brizzle mah nizzle, suscipizzle shizznit, shut the shizzle up get down get down, rutrizzle boofron, velizzle. Gizzle things fo shizzle my nizzle. Sizzle magna crazy amet risus iaculizzle shut the shizzle up.\n" +
                "\n" +
                "Sizzle sagittis leo non nisi. Boom shackalack rhoncizzle, arcu non shiznit yippiyo, owned nulla aliquizzle sizzle, non auctor nulla felizzle a da bomb. Suspendisse sizzle shizzlin dizzle augue. Yo mamma egestas doggy izzle fo shizzle my nizzle. Prizzle consectetuer its fo rizzle sapizzle. Etiam crazy, dizzle boom shackalack amet accumsan tincidunt, leo yo ultrices hizzle, boofron vestibulizzle erat ghetto sizzle gangsta purus. Maecenizzle ass tortizzle gangsta enizzle. Phasellizzle stuff. Nulla tellivizzle lacizzle, convallis nec, aliquizzle crazy sure, pulvinar egestizzle, augue. Vivamus i'm in the shizzle. Vestibulizzle ante ipsum fizzle izzle faucibus orci we gonna chung et ultrices posuere owned Curae; The bizzle bling bling the bizzle eu owned bibendizzle crunk. Fusce mofo tortizzle, bling bling shizznit, sempizzle vizzle, commodo ac, yippiyo. Etizzle feugizzle, for sure egizzle vehicula cool, lorizzle justo crazy phat, id rizzle mi crazy vitae erizzle.\n" +
                "\n" +
                "Sizzle . Shizznit sagittizzle massa shizznit maurizzle. Break yo neck, yall ante ipsum primis shit faucibus ass luctizzle i saw beyonces tizzles and my pizzle went crizzle ultricizzle posuere cubilia I saw beyonces tizzles and my pizzle went crizzle; Aenizzle dawg. Pellentesque habitant morbi tristique senectizzle sheezy boofron et you son of a bizzle fo shizzle shiznit egestizzle. Donec tempizzle gizzle velizzle. Uhuh ... yih! erat fo shizzle my nizzle. Own yo' we gonna chung enizzle, scelerisque dope, dignissim a, get down get down fo, arcu. Shizzlin dizzle fo shizzle mah nizzle fo rizzle, mah home g-dizzle. Donizzle shiz, est dang pharetra aliquizzle, magna check out this ultricies sizzle, fo shizzle mah nizzle fo rizzle, mah home g-dizzle ghetto urna brizzle quis owned. Izzle tellizzle neque, ass break yo neck, yall, shit ma nizzle, vulputate nizzle, leo. Praesent shiznit, bibendizzle sizzle nizzle, interdizzle dawg, dignissim malesuada, arcu. Aenean egizzle ipsum izzle shiz ullamcorpizzle daahng dawg. Donec phat. Mauris nizzle urna, i'm in the shizzle izzle, scelerisque yo, funky fresh nizzle, fo shizzle mah nizzle fo rizzle, mah home g-dizzle. Etizzle gravida."
    */

    }

    @TargetApi(Build.VERSION_CODES.O)
    fun changeText()
    {
        hibafajta.justificationMode=JUSTIFICATION_MODE_INTER_WORD
        hibafajta.text = LogicHandler.chosen.instruments.keys.elementAt(h)
        manualText.text = LogicHandler.chosen.instrumentDescription(hibafajta.text as String)
    }
}

