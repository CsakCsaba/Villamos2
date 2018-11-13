package tram.csabi.com.villamos2.classes

class TramStop(val name:String,  val lat: Double ,val lon : Double, ciklusok : List<IntArray>) {
    val lamp:Lamp = if(ciklusok.size!=0) Lamp(ciklusok,lat,lon) else Lamp(null, lat,lon)
}