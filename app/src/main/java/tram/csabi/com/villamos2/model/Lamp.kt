package tram.csabi.com.villamos2.model

import android.os.Build
import android.support.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Lamp(private val lampLoops: List<IntArray>?, lat: Double, lon: Double ) : Crossroads(lat, lon) {

    override var metersCheck = 30
    override var minuteCheck = 2

    fun vanLampa():Boolean= if (lampLoops!=null) true else false

    @RequiresApi(Build.VERSION_CODES.O)
    private fun chooseCurrentCycle(current : LocalDateTime, lista : List<IntArray>) : IntArray {
        var i = 0
        while(i<lista.size && ( current.hour> lista[i][0] || current.hour== lista[i][0] && current.minute> lista[i][1]|| current.hour== lista[i][0] && current.minute== lista[i][1]  && current.second >= lista[i][2])){
            i++
        }
        i--
        if(i==-1)
            i=lista.size-1
        return lista[i]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun timeLeft () : IntArray //green first 0, green second 1
    {
        var elapsedTimeInSeconds = 0

        val current = LocalDateTime.now()


        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatterString = current.format(formatter).toString().split(':')
        val baseTime = chooseCurrentCycle(current,lampLoops!!)

        for (i in formatterString.indices){
            elapsedTimeInSeconds+= ((formatterString[i].toInt() - baseTime[i])*Math.pow(60.0,2.0-i)).toInt()//összes eltelt idő
            //12:00:00 az alap most 13:30:30 van akkor 1*60^2 + 30*50 + 30
        }

        val elapsedTimeInCycle = elapsedTimeInSeconds%baseTime[baseTime.size-2] // összes eltelt idő a cikluson belül

        val nextCycleStartIn = baseTime[baseTime.size-2]-elapsedTimeInCycle

        val nextCycle = chooseCurrentCycle(current.plusSeconds(nextCycleStartIn.toLong()),lampLoops)
        val newTime : LocalDateTime = current.plusSeconds(nextCycleStartIn.toLong())

        // vissza adott tömb elemei: 0.: mennyi idő van hátra a következő lámpa váltásig 1.:

        //0.:első ciklus részből visszamaradt idő
        //1.:második ciklus részből visszamaradt idő
        //2.:teljes ciklus
        //3.: szín
        if(baseTime[baseTime.size-1]>=elapsedTimeInCycle){ // ha a ciklus első felében vagyunk
            return intArrayOf(baseTime[baseTime.size-1] - elapsedTimeInCycle, baseTime[baseTime.size-2]-baseTime[baseTime.size-1],baseTime[baseTime.size-2], baseTime[baseTime.size-3])
        }
        else {
            return intArrayOf(baseTime[baseTime.size-2] - elapsedTimeInCycle,  nextCycle[nextCycle.size-1],nextCycle[nextCycle.size-2] , 1- baseTime[baseTime.size-3]  )
        }

    }



}