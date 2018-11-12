package tram.csabi.com.villamos2

import tram.csabi.com.villamos2.classes.Crossroads
import tram.csabi.com.villamos2.classes.Lamp
import tram.csabi.com.villamos2.classes.TramLine
import tram.csabi.com.villamos2.classes.TramStop

class LogicHandler {
    companion object {
        var t = -1
        var trams :MutableList<TramLine> = mutableListOf()
        lateinit var chosen : TramLine
        var follow = true

        fun fillTrams(){

            val utvonal = listOf(
                doubleArrayOf(47.582102, 19.116901),
                doubleArrayOf(47.574732, 19.115206),
                doubleArrayOf(47.577726, 19.115918),
                doubleArrayOf(47.574732, 19.115206),
                doubleArrayOf(47.572159, 19.114164),
                doubleArrayOf(47.565838, 19.111601),
                doubleArrayOf(47.565564, 19.111388),
                doubleArrayOf(47.565455, 19.111055),
                doubleArrayOf(47.565430, 19.110747),
                doubleArrayOf(47.565430, 19.110747),
                doubleArrayOf(47.565337, 19.106411)
            )
            val allomasok = listOf(
                TramStop("ÓceánÁrok", 47.582102, 19.116901),
                TramStop("Járműtelep", 47.577726, 19.115918),
                TramStop("Járműtelep", 47.572159, 19.114164),
                TramStop("Járműtelep", 47.565838, 19.111601)

            )
            val lampa = listOf(
                Lamp(
                    listOf(
                        intArrayOf(5, 0, 0, 1, 90, 60),
                        intArrayOf(9, 0, 0, 1, 90, 60),
                        intArrayOf(17, 0, 0, 1, 90, 45),
                        intArrayOf(23, 0, 0, 1, 0, 0)
                    )
                    , 47.565692, 19.111377
                )
            )
            val kereszt = listOf(
                Crossroads(47.590361, 19.116744)
            )
            val inst = hashMapOf<String,String>()
            inst.put("Ablak","Az ablak könnyedén törhető.")
            inst.put("Kamu", "Lorizzle go to hizzle dolor sizzle amizzle, dang adipiscing sizzle. Nullizzle daahng dawg velizzle, shizzle my nizzle crocodizzle volutpizzle, suscipizzle quis, funky fresh vel, arcu. Pellentesque funky fresh fizzle. Sizzle eros. Daahng dawg at dolor dapibus turpis tempizzle ass. Maurizzle pellentesque nibh shiznit turpizzle. For sure izzle tortizzle. Pellentesque eleifend rhoncus crunk. In fo shizzle mah nizzle fo rizzle, mah home g-dizzle mammasay mammasa mamma oo sa shizznit dictumst. Donec dapibus. Ghetto urna, pretizzle gangster, mattizzle uhuh ... yih!, eleifend own yo', shiz. Yo suscipizzle. Integizzle semper velit sizzle purus.\n" +
                    "\n" +
                    "Donizzle posuere shizzlin dizzle mauris. Phasellizzle shiz elizzle away shiz izzle tincidunt. Shut the shizzle up a cool. Gizzle in lacus sed mauris elementum boom shackalack. Nunc at dizzle sizzle its fo rizzle nizzle ultricizzle porta. Break it down velizzle tortor, shit i'm in the shizzle, hendrerit quis, adipiscing quis, dui. Etizzle velit sheezy, aliquizzle brizzle, pharetra non, dictizzle gangster, turpizzle. Fizzle sheezy. Cras lorizzle. Sure vitae erizzle ut libero commodo adipiscing. Fusce ac ass eu nibh i saw beyonces tizzles and my pizzle went crizzle mattizzle. Phasellus that's the shizzle rizzle nizzle erizzle. That's the shizzle lorem leo, sollicitudin sizzle, mattis izzle, commodo nec, shit. Donizzle faucibus ghetto ligula. feugiat, tellizzle dang my shizz boofron, sapizzle shut the shizzle up tincidunt ante, egizzle dapibus pede fo shizzle mah nizzle fo rizzle, mah home g-dizzle gizzle lorem. Phasellus quizzle shiznit, for sure id, tempus izzle, semper in, sapizzle. Ut crazy magna i'm in the shizzle ipsizzle. Sizzle brizzle mah nizzle, suscipizzle shizznit, shut the shizzle up get down get down, rutrizzle boofron, velizzle. Gizzle things fo shizzle my nizzle. Sizzle magna crazy amet risus iaculizzle shut the shizzle up.\n" +
                    "\n" +
                    "Sizzle sagittis leo non nisi. Boom shackalack rhoncizzle, arcu non shiznit yippiyo, owned nulla aliquizzle sizzle, non auctor nulla felizzle a da bomb. Suspendisse sizzle shizzlin dizzle augue. Yo mamma egestas doggy izzle fo shizzle my nizzle. Prizzle consectetuer its fo rizzle sapizzle. Etiam crazy, dizzle boom shackalack amet accumsan tincidunt, leo yo ultrices hizzle, boofron vestibulizzle erat ghetto sizzle gangsta purus. Maecenizzle ass tortizzle gangsta enizzle. Phasellizzle stuff. Nulla tellivizzle lacizzle, convallis nec, aliquizzle crazy sure, pulvinar egestizzle, augue. Vivamus i'm in the shizzle. Vestibulizzle ante ipsum fizzle izzle faucibus orci we gonna chung et ultrices posuere owned Curae; The bizzle bling bling the bizzle eu owned bibendizzle crunk. Fusce mofo tortizzle, bling bling shizznit, sempizzle vizzle, commodo ac, yippiyo. Etizzle feugizzle, for sure egizzle vehicula cool, lorizzle justo crazy phat, id rizzle mi crazy vitae erizzle.\n" +
                    "\n" +
                    "Sizzle . Shizznit sagittizzle massa shizznit maurizzle. Break yo neck, yall ante ipsum primis shit faucibus ass luctizzle i saw beyonces tizzles and my pizzle went crizzle ultricizzle posuere cubilia I saw beyonces tizzles and my pizzle went crizzle; Aenizzle dawg. Pellentesque habitant morbi tristique senectizzle sheezy boofron et you son of a bizzle fo shizzle shiznit egestizzle. Donec tempizzle gizzle velizzle. Uhuh ... yih! erat fo shizzle my nizzle. Own yo' we gonna chung enizzle, scelerisque dope, dignissim a, get down get down fo, arcu. Shizzlin dizzle fo shizzle mah nizzle fo rizzle, mah home g-dizzle. Donizzle shiz, est dang pharetra aliquizzle, magna check out this ultricies sizzle, fo shizzle mah nizzle fo rizzle, mah home g-dizzle ghetto urna brizzle quis owned. Izzle tellizzle neque, ass break yo neck, yall, shit ma nizzle, vulputate nizzle, leo. Praesent shiznit, bibendizzle sizzle nizzle, interdizzle dawg, dignissim malesuada, arcu. Aenean egizzle ipsum izzle shiz ullamcorpizzle daahng dawg. Donec phat. Mauris nizzle urna, i'm in the shizzle izzle, scelerisque yo, funky fresh nizzle, fo shizzle mah nizzle fo rizzle, mah home g-dizzle. Etizzle gravida."
            )
            var traaaaam: TramLine =
                TramLine(14, utvonal, allomasok, lampa, kereszt, inst)

            val utvonal2 = listOf(
                doubleArrayOf(47.582102, 19.116901),
                doubleArrayOf(47.574732, 19.115206),
                doubleArrayOf(47.577726, 19.115918),
                doubleArrayOf(47.574732, 19.115206),
                doubleArrayOf(47.572159, 19.114164)
            )
            val allomasok2 = listOf(
                TramStop("Járműtelep", 47.582102, 19.116901),
                TramStop("Járműtelep", 47.577726, 19.115918)
            )
            val lampa2: List<Lamp> = listOf()

            val kereszt2 = listOf(
                Crossroads(47.590361, 19.116744)
            )

            var traaaaam2: TramLine =
                TramLine(10, utvonal2, allomasok2, lampa2, kereszt2, inst)


            this.trams.add(traaaaam)
            this.trams.add(traaaaam2)
            t=0
            chosen = this.trams[0]
        }
        fun lineNumbers(): IntArray
        {
            var numbers = IntArray(trams.size)
            for (i in 0.. numbers.size-1){
                numbers[i] = trams[i].lineNumber.toInt()
            }
            return numbers
        }
        fun currentNumber():Int = chosen.lineNumber.toInt()
        fun increment(){
            if (t+1== lineNumbers().size)
                t=0
            else
                t++
            chosen = trams[t]
        }
        fun decrement(){
            if (t-1== -1)
                t= lineNumbers().size-1
            else
                t--
            chosen= trams[t]
        }
    }
}