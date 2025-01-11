/*import BISC.{Arena, Player}*/

object Main {
  def main(args: Array[String]): Unit = {

    /** Creates the playing arena, with a given size */
    val arena: Arena = new Arena(20, 20)

    /** Creates the players, with their start position */
    val player1: Player = new Player(1, 4, 4)
    val player2: Player = new Player(2, 16, 16)

    do {
      /** Sets the current position of the player */
      arena.setCurrentPos(player1.currentPos, player1.playerID)

      /** Displays the grid in the console */
      arena.displayGrid()

      /** Displays the player's score */
      println(s"Player ${player1.playerID} : ${player1.getScore(arena.grid)} pts")

      /** Asks the player for the next step, and updates the current position */
      player1.playerMove(arena.grid)
      /** Turns the last position into a "t" plus the player's ID */
      arena.setTemp(player1.lastPos, player1.playerID)


      /** Flood fills all cells that are outside the perimeter of the player's surface */
      //arena.floodFill(20, 1, player1.playerID)
      arena.action(player1.currentPos, player1.playerID.toString)
      // arena.displayGrid()
      // var a = Input.readString()

    } while (!player1.gameOver)

    println("Game over !")
  }
}