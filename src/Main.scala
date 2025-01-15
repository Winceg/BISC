
/*import BISC.{Arena, Player}*/

object Main {
  def main(args: Array[String]): Unit = {
    /** Creates the playing arena, with a given size */
    val arena: Arena = new Arena(30)
    var players: Array[Player] = Array.ofDim(2)
    var gameOver: Boolean = false
    var speed: Int = 400

    /** Creates a new fungraphics display */
    val display = new GameDisplay(arena, 18)


    /** Creates player1, with his start position */
    players(0) = new Player(1, 4, 4, arena.gridSizeX, new KeyboardInput(display.a, players, 1))
    arena.grid(players(0).startPos(0))(players(0).startPos(1)) = players(0).playerID.toString

    /** Creates player1, with his start position */
    players(1) = new Player(2, 24, 24, arena.gridSizeX, new KeyboardInput(display.a, players, 2))
    arena.grid(players(1).startPos(0))(players(1).startPos(1)) = players(1).playerID.toString

    val mainKeyboard: KeyboardInput = new KeyboardInput(display.a, players, 0)

    println(s"Number of players : ${players.length}")

    /** MENU APPEAR , press enter now
     * var inputKeyMenu = ""
     * display.menuScreen()
     * do {
     * } while (inputKeyMenu == "")
     */
    /** Menu page appears  */
    display.menuScreen(mainKeyboard)
    display.launchingScreen()

    // ISERT HERE , 3 time launcing screen with a duration of 1 sec each//
    //display.launchingScreen()

    do {
      for (player <- players) {
        /** Displays the grid in the console */
        // Console display :
        // arena.displayCroppedGrid()
        display.gamePaintClock()

        /** Sets the player's direction based on keyboard input */
        player.lastDirection = player.direction
        if (player.keyboard.getReturnString().nonEmpty) player.direction = player.keyboard.getReturnString() else player.direction = player.lastDirection

        /** Asks the player for the next step, and updates the current position */
        player.playerMove(arena.grid)

        /** Determines the action based on the content of the cell */
        arena.action(player.currentPos, player.playerID.toString, players) match {

          /** If the cell is a player's own captured cell, executes the floodFill to validate temporary captured cells */
          case "ff" =>
            arena.floodFill(player.playerID) // Flood fills all cells that are outside the perimeter of the player's surface

            /** Sets the current position of the player */
            arena.setCurrentPos(player.currentPos, player.playerID)

          /** If a player hits his own tail, game over for him */
          case "go" => player.gameOver = true

          /** If a player hits another player's tail, the other player is game over */
          case s if s.startsWith("gop") =>
            players(players.indexWhere { p => p.playerID == s.substring(3).toInt }).gameOver = true

          /** If the cell is empty, sets the current cell to head ("x1") and the last to temp ("t1") */
          case "sp" =>

            /** Sets the current position of the player */
            arena.setCurrentPos(player.currentPos, player.playerID)

            /** Turns the last position into a "t" plus the player's ID */
            arena.setTemp(player.lastPos, player.playerID)
          case _ =>
        }
      }

      /** Checks if one or more players are game over */
      for (player <- players) {
        gameOver = gameOver || player.gameOver
      }
      Thread.sleep(speed)

    } while (!gameOver)
    // insert here game over screen , with jump to menu screen on top of the programm
    display.menuScreen(mainKeyboard)

    println("Game over !")
    for (player <- players) {
      if (!player.gameOver) println(s"Player${player.playerID} wins !")
    }

    var winner: String = ""
    for (player <- players) {
      if (!player.gameOver) winner = player.playerID.toString
    }

  }
}