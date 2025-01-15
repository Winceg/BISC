object Main {
  def main(args: Array[String]): Unit = {
    var game: Boolean = true

    /** Creates the playing arena, with a given size */
    val arena: Arena = new Arena(40)
    val players: Array[Player] = Array.ofDim(2)
    var gameOver: Boolean = false
    val speed: Int = 150

    /** Creates a new FunGraphics display */
    val display = new GameDisplay(arena, 18)

    val mainKeyboard: KeyboardInput = new KeyboardInput(display.a, players, 0)

    /** Creates player1, with his start position */
    players(0) = new Player(1, 4, 4, arena.gridSizeX, new KeyboardInput(display.a, players, 1))

    /** Creates player2, with his start position */
    players(1) = new Player(2, 24, 24, arena.gridSizeX, new KeyboardInput(display.a, players, 2))

    do {
      /** Sets the first captured cells of each player */
      arena.grid(players(0).startPos(0))(players(0).startPos(1)) = players(0).playerID.toString
      arena.grid(players(1).startPos(0))(players(1).startPos(1)) = players(1).playerID.toString

      /** Menu appears, followed by the launch screen */
      display.menuScreen(mainKeyboard)
      display.launchingScreen()

      do {
        for (player <- players) {
          /** Displays the grid in the console */
          display.gamePaintClock(players, arena)

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

      var winner: String = ""
      for (player <- players) {
        if (!player.gameOver) winner = player.playerID.toString
      }
      display.gameOverScreen(mainKeyboard, winner, players, arena)

      /** Resetting all game parameters */
      arena.resetGrid()
      for (player <- players) {
        player.reset()
      }
      gameOver = false

      /** Exit the game */
      game = if (mainKeyboard.getReturnString() == "esc") false else true

    } while (game)
  }
}