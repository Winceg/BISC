import hevs.graphics.FunGraphics

class GameDisplay (val arena : Arena,val sizeFactor : Int){
  val sizeFactor : Int
  val graphikGridX : Int = arena.gridSizeX*sizeFactor
  val graphikGridY : Int = (arena.gridSizeY+2)*sizeFactor

  val a = new FunGraphics(graphikGridX, graphikGridY, "BISC")
  for(i<- arena.grid.indices){
    for(j<- arena.grid(i).indices){

    }
  }

}
