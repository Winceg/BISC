import BISC.{Arena, Player}

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val arena: Arena = new Arena(20, 20)
    val player1: Player = new Player(1, 4, 2)
    val player2: Player = new Player(2, 12, 9)

    do {
      arena.setCurrentPos(player1.currentPos, player1.playerID)
      arena.setCurrentPos(player2.currentPos, player2.playerID)

      arena.displayGrid()
      println(s"Player ${player1.playerID} : ${player1.getScore(arena.grid)} pts")
      println(s"Player ${player2.playerID} : ${player2.getScore(arena.grid)} pts")

      player1.playerMove(arena.grid)

      arena.displayGrid()
      println(s"Player ${player1.playerID} : ${player1.getScore(arena.grid)} pts")
      println(s"Player ${player2.playerID} : ${player2.getScore(arena.grid)} pts")

      player2.playerMove(arena.grid)
    }while(!player1.gameOver)
  }
}