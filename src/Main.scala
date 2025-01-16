object Main {
  def main(args: Array[String]): Unit = {
    var game: Boolean = true

    do {
      /** Creates the playing arena, with a given size */
      var arenaSize: Int = 40
      val arena: Arena = new Arena(arenaSize)
      val startPositions: Array[Array[Int]] = Array(
      Array(arenaSize / 5, arenaSize / 5),
      Array(arenaSize - arenaSize / 5, arenaSize / 5),
      Array(arenaSize / 5, arenaSize - arenaSize / 5),
      Array(arenaSize - arenaSize / 5, arenaSize - arenaSize / 5)
      )

      /** Creates a new FunGraphics display */
      val display = new GameDisplay(arena, 25)
      val mainKeyboard: MenuKeyboardInput = new MenuKeyboardInput(display.a)
      var numberOfPlayers: Int = display.menuScreen(mainKeyboard)

      val players: Array[Player] = Array.ofDim(numberOfPlayers)
      var gameOver: Boolean = false
      val speed: Int = 150



      /** Creates the players, with their start position */
      for (i <- 0 until numberOfPlayers) {
        players(i) = new Player(i + 1, startPositions(i)(0), startPositions(i)(1), arena.gridSizeX, new KeyboardInput(display.a, players, i + 1))
      }

      /** Sets the first captured cells of each player */
      for (player <- players) {
        arena.grid(player.startPos(0))(player.startPos(1)) = player.playerID.toString
      }

      /** Menu appears, followed by the launch screen */
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
      Thread.sleep(2000)

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