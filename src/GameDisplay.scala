import hevs.graphics.FunGraphics

import java.awt.Color

class GameDisplay (val arena : Arena,val sizeFactor : Int){

  //fonction to kill the usless border
  val realSizeX : Int = arena.gridSizeX - 2
  val realSizeY : Int = arena.gridSizeY -2
  var arenaGraphik : Array[Array[String]] = Array.ofDim(realSizeX,realSizeY)
  for (i<- arenaGraphik.indices){
    for(j <- arenaGraphik(i).indices){
      arenaGraphik(i)(j) = arena.grid(i+1)(j+1)
    }
  }
  //Creat different color used in the game
  val cBorders = new Color(0,0,0)
  val cCapPlayer1 = new Color(0,180,0)
  val cTempPlayer1 = new Color(100,180,100)
  val cHeadPlayer1 = new Color(0,255,0)
  val cCapPlayer2 = new Color (180,0,0)
  val cTempPlayer2 = new Color (180,100,100)
  val cHeadPlayer2 = new Color (255,0,0)

  //create a graphic pixel version with a size factor
  val graphikGridX : Int = realSizeX*sizeFactor
  val graphikGridY : Int = (realSizeY+2)*sizeFactor
  val a = new FunGraphics(graphikGridX, graphikGridY, "BISC")

  // paint the borders of the grid in black
  for(i<-0 to graphikGridX){
    for(j<-0 to graphikGridY-(2*sizeFactor)){
      if (i == 0||j == 0 || i%sizeFactor > 0 || j%sizeFactor>0 || (i+1)%sizeFactor >0 || (j+1)%sizeFactor >0){
        a.setPixel(i,j,cBorders)
      }
    }
  }

  //check every cell and paint it in the right color

}
