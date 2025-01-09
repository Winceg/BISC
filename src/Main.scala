import BISC.{Arena, Player}

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val arena: Arena = new Arena(10, 10)
    val player1: Player = new Player(1)

    do {
      arena.setCurrentPos(player1.currentPos, player1.playerID)
      arena.displayGrid()
      player1.playerMove(arena.grid)
    }while(!player1.gameOver)
  }
}