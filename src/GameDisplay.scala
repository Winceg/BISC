import hevs.graphics.FunGraphics

import java.awt.{Color, Font, Rectangle}

class GameDisplay(val arena: Arena, val sizeFactor: Int) {
  /** Creates a new array without the borders */
  val realSizeX: Int = arena.gridSizeX - 4
  val realSizeY: Int = arena.gridSizeY - 4
  var arenaGraphik: Array[Array[String]] = Array.ofDim(realSizeX, realSizeY)
  for (i <- arenaGraphik.indices) {
    for (j <- arenaGraphik(i).indices) {
      arenaGraphik(i)(j) = arena.grid(i + 2)(j + 2)
    }
  }

  /** Creates the different colors used in the game */
  val cEmpty = new Color(255, 255, 255)
  val cTransparentBackground = new Color(255, 255, 255, 150)
  val cBorders = new Color(0, 0, 0)
  val cCapPlayer1 = new Color(0, 180, 0)
  val cTempPlayer1 = new Color(100, 180, 100)
  val cHeadPlayer1 = new Color(0, 255, 0)
  val cCapPlayer2 = new Color(180, 0, 0)
  val cTempPlayer2 = new Color(180, 100, 100)
  val cHeadPlayer2 = new Color(255, 0, 0)
  val cCapPlayer3 = new Color(0, 102, 220)
  val cTempPlayer3 = new Color(102, 178, 255)
  val cHeadPlayer3 = new Color(0, 128, 255)
  val cCapPlayer4 = new Color(210, 210, 0)
  val cTempPlayer4 = new Color(255, 255, 102)
  val cHeadPlayer4 = new Color(255, 255, 0)
  val cCounter = new Color(20, 100, 255)
  val playerColors: Array[Color] = Array(cHeadPlayer1, cHeadPlayer2, cHeadPlayer3, cHeadPlayer4)
  //Create different fonts used in the game
  val gameTitle: Font = new Font("Verdana", Font.BOLD, 50)
  val title: Font = new Font("Verdana", Font.BOLD, 20)
  val subtitle: Font = new Font("Verdana", Font.BOLD, 15)
  val scoreTitle: Font = new Font("Verdana", Font.BOLD, 16)
  val scoreTitleBold: Font = new Font("Verdana", Font.BOLD, 16)
  //create a graphic pixel version with a size factor
  val graphikGridX: Int = realSizeX * sizeFactor
  val graphikGridY: Int = realSizeY * sizeFactor //content the space for score and more
  val a = new FunGraphics(graphikGridX, graphikGridY + 50, "BISC")


  /** Method to check every cell and paint it in the right color */
  def gamePaintClock(players: Array[Player], arena: Arena): Unit = {
    var colorToPaint: Color = cEmpty
    var i: Int = 0
    val offset: Int = 200
    val boxWidth: Int = 150
    val startBox: Int = graphikGridX / 2 - (60 * players.length + (offset - boxWidth) / 2 * (players.length - 1))
    for (player <- players) {
      a.setColor(new Color(125, 125, 125))
      a.drawFillRect(startBox + 4 + i * offset, graphikGridY + 4, boxWidth, 40)
      a.setColor(new Color(200, 200, 200))
      a.drawFillRect(startBox + i * offset, graphikGridY, boxWidth, 40)
      a.setColor(cBorders)
      a.setPenWidth(3)
      a.drawRect(startBox + i * offset, graphikGridY, boxWidth, 40)
      a.setPenWidth(1)
      a.drawString(startBox + 8 + i * offset, graphikGridY + 27, s"Score P${player.playerID} : ${player.getScore(arena.grid)}", scoreTitleBold, cBorders)
      a.drawString(startBox + 7 + i * offset, graphikGridY + 26, s"Score P${player.playerID} : ${player.getScore(arena.grid)}", scoreTitle, playerColors(i))
      i += 1
    }

    /** Refreshing information from the data array */
    for (i <- arenaGraphik.indices) {
      for (j <- arenaGraphik(i).indices) {
        arenaGraphik(i)(j) = arena.grid(i + 2)(j + 2)
      }
    }

    /** Paints the borders of the grid in black */
    for (i <- 0 until graphikGridX) {
      for (j <- 0 until graphikGridY - sizeFactor) {
        if (i == 0 || j == 0 || i % sizeFactor == 0 || j % sizeFactor == 0 || (i + 1) % sizeFactor == 0 || (j + 1) % sizeFactor == 0) {
          a.setPixel(i, j, cBorders)
        }
        else {
          a.setPixel(i, j, cEmpty)
        }
      }
    }

    /** First loop to see what's in the array and set the color we want to paint */
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
          case "3" => colorToPaint = cCapPlayer3
          case "t3" => colorToPaint = cTempPlayer3
          case "x3" => colorToPaint = cHeadPlayer3
          case "4" => colorToPaint = cCapPlayer4
          case "t4" => colorToPaint = cTempPlayer4
          case "x4" => colorToPaint = cHeadPlayer4
        }

        /** Second loop to color each pixel on the graphic right spot */
        for (k <- 1 to sizeFactor - 2) {
          for (l <- 1 to sizeFactor - 2) {
            a.setPixel(j * sizeFactor + k, i * sizeFactor + l, colorToPaint)
          }
        }
      }
    }
  }

  /** Displays the start menu and waits for the user to press "enter" */
  def menuScreen(keyboard: MenuKeyboardInput): Int = {
    var output: Int = 1
    var input: String = ""
    a.clear(cEmpty)
    a.setColor(cBorders)
    a.drawFillRect(graphikGridX / 4 + 10, graphikGridY / 4 + 10, graphikGridX / 2, graphikGridY / 2)
    a.setColor(cTransparentBackground)
    a.drawFillRect(0, 0, graphikGridX, graphikGridY)
    a.setColor(cEmpty)
    a.drawFillRect(graphikGridX / 4, graphikGridY / 4, graphikGridX / 2, graphikGridY / 2)
    a.setColor(cBorders)
    a.setPenWidth(4)
    a.drawRect(graphikGridX / 4, graphikGridY / 4, graphikGridX / 2, graphikGridY / 2)
    a.drawString(graphikGridX / 2 - 68, graphikGridY / 2 - 98, "BISC", gameTitle, cBorders)
    a.drawString(graphikGridX / 2 - 70, graphikGridY / 2 - 100, "BISC", gameTitle, cHeadPlayer3)
    a.drawString(graphikGridX / 2 - 35, graphikGridY / 2 - 20, "MENU", title, cBorders)
    a.drawString(graphikGridX / 2 - 100, graphikGridY / 2 + 30, "For 1 player, hit numpad1", subtitle, cBorders)
    a.drawString(graphikGridX / 2 - 100, graphikGridY / 2 + 50, "For 2 player, hit numpad2", subtitle, cBorders)
    a.drawString(graphikGridX / 2 - 100, graphikGridY / 2 + 70, "For 3 player, hit numpad3", subtitle, cBorders)
    a.drawString(graphikGridX / 2 - 100, graphikGridY / 2 + 90, "For 4 player, hit numpad4", subtitle, cBorders)
    a.drawString(graphikGridX / 2 - 25, graphikGridY / 2 + 130, "Then", subtitle, cBorders)
    a.drawString(graphikGridX / 2 - 100, graphikGridY / 2 + 160, "Press enter to Start a Game", subtitle, cBorders)

    /** Waits until the user presses the "enter" key */
    do {
      input = keyboard.getReturnString()
      a.setPenWidth(1)
      input match {
        case "1" =>
          output = 1
          a.setColor(cHeadPlayer1)
          a.drawFilledCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 23, 4)
          a.setColor(cBorders)
          a.drawCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 23, 5)
        case "2" =>
          output = 2
          a.setColor(cHeadPlayer2)
          a.drawFilledCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 43, 4)
          a.setColor(cBorders)
          a.drawCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 43, 5)
        case "3" =>
          output = 3
          a.setColor(cHeadPlayer3)
          a.drawFilledCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 63, 4)
          a.setColor(cBorders)
          a.drawCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 63, 5)
        case "4" =>
          output = 4
          a.setColor(cHeadPlayer4)
          a.drawFilledCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 83, 4)
          a.setColor(cBorders)
          a.drawCircle(graphikGridX / 2 - 108, graphikGridY / 2 + 83, 5)
        case _ =>
      }
      Thread.sleep(100)
    } while (input != "enter")
    keyboard.setReturnString("")
    a.clear(cEmpty)
    output
  }

  /** Displays a countdown before the game starts */
  def launchingScreen(): Unit = {
    val timer: Int = 3
    for (i <- timer to 1 by -1) {
      a.clear(cEmpty)
      a.drawString(graphikGridX / 2 - 20, graphikGridY / 2 - 10, i.toString, gameTitle, cCounter)
      Thread.sleep(1000)
    }
    a.clear(cEmpty)
    a.drawString(graphikGridX / 2 - 40, graphikGridY / 2 - 10, "GO!", gameTitle, cHeadPlayer1)
    Thread.sleep(600)
    a.clear(cEmpty)

  }

  /** Displays the game over screen, with the winner */
  def gameOverScreen(keyboard: MenuKeyboardInput, winnerPlayer: String, players: Array[Player], arena: Arena): Unit = {
    var input: String = ""
    a.setColor(cBorders)
    a.drawFillRect(graphikGridX / 4 + 10, graphikGridY / 4 + 10, graphikGridX / 2, graphikGridY / 2)
    a.setColor(cTransparentBackground)
    a.drawFillRect(0, 0, graphikGridX, graphikGridY)
    a.setColor(cEmpty)
    a.drawFillRect(graphikGridX / 4, graphikGridY / 4, graphikGridX / 2, graphikGridY / 2)
    a.setColor(cBorders)
    a.setPenWidth(4)
    a.drawRect(graphikGridX / 4, graphikGridY / 4, graphikGridX / 2, graphikGridY / 2)
    a.setPenWidth(1)
    a.drawString(graphikGridX / 2 - 168, graphikGridY / 2 - 118, "GAME OVER", gameTitle, cBorders)
    a.drawString(graphikGridX / 2 - 170, graphikGridY / 2 - 120, "GAME OVER", gameTitle, cHeadPlayer2)
    a.drawString(graphikGridX / 2 - 55, graphikGridY / 2, s"Player$winnerPlayer wins!", subtitle, cBorders)
    var i = 0
    for (player <- players) {
      a.drawString(graphikGridX / 2 - 45, graphikGridY / 2 + 25 + i * 25, s"Player${player.playerID} : ${player.getScore(arena.grid)}", subtitle, cBorders)
      i += 1
    }
    a.drawString(graphikGridX / 2 - 125, graphikGridY / 2 + 180, "Press enter to start again", title, cBorders)
    a.drawString(graphikGridX / 2 - 80, graphikGridY / 2 + 205, "Press esc to exit", title, cBorders)

    /** Waits for user input, if enter starts a new game, if esc exits the game */
    do {
      input = keyboard.getReturnString()
      Thread.sleep(100)
      if (input == "esc") System.exit(-1)
    } while (input != "enter")
    keyboard.setReturnString("")
    a.clear(cEmpty)
  }
}
