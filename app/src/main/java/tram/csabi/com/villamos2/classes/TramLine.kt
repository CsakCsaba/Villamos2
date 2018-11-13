package tram.csabi.com.villamos2.classes

class TramLine(val lineNumber:Short = 0, val lineTrack  : List<DoubleArray>, val lineStops  : List<TramStop>,
                val crossroads: List<Crossroads>, val instruments: HashMap<String,String>){

    //vissza adnak értéket
    fun instrumentDescription(instName: String) = instruments.get(instName)
    fun coordinateList(name : Int):List<DoubleArray>{

        var coordinates : MutableList<DoubleArray> = mutableListOf()
        when(name){
            0 -> for(i in lineStops){
                coordinates.add(doubleArrayOf(i.lat,i.lon))            }

            2-> for(i in crossroads){
                coordinates.add(doubleArrayOf(i.lat,i.lon))
            }
        }
        return coordinates
    }
}