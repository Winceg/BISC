class Player(val playerID: Int, startPosX: Int, startPosY: Int, arenaWidth: Int, val keyboard: KeyboardInput) {

  /** CONTENT : player movements + player score */


  /** Class defining the player attributes and functions */
  val startPos: Array[Int] = Array(startPosX + 2, startPosY + 2)
  var currentPos: Array[Int] = startPos.clone()
  var lastPos: Array[Int] = currentPos.clone()
  var direction: String = startDirection(arenaWidth)
  var lastDirection: String = direction
  var score: Int = 0
  var gameOver: Boolean = false

  /** Defines the starting direction based on the start position of a player */
  def startDirection(arenaWidth: Int): String = {
    if (startPosX < (arenaWidth / 2).toInt) "r" else "l"
  }

  /** Gets user input and sets new current position */
  def playerMove(grid: Array[Array[String]], input: String): Unit = {
    this.lastPos = Array(this.currentPos: _*)
    //    Console controls :
    //    println(s"Player ${this.playerID}, enter direction :")
    //    val input: String = Input.readString()
    var inputKey: String = ""
    if(input.substring(1) == this.playerID.toString) {
      inputKey = input.substring(0, 1)
    }else{
      inputKey = lastDirection
    }
    /*
    Idée :
    input.substring match
    thisplayerid => inputKey = input.substring(0, 1)
    up && lastdirection == down => inputkey = down
    down && lastdirection == up => inputkey = up
    right && lastdirection == left => inputkey = left
    left && lastdirection == right => inputkey = right

    * */

    println(s"InputKey : $inputKey")

    /** Matches player input to movements in the grid
     * Also ends the game if the player hits the wall (next position is outside the playing surface */
    inputKey match {
      case "r" =>
        if ((this.currentPos(1) + 1) < grid.length - 2) {
          this.currentPos(1) = this.currentPos(1) + 1
        } else {
          this.gameOver = true
          println("You've hit the wall !")
        }
      case "l" =>
        if ((this.currentPos(1) - 1) >= 2) {
          this.currentPos(1) = this.currentPos(1) - 1
        } else {
          this.gameOver = true
          println(s"Player${this.playerID}, you've hit the wall !")
        }
      case "d" =>
        if ((this.currentPos(0) + 1) < grid.length - 2) {
          this.currentPos(0) = this.currentPos(0) + 1
        } else {
          this.gameOver = true
          println("You've hit the wall !")
        }
      case "u" =>
        if ((this.currentPos(0) - 1) >= 2) {
          this.currentPos(0) = this.currentPos(0) - 1
        } else {
          this.gameOver = true
          println("You've hit the wall !")
        }
      case _ =>
        println("Saisie non valide")
    }
  }

  /** Counts the number of captured cells */
  def getScore(grid: Array[Array[String]]): Int = {
    this.score = 0
    for (i <- grid.indices) {
      for (j <- grid(i).indices) {
        if (grid(i)(j) == this.playerID.toString) {
          this.score += 1
        }
      }
    }
    this.score
  }

}
