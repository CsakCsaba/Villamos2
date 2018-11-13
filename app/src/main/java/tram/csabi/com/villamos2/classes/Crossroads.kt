package tram.csabi.com.villamos2.classes
import android.os.Build
import android.support.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class Crossroads (val lat: Double, val lon : Double) {

    @RequiresApi(Build.VERSION_CODES.O)
    var lastChecked = LocalDateTime.of(1,1,1,1,1,1)
    open var metersCheck = 100

    @RequiresApi(Build.VERSION_CODES.O)
    open fun Warning (lat : Double, lon : Double) : Boolean{

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss")
        val formatterString = current.format(formatter).toString().split(':')


        var most = LocalDateTime.now()
        if ((most.compareTo(lastChecked)>0 &&  most.minute.minus(lastChecked.minute) >= 2)&& (distanceInM(this.lat,
                this.lon,lat,lon)<=metersCheck))
        {
            lastChecked = most
            return true
        }
        return  false
    }
    private fun degreeToRadians(degrees:Double):Double = degrees*Math.PI /180

    private fun distanceInM(lat1: Double, lon1:Double, lat2:Double, lon2:Double): Double{

        var earthRadiusM = 6371000
        var dLat= degreeToRadians(lat2-lat1)
        var dLon=degreeToRadians(lon2-lon1)

        val lat12 = degreeToRadians(lat1)
        val lat22 = degreeToRadians(lat2)

        var a = Math.sin(dLat/2)*Math.sin(dLat/2)+
                Math.sin(dLon/2)*Math.sin(dLon/2)* Math.cos(lat12) * Math.cos(lat22)
        var c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a))

        return earthRadiusM*c
    }

}