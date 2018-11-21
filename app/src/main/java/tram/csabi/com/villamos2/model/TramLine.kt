package tram.csabi.com.villamos2.model

class TramLine(val lineNumber:Short = 0, val lineTrack  : List<DoubleArray>, val lineStops  : List<TramStop>,
                val crossroads: List<Crossroads>, val instruments: HashMap<String,String>){

    //vissza adnak értéket
    fun instrumentDescription(instName: String) = instruments.get(instName)
    fun coordinateList(name : Int):List<Array<String>>{

        var coordinates : MutableList<Array<String>> = mutableListOf()
        when(name){
            0 -> for(i in lineStops){
                coordinates.add(arrayOf(i.lat.toString(),i.lon.toString(), i.name))            }

            2-> for(i in crossroads){
                coordinates.add(arrayOf(i.lat.toString(),i.lon.toString()))
            }
        }
        return coordinates
    }
}