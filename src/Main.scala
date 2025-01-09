import BISC.{Arena, Player}

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val arena: Arena = new Arena(20, 20)
    val player1: Player = new Player(1, 10, 5)
    val player2: Player = new Player(2, 12, 9)

    do {
      arena.setCurrentPos(player1.currentPos, player1.playerID)

      arena.displayGrid()
      //println(s"Player ${player1.playerID} : ${player1.getScore(arena.grid)} pts")

      player1.playerMove(arena.grid)
      arena.setTemp(player1.lastPos, player1.playerID)

      arena.floodFill(10, 10, player1.playerID)
      // arena.displayGrid()
      // var a = Input.readString()

    } while (!player1.gameOver)
  }
}