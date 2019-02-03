package tram.csabi.com.villamos2.Model

data class TramLine(val lineNumber:String, val lineTrack  : List<Coordinate>, val lineStops  : List<TramStop>,
                val crossroads: List<Crossroads>, val instruments: HashMap<String,String>){

    //vissza adnak értéket
    fun instrumentDescription(instName: String) = instruments.get(instName)
    fun coordinateList(name : Int):List<Array<String>>{

        var coordinates : MutableList<Array<String>> = mutableListOf()
        when(name){
            0 -> for(i in lineStops){
                coordinates.add(arrayOf(i.lat.toString(),i.lon.toString(), i.name))            }

            1-> for(i in crossroads){
                coordinates.add(arrayOf(i.lat.toString(),i.lon.toString(), i.name ))
            }
        }
        return coordinates
    }
}