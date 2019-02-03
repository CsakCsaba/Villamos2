package tram.csabi.com.villamos2.Model

class TramStop(val name:String,  val lat: Double ,val lon : Double, cycles : List<IntArray>) {
    val lamp:Lamp = if(cycles.isNotEmpty()) Lamp(cycles,lat,lon) else Lamp(null, lat,lon)
}