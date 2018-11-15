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

        fun fillSzoveg() :HashMap<String,String>
        {

            var inst =hashMapOf<String,String>()
            inst.put("Ablak","Az ablak könnyedén törhető.")
            inst.put("Walesi Bárdok","Edward király, angol király\n" +
                    "Léptet fakó lován:\n" +
                    "Hadd látom, úgymond, mennyit ér\n" +
                    "A velszi tartomány.\n" +
                    "\n" +
                    "Van-e ott folyó és földje jó?\n" +
                    "Legelőin fű kövér?\n" +
                    "Használt-e a megöntözés:\n" +
                    "A pártos honfivér?\n" +
                    "\n" +
                    "S a nép, az istenadta nép,\n" +
                    "Ha oly boldog-e rajt’\n" +
                    "Mint akarom, s mint a barom,\n" +
                    "Melyet igába hajt?\n" +
                    "\n" +
                    "Felség! valóban koronád\n" +
                    "Legszebb gyémántja Velsz:\n" +
                    "Földet, folyót, legelni jót,\n" +
                    "Hegy-völgyet benne lelsz.\n" +
                    "\n" +
                    "S a nép, az istenadta nép\n" +
                    "Oly boldog rajta, Sire!\n" +
                    "Kunyhói mind hallgatva, mint\n" +
                    "Megannyi puszta sir.\n" +
                    "\n" +
                    "Edward király, angol király\n" +
                    "Léptet fakó lován:\n" +
                    "Körötte csend amerre ment,\n" +
                    "És néma tartomány.\n" +
                    "\n" +
                    "Montgomery a vár neve,\n" +
                    "Hol aznap este szállt;\n" +
                    "Montgomery, a vár ura,\n" +
                    "Vendégli a királyt.\n" +
                    "\n" +
                    "Vadat és halat, s mi jó falat\n" +
                    "Szem-szájnak ingere,\n" +
                    "Sürgő csoport, száz szolga hord,\n" +
                    "Hogy nézni is tereh;\n" +
                    "\n" +
                    "S mind, amiket e szép sziget\n" +
                    "Ételt-italt terem;\n" +
                    "S mind, ami bor pezsegve forr\n" +
                    "Túl messzi tengeren.\n" +
                    "\n" +
                    "Ti urak, ti urak! hát senkisem\n" +
                    "Koccint értem pohárt?\n" +
                    "Ti urak, ti urak!... ti velsz ebek!\n" +
                    "Ne éljen Eduárd?\n" +
                    "\n" +
                    "Vadat és halat, s mi az ég alatt\n" +
                    "Szem-szájnak kellemes,\n" +
                    "Azt látok én: de ördög itt\n" +
                    "Belül minden nemes.\n" +
                    "\n" +
                    "Ti urak, ti urak, hitvány ebek!\n" +
                    "Ne éljen Eduárd?\n" +
                    "Hol van, ki zengje tetteim -\n" +
                    "Elő egy velszi bárd!\n" +
                    "\n" +
                    "Egymásra néz a sok vitéz,\n" +
                    "A vendég velsz urak;\n" +
                    "Orcáikon, mint félelem,\n" +
                    "Sápadt el a harag.\n" +
                    "\n" +
                    "Szó bennszakad, hang fennakad,\n" +
                    "Lehellet megszegik. -\n" +
                    "Ajtó megől fehér galamb,\n" +
                    "Ősz bárd emelkedik.\n" +
                    "\n" +
                    "Itt van, király, ki tetteidet\n" +
                    "Elzengi, mond az agg;\n" +
                    "S fegyver csörög, haló hörög\n" +
                    "Amint húrjába csap.\n" +
                    "\n" +
                    "„Fegyver csörög, haló hörög,\n" +
                    "A nap vértóba száll,\n" +
                    "Vérszagra gyűl az éji vad:\n" +
                    "Te tetted ezt, király!\n" +
                    "\n" +
                    "Levágva népünk ezrei,\n" +
                    "Halomba, mint kereszt,\n" +
                    "Hogy sírva tallóz aki él:\n" +
                    "Király, te tetted ezt!”\n" +
                    "\n" +
                    "Máglyára! el! igen kemény -\n" +
                    "Parancsol Eduárd -\n" +
                    "Ha! lágyabb ének kell nekünk;\n" +
                    "S belép egy ifju bárd.\n" +
                    "\n" +
                    "„Ah! lágyan kél az esti szél\n" +
                    "Milford-öböl felé;\n" +
                    "Szüzek siralma, özvegyek\n" +
                    "Panasza nyög belé.\n" +
                    "\n" +
                    "Ne szülj rabot, te szűz! anya\n" +
                    "Ne szoptass csecsemőt!...”\n" +
                    "S int a király. S elérte még\n" +
                    "A máglyára menőt.\n" +
                    "\n" +
                    "De vakmerőn s hivatlanúl\n" +
                    "Előáll harmadik;\n" +
                    "Kobzán a dal magára vall,\n" +
                    "Ez íge hallatik:\n" +
                    "\n" +
                    "„Elhullt csatában a derék -\n" +
                    "No halld meg, Eduárd:\n" +
                    "Neved ki diccsel ejtené,\n" +
                    "Nem él oly velszi bárd.\n" +
                    "\n" +
                    "„Emléke sír a lanton még -\n" +
                    "No halld meg, Eduárd:\n" +
                    "Átok fejedre minden dal,\n" +
                    "Melyet zeng velszi bárd.”\n" +
                    "\n" +
                    "Meglátom én! - S parancsot ád\n" +
                    "Király rettenetest:\n" +
                    "Máglyára, ki ellenszegűl,\n" +
                    "Minden velsz énekest!\n" +
                    "\n" +
                    "Szolgái szét száguldanak,\n" +
                    "Ország-szerin, tova.\n" +
                    "Montgomeryben így esett\n" +
                    "A híres lakoma. -\n" +
                    "\n" +
                    "S Edvárd király, angol király\n" +
                    "Vágtat fakó lován;\n" +
                    "Körötte ég földszint az ég:\n" +
                    "A velszi tartomány.\n" +
                    "\n" +
                    "Ötszáz, bizony, dalolva ment\n" +
                    "Lángsírba velszi bárd:\n" +
                    "De egy se birta mondani\n" +
                    "Hogy: éljen Eduárd. -\n" +
                    "\n" +
                    "Ha, ha! mi zúg?... mi éji dal\n" +
                    "London utcáin ez?\n" +
                    "Felköttetem a lord-majort,\n" +
                    "Ha bosszant bármi nesz!\n" +
                    "\n" +
                    "Áll néma csend; légy szárnya bent,\n" +
                    "Se künn, nem hallatik:\n" +
                    "„Fejére szól, ki szót emel!\n" +
                    "Király nem alhatik.”\n" +
                    "\n" +
                    "Ha, ha! elő síp, dob, zene!\n" +
                    "Harsogjon harsona:\n" +
                    "Fülembe zúgja átkait\n" +
                    "A velszi lakoma...\n" +
                    "\n" +
                    "De túl zenén, túl síp-dobon,\n" +
                    "Riadó kürtön át:\n" +
                    "Ötszáz énekli hangosan\n" +
                    "A vértanúk dalát.")
            inst.put("Kamu", "Lorizzle go to hizzle dolor sizzle amizzle, dang adipiscing sizzle. Nullizzle daahng dawg velizzle, shizzle my nizzle crocodizzle volutpizzle, suscipizzle quis, funky fresh vel, arcu. Pellentesque funky fresh fizzle. Sizzle eros. Daahng dawg at dolor dapibus turpis tempizzle ass. Maurizzle pellentesque nibh shiznit turpizzle. For sure izzle tortizzle. Pellentesque eleifend rhoncus crunk. In fo shizzle mah nizzle fo rizzle, mah home g-dizzle mammasay mammasa mamma oo sa shizznit dictumst. Donec dapibus. Ghetto urna, pretizzle gangster, mattizzle uhuh ... yih!, eleifend own yo', shiz. Yo suscipizzle. Integizzle semper velit sizzle purus.\n" +
                    "\n" +
                    "Donizzle posuere shizzlin dizzle mauris. Phasellizzle shiz elizzle away shiz izzle tincidunt. Shut the shizzle up a cool. Gizzle in lacus sed mauris elementum boom shackalack. Nunc at dizzle sizzle its fo rizzle nizzle ultricizzle porta. Break it down velizzle tortor, shit i'm in the shizzle, hendrerit quis, adipiscing quis, dui. Etizzle velit sheezy, aliquizzle brizzle, pharetra non, dictizzle gangster, turpizzle. Fizzle sheezy. Cras lorizzle. Sure vitae erizzle ut libero commodo adipiscing. Fusce ac ass eu nibh i saw beyonces tizzles and my pizzle went crizzle mattizzle. Phasellus that's the shizzle rizzle nizzle erizzle. That's the shizzle lorem leo, sollicitudin sizzle, mattis izzle, commodo nec, shit. Donizzle faucibus ghetto ligula. feugiat, tellizzle dang my shizz boofron, sapizzle shut the shizzle up tincidunt ante, egizzle dapibus pede fo shizzle mah nizzle fo rizzle, mah home g-dizzle gizzle lorem. Phasellus quizzle shiznit, for sure id, tempus izzle, semper in, sapizzle. Ut crazy magna i'm in the shizzle ipsizzle. Sizzle brizzle mah nizzle, suscipizzle shizznit, shut the shizzle up get down get down, rutrizzle boofron, velizzle. Gizzle things fo shizzle my nizzle. Sizzle magna crazy amet risus iaculizzle shut the shizzle up.\n" +
                    "\n" +
                    "Sizzle sagittis leo non nisi. Boom shackalack rhoncizzle, arcu non shiznit yippiyo, owned nulla aliquizzle sizzle, non auctor nulla felizzle a da bomb. Suspendisse sizzle shizzlin dizzle augue. Yo mamma egestas doggy izzle fo shizzle my nizzle. Prizzle consectetuer its fo rizzle sapizzle. Etiam crazy, dizzle boom shackalack amet accumsan tincidunt, leo yo ultrices hizzle, boofron vestibulizzle erat ghetto sizzle gangsta purus. Maecenizzle ass tortizzle gangsta enizzle. Phasellizzle stuff. Nulla tellivizzle lacizzle, convallis nec, aliquizzle crazy sure, pulvinar egestizzle, augue. Vivamus i'm in the shizzle. Vestibulizzle ante ipsum fizzle izzle faucibus orci we gonna chung et ultrices posuere owned Curae; The bizzle bling bling the bizzle eu owned bibendizzle crunk. Fusce mofo tortizzle, bling bling shizznit, sempizzle vizzle, commodo ac, yippiyo. Etizzle feugizzle, for sure egizzle vehicula cool, lorizzle justo crazy phat, id rizzle mi crazy vitae erizzle.\n" +
                    "\n" +
                    "Sizzle . Shizznit sagittizzle massa shizznit maurizzle. Break yo neck, yall ante ipsum primis shit faucibus ass luctizzle i saw beyonces tizzles and my pizzle went crizzle ultricizzle posuere cubilia I saw beyonces tizzles and my pizzle went crizzle; Aenizzle dawg. Pellentesque habitant morbi tristique senectizzle sheezy boofron et you son of a bizzle fo shizzle shiznit egestizzle. Donec tempizzle gizzle velizzle. Uhuh ... yih! erat fo shizzle my nizzle. Own yo' we gonna chung enizzle, scelerisque dope, dignissim a, get down get down fo, arcu. Shizzlin dizzle fo shizzle mah nizzle fo rizzle, mah home g-dizzle. Donizzle shiz, est dang pharetra aliquizzle, magna check out this ultricies sizzle, fo shizzle mah nizzle fo rizzle, mah home g-dizzle ghetto urna brizzle quis owned. Izzle tellizzle neque, ass break yo neck, yall, shit ma nizzle, vulputate nizzle, leo. Praesent shiznit, bibendizzle sizzle nizzle, interdizzle dawg, dignissim malesuada, arcu. Aenean egizzle ipsum izzle shiz ullamcorpizzle daahng dawg. Donec phat. Mauris nizzle urna, i'm in the shizzle izzle, scelerisque yo, funky fresh nizzle, fo shizzle mah nizzle fo rizzle, mah home g-dizzle. Etizzle gravida."
            )
            inst.put("Barbie", "Hiya Barbie\n" +
                    "Hi Ken!\n" +
                    "Do you want to go for a ride?\n" +
                    "Sure Ken\n" +
                    "Jump in\n" +
                    "I'm a Barbie girl, in a Barbie world\n" +
                    "Life in plastic, it's fantastic\n" +
                    "You can brush my hair, undress me everywhere\n" +
                    "Imagination, life is your creation\n" +
                    "Come on Barbie, let's go party!\n" +
                    "I'm a Barbie girl, in a Barbie world\n" +
                    "Life in plastic, it's fantastic\n" +
                    "You can brush my hair, undress me everywhere\n" +
                    "Imagination, life is your creation\n" +
                    "I'm a blond bimbo girl, in a fantasy world\n" +
                    "Dress me up, make it tight, I'm your dolly\n" +
                    "You're my doll, rock'n'roll, feel the glamor in pink\n" +
                    "Kiss me here, touch me there, hanky panky\n" +
                    "You can touch\n" +
                    "you can play\n" +
                    "if you say \"I'm always yours\"\n" +
                    "I'm a Barbie girl, in a Barbie world\n" +
                    "Life in plastic, it's fantastic\n" +
                    "You can brush my hair, undress me everywhere\n" +
                    "Imagination, life is your creation\n" +
                    "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                    "Come on Barbie, let's go party! (Oh oh)\n" +
                    "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                    "Come on Barbie, let's go party! (Oh oh)\n" +
                    "Make me walk, make me talk, do whatever you please\n" +
                    "I can act like a star, I can beg on my knees\n" +
                    "Come jump in, bimbo friend, let us do it again\n" +
                    "Hit the town, fool around, let's go party\n" +
                    "You can touch\n" +
                    "you can play\n" +
                    "If you say \"I'm always yours\"\n" +
                    "You can touch\n" +
                    "you can play,\n" +
                    "If you say \"I'm always yours\"\n" +
                    "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                    "Come on Barbie, let's go party! (Oh oh)\n" +
                    "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                    "Come on Barbie, let's go party! (Oh oh)\n" +
                    "I'm a Barbie girl, in a Barbie world\n" +
                    "Life in plastic, it's fantastic\n" +
                    "You can brush my hair, undress me everywhere\n" +
                    "Imagination, life is your creation\n" +
                    "I'm a Barbie girl, in a Barbie world\n" +
                    "Life in plastic, it's fantastic\n" +
                    "You can brush my hair, undress me everywhere\n" +
                    "Imagination, life is your creation\n" +
                    "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                    "Come on Barbie, let's go party! (Oh oh)\n" +
                    "Come on Barbie, let's go party! (Ah ah ah yeah)\n" +
                    "Come on Barbie, let's go party! (Oh oh)\n" +
                    "Oh, I'm having so much fun!\n" +
                    "Well Barbie, we are just getting started\n" +
                    "Oh, I love you Ken")

            return inst
        }
        fun fillTrams(){

            val utvonal = listOf(
                doubleArrayOf(47.587420, 19.118058),
                doubleArrayOf(47.585275, 19.117223),
                doubleArrayOf(47.584153, 19.116948),
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
                doubleArrayOf(47.565337, 19.106411),
                doubleArrayOf( 47.565167, 19.100525) ,
                doubleArrayOf(47.565004, 19.093977)

            )

            val allomasok = listOf( // bőrfestő 47.587420, 19.118058
                TramStop("Bőrfestő utca", 47.587420, 19.118058, listOf()), // bőrönd 47.585275, 19.117223
                TramStop("Bőröndös utca", 47.585275, 19.117223, listOf()),
                TramStop("jármű telep utca", 47.582091, 19.116880,
                    listOf(
                        intArrayOf(5, 0, 0, 1, 90, 60),
                        intArrayOf(9, 0, 0, 1, 90, 60),
                        intArrayOf(17, 0, 0, 1, 90, 45),
                        intArrayOf(23, 0, 0, 1, 0, 0)

                )),
                TramStop("Óceán árok", 47.577727, 19.115936, listOf()),
                TramStop("Atlétikai stadion", 47.572173, 19.114139, listOf()),
                TramStop("Újpest - rákos palota vasútállomás", 47.565830, 19.111618, listOf()),
                TramStop("Szülő otthon", 47.565345, 19.106339, listOf()),
                TramStop("Szakrendelő", 47.565167, 19.100525, listOf()),
                TramStop("Nem tom", 47.565004, 19.093977, listOf())
                //TramStop("Újpest - rákos palota vasútállomás", 47.565830, 19.111618, listOf())
            )

            val kereszt = listOf(
                Crossroads(47.590361, 19.116744),
                Crossroads(47.576854, 19.115752),
                Crossroads(47.565622, 19.111375)

            )
            val inst = fillSzoveg()
            var traaaaam: TramLine =
                TramLine(14, utvonal, allomasok, kereszt, inst)

            val utvonal2 = listOf(
                doubleArrayOf(47.582102, 19.116901),
                doubleArrayOf(47.574732, 19.115206),
                doubleArrayOf(47.577726, 19.115918),
                doubleArrayOf(47.574732, 19.115206),
                doubleArrayOf(47.572159, 19.114164)
            )
            val allomasok2 = listOf(
                TramStop("Járműtelep", 47.582102, 19.116901, listOf()),
                TramStop("Járműtelep", 47.577726, 19.115918,listOf())
            )

            val kereszt2 = listOf(
                Crossroads(47.590361, 19.116744)
            )

            var traaaaam2: TramLine =
                TramLine(10, utvonal2, allomasok2, kereszt2, inst)


            //Séta kápon:

            val utvonal3 = listOf(
                doubleArrayOf(47.589088, 19.114565), // állomás
                doubleArrayOf(47.590159, 19.116441), // állomás // 47.590159, 19.116441
                doubleArrayOf(47.590358, 19.116747),
                doubleArrayOf(47.590889, 19.117498),
                doubleArrayOf(47.591566, 19.118136),//állomás
                doubleArrayOf(47.591377, 19.119140),//állomás
                doubleArrayOf(47.590151, 19.118667),
                doubleArrayOf(47.589793, 19.118399),
                doubleArrayOf(47.589608, 19.118013)//állomás
            )
            val allomasok3 = listOf(
                //TramStop("Csabi utca", 47.588137, 19.114804, listOf()),
                TramStop("Busz állomás", 47.589088, 19.114565, listOf(
                    intArrayOf(5, 0, 0, 1, 90, 60),
                    intArrayOf(9, 0, 0, 1, 90, 60),
                    intArrayOf(17, 0, 0, 1, 90, 45),
                    intArrayOf(23, 0, 0, 1, 0, 0)
                )),
                TramStop("Suli sarka", 47.590159, 19.116441, listOf()),
                TramStop("Victory Fitness", 47.591566, 19.118136, listOf(
                    intArrayOf(5, 0, 0, 1, 90, 60),
                    intArrayOf(9, 0, 0, 1, 90, 60),
                    intArrayOf(17, 0, 0, 1, 90, 45),
                    intArrayOf(23, 0, 0, 1, 0, 0)
                )),
                TramStop("Zorro", 47.591377, 19.119140, listOf()),
                TramStop("Óvoda és templom", 47.589608, 19.118013, listOf())

            )
            val kereszt3 = listOf(
                Crossroads(47.590361, 19.116744),
                Crossroads(47.588137, 19.114804)
            )

            var seta: TramLine =
                TramLine(9, utvonal3, allomasok3, kereszt3, inst)


            this.trams.add(traaaaam)
            this.trams.add(traaaaam2)
            this.trams.add(seta)
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