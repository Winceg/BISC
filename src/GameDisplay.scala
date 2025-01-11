import hevs.graphics.FunGraphics

import java.awt.Color

class GameDisplay (val arena : TestingArena,val sizeFactor : Int){

  //fonction to kill the usless border and create a new array
  val realSizeX : Int = arena.gridSizeX
  val realSizeY : Int = arena.gridSizeY
  var arenaGraphik : Array[Array[String]] = Array.ofDim(realSizeX,realSizeY)
  for (i<- arenaGraphik.indices){
    for(j <- arenaGraphik(i).indices){
      arenaGraphik(i)(j) = arena.grid(i+1)(j+1)
    }
  }

  // Method to control what we have done in the new array
  def displayCroppedGrid(): Unit = {
    for (i <- arenaGraphik.indices) {
      for (j <- arenaGraphik(i).indices) {
        print(s" ${arenaGraphik(i)(j)} ")
      }
      println()
    }
  }
  //Create different color used in the game
  val cEmpty = new Color(255,255,255)
  val cBorders = new Color(0,0,0)
  val cCapPlayer1 = new Color(0,180,0)
  val cTempPlayer1 = new Color(100,180,100)
  val cHeadPlayer1 = new Color(0,255,0)
  val cCapPlayer2 = new Color (180,0,0)
  val cTempPlayer2 = new Color (180,100,100)
  val cHeadPlayer2 = new Color (255,0,0)

  //create a graphic pixel version with a size factor
  val graphikGridX : Int = realSizeX*sizeFactor
  val graphikGridY : Int = (realSizeY+1)*sizeFactor //content the space for score and more
  val a = new FunGraphics(graphikGridX, graphikGridY, "BISC")

  // paint the borders of the grid in black
  for(i<-0 to graphikGridX-1){
    for(j<-0 to graphikGridY-sizeFactor-1){
      if (i == 0||j == 0 || i%sizeFactor == 0 || j%sizeFactor==0 || (i+1)%sizeFactor==0 || (j+1)%sizeFactor==0){
        a.setPixel(i,j,cBorders)
      }
      else{a.setPixel(i,j,cEmpty)}
    }
  }
  //Add a menu feature or something in the bottom of the game

  //Method to check every cell and paint it in the right color
  def gamePaintClock(): Unit = {
    var colorToPaint : Color = cEmpty
    // first loop to see what's in the array and set the color we want to paint
    for(i<-arenaGraphik.indices){
      for(j<-arenaGraphik(i).indices){
        arenaGraphik(i)(j) match {
          case "*"   => colorToPaint = cEmpty
          case "-"   => colorToPaint = cBorders
          case "1"   => colorToPaint = cCapPlayer1
          case "t1"  => colorToPaint = cTempPlayer1
          case "x1"  => colorToPaint = cHeadPlayer1
          case "2"   => colorToPaint = cCapPlayer2
          case "t2"  => colorToPaint = cTempPlayer2
          case "x2"  => colorToPaint = cHeadPlayer2
        }
        // second loop to color each pixel on the graphic right spot
        for(k<- 1 to sizeFactor-2){
          for(l<- 1 to sizeFactor-2){
            a.setPixel(j*sizeFactor+k,i*sizeFactor+l,colorToPaint)
          }
        }
      }
    }
  }
}
