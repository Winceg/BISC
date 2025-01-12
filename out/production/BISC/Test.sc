var cell: String = "t1"
val player: String = "1"
val temp: String = "t" + player
var result: Int = 0

cell match{
  case s if s == player => result = 1
  case s"t$player" => result = 2
  case _ => result = 3
}
result
class Player(val playerID: Int, startPosX: Int, startPosY: Int) {

  /** CONTENT : player movements + player score */


  /** Class defining the player attributes and functions */
  val startPos: Array[Int] = Array(startPosX, startPosY)
  var currentPos: Array[Int] = startPos.clone()
  var lastPos: Array[Int] = currentPos.clone()
  var score: Int = 0
  var gameOver: Boolean = false
  var win: Boolean = false //might be useless
}

var player: Array[Player] = Array(new Player(1, 4, 4),new Player(2, 8, 8))

cell.substring(1)
player(0).playerID
player(1).playerID
player.find(_ == (2, 8, 8))
/*a.contains(cell.substring(1))*/