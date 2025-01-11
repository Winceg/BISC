import hevs.graphics.FunGraphics

class GameDisplay (val arena : Arena,val sizeFactor : Int){

  //fonction to kill the usless border
  val realSizeX : Int = arena.gridSizeX - 2
  val realSizeY : Int ) arena.gridSizeY -2
  var arenaGraphik : Array[Array[String]] = Array.ofDim(realSizeX,realSizeY)
  for (i<- arenaGraphik.indices){
    for(j <- arenaGraphik(i).indices){
      arenaGraphik(i)(j) = arena.grid(i+1)(j+1)
    }
  }
  val graphikGridX : Int = arena.gridSizeX*sizeFactor
  val graphikGridY : Int = (arena.gridSizeY+2)*sizeFactor

  val a = new FunGraphics(graphikGridX, graphikGridY, "BISC")
  for(i<- arena.grid.indices){
    for(j<- arena.grid(i).indices){


    }
  }
}
