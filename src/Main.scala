
/*import BISC.{Arena, Player}*/

object Main {
  def main(args: Array[String]): Unit = {
    /** Creates the playing arena, with a given size */
    val numberOfPlayers: Int = 2
    val arena: Arena = new Arena(20, 20)
    val players: Array[Player] = Array.ofDim(numberOfPlayers)
    var gameOver: Boolean = false

    /** Creates the players, with their start position */
    players(0) = new Player(1, 4, 4)
    players(1) = new Player(2, 8, 6)

    println(s"Number of players : ${players.length}")

    arena.grid(6)(6) = "1"
    arena.grid(10)(8) = "2"

    do {
      /** Displays the grid in the console */
      arena.displayGrid()

      for (player <- players) {
        /** Displays the player's score */
        println(s"Player ${player.playerID} : ${player.getScore(arena.grid)} pts")

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

    } while (!gameOver)

    println("Game over !")
    for (player <- players) {
      if (!player.gameOver) println(s"Player${player.playerID} wins !")
    }
  }
}