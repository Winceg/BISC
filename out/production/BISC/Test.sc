var cell: String = "t2"
val player: String = "1"
val temp: String = "t" + player
var result: Int = 0

cell match {
  case s if s == player => result = 1
  case s"t$player" => result = 2
  case _ => result = 3
}
result

class Player(val playerID: Int, startPosX: Int, startPosY: Int) {
  val startPos: Array[Int] = Array(startPosX, startPosY)

}

var player: Array[Player] = Array.ofDim(2)
player(0) = new Player(1, 4, 4)
player(1) = new Player(2, 8, 8)

player(0).playerID
player(1).playerID


var losingPlayerID = player.find(p => p.playerID.toString == cell.substring(1)) map { player =>
  player.playerID
}
if(losingPlayerID != None) {
  losingPlayerID.toString.substring(5, 6)
}else "0"

var vlue: String = "2"
vlue.toInt