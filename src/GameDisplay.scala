import hevs.graphics.FunGraphics

import java.awt.{Color, Font}

class GameDisplay(val arena: Arena, val sizeFactor: Int) {
  // test commit push
  //fonction to kill the usless border and create a new array
  val realSizeX: Int = arena.gridSizeX - 4
  val realSizeY: Int = arena.gridSizeY - 4
  var arenaGraphik: Array[Array[String]] = Array.ofDim(realSizeX, realSizeY)
  for (i <- arenaGraphik.indices) {
    for (j <- arenaGraphik(i).indices) {
      arenaGraphik(i)(j) = arena.grid(i + 2)(j + 2)
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
  val cEmpty = new Color(255, 255, 255)
  val cBorders = new Color(0, 0, 0)
  val cCapPlayer1 = new Color(0, 180, 0)
  val cTempPlayer1 = new Color(100, 180, 100)
  val cHeadPlayer1 = new Color(0, 255, 0)
  val cCapPlayer2 = new Color(180, 0, 0)
  val cTempPlayer2 = new Color(180, 100, 100)
  val cHeadPlayer2 = new Color(255, 0, 0)
  val cCounter = new Color(20,100,255)
  //Create different fonts used in the game
  val gameTitle: Font = new Font("Georgia", Font.BOLD, 50)
  val title: Font = new Font("Georgia", Font.BOLD, 20)
  val subtitle: Font = new Font("Georgia", Font.BOLD, 15)
  val scoreTitle: Font = new Font("Georgia", Font.BOLD, 10)
  //create a graphic pixel version with a size factor
  val graphikGridX: Int = realSizeX * sizeFactor
  val graphikGridY: Int = (realSizeY + 1) * sizeFactor //content the space for score and more
  val a = new FunGraphics(graphikGridX, graphikGridY, "BISC")


  //Method to check every cell and paint it in the right color
  def gamePaintClock(players : Array[Player]): Unit = {
    var colorToPaint: Color = cEmpty
    var i: Int = 2
    for(player <- players) {
      a.drawString(graphikGridX + sizeFactor * i, graphikGridY - 20, s"Score P${player.playerID} : ${player.score}", scoreTitle, cCapPlayer1)
      i += 5
    }

    /** Refreshing information from the data array */
    for (i <- arenaGraphik.indices) {
      for (j <- arenaGraphik(i).indices) {
        arenaGraphik(i)(j) = arena.grid(i + 2)(j + 2)
      }
    }

    // paint the borders of the grid in black
    for (i <- 0 to graphikGridX - 1) {
      for (j <- 0 to graphikGridY - sizeFactor - 1) {
        if (i == 0 || j == 0 || i % sizeFactor == 0 || j % sizeFactor == 0 || (i + 1) % sizeFactor == 0 || (j + 1) % sizeFactor == 0) {
          a.setPixel(i, j, cBorders)
        }
        else {
          a.setPixel(i, j, cEmpty)
        }
      }
    }

    // first loop to see what's in the array and set the color we want to paint
    for (i <- arenaGraphik.indices) {
      for (j <- arenaGraphik(i).indices) {
        arenaGraphik(i)(j) match {
          case "*" => colorToPaint = cEmpty
          case "-" => colorToPaint = cBorders
          case "1" => colorToPaint = cCapPlayer1
          case "t1" => colorToPaint = cTempPlayer1
          case "x1" => colorToPaint = cHeadPlayer1
          case "2" => colorToPaint = cCapPlayer2
          case "t2" => colorToPaint = cTempPlayer2
          case "x2" => colorToPaint = cHeadPlayer2
        }
        // second loop to color each pixel on the graphic right spot
        for (k <- 1 to sizeFactor - 2) {
          for (l <- 1 to sizeFactor - 2) {
            a.setPixel(j * sizeFactor + k, i * sizeFactor + l, colorToPaint)
          }
        }
      }
    }
  }

  def menuScreen(keyboard: KeyboardInput): Unit = {
    println("menu")
    var input : String = ""
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-60, graphikGridY/2-100,"BISC",gameTitle,cBorders)
    a.drawString(graphikGridX/2-30, graphikGridY/2-20,"MENU",title,cBorders)
    a.drawString(graphikGridX/2-100, graphikGridY/2+200,"Press enter to Start a Game",subtitle,cBorders)
    do {
      input = keyboard.getReturnString()
      Thread.sleep(100)
    } while (input != "enter")
    keyboard.setReturnString("")
    a.clear(cEmpty)
  }

  def launchingScreen(): Unit = {
    println("Launching")
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-20, graphikGridY/2-10,"3",gameTitle, cCounter)
    Thread.sleep(1000)
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-20, graphikGridY/2-10,"2",gameTitle,cCounter)
    Thread.sleep(1000)
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-20, graphikGridY/2-10,"1",gameTitle,cCounter)
    Thread.sleep(1000)
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-40, graphikGridY/2-10,"GO",gameTitle,cHeadPlayer1)
    Thread.sleep(1000)
    a.clear(cEmpty)

  }

  def gameOverScreen(keyboard: KeyboardInput, winnerPlayer: String): Unit = {
    var input : String = ""
    println("GameOver")
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-160, graphikGridY/2-100,"GAME OVER",gameTitle, cHeadPlayer2)
    a.drawString(graphikGridX/2-30, graphikGridY/2+150,s"Player $winnerPlayer win",subtitle,cBorders)
    a.drawString(graphikGridX/2-140, graphikGridY/2+200,"Press enter to go in the Menu",title,cBorders)
    do {
      input = keyboard.getReturnString()
      Thread.sleep(100)
    } while (input != "enter")
    keyboard.setReturnString("")
    a.clear(cEmpty)
  }

  def pauseScreen (keyboard: KeyboardInput): Unit = {
    var input : String = ""
    println("Pause")
    a.clear(cEmpty)
    a.drawString(graphikGridX/2-85, graphikGridY/2-100,"PAUSE",gameTitle, cBorders)
    a.drawString(graphikGridX/2-100, graphikGridY/2+200,"Press enter to resume",title,cBorders)
    do {
      input = keyboard.getReturnString()
      Thread.sleep(100)
    } while (input != "enter")
    keyboard.setReturnString("")
    a.clear(cEmpty)
  }
}
