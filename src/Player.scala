class Player(val playerID: Int, startPosX: Int, startPosY: Int, arenaWidth: Int, val keyboard: KeyboardInput) {

  /** CONTENT : player reset, start direction, player movements, get player score */

  /** Class defining the player attributes and functions */
  val startPos: Array[Int] = Array(startPosX + 2, startPosY + 2)
  var currentPos: Array[Int] = startPos.clone()
  var lastPos: Array[Int] = currentPos.clone()
  var direction: String = startDirection(arenaWidth)
  var lastDirection: String = direction
  var score: Int = 0
  var gameOver: Boolean = false

  def reset(): Unit = {
    this.currentPos = startPos.clone()
    this.lastPos = currentPos.clone()
    this.score = 0
    this.direction = startDirection(arenaWidth)
    this.lastDirection = this.direction
    this.gameOver = false
    this.keyboard.setReturnString(this.direction)
  }

  /** Defines the starting direction based on the start position of a player (i.e. if player starts in the left half of the arena, his start direction is right)  */
  def startDirection(arenaWidth: Int): String = {
    if (startPosX < (arenaWidth / 2)) "r" + this.playerID else "l" + this.playerID
  }

  /** Gets user input and sets new current position */
  def playerMove(grid: Array[Array[String]]): Unit = {
    this.lastPos = Array(this.currentPos: _*)
    var inputKey: String = ""

    if (this.direction.substring(1) == this.playerID.toString) {
      inputKey = this.direction.substring(0, 1)

      /** Matches keyboard input with direction, checks and ignores if opposite direction is pressed */
      inputKey match {
        case "u" => if (this.lastDirection.substring(0, 1) == "d") {
          inputKey = "d"
          this.direction = this.lastDirection
        } else {
          inputKey = "u"
        }
        case "d" => if (this.lastDirection.substring(0, 1) == "u") {
          inputKey = "u"
          this.direction = this.lastDirection
        } else {
          inputKey = "d"
        }
        case "l" => if (this.lastDirection.substring(0, 1) == "r") {
          inputKey = "r"
          this.direction = this.lastDirection
        } else {
          inputKey = "l"
        }
        case "r" => if (this.lastDirection.substring(0, 1) == "l") {
          inputKey = "l"
          this.direction = this.lastDirection
        } else {
          inputKey = "r"
        }
        case _ => inputKey = this.lastDirection
      }
    }

    /** Matches player input to movements in the grid
     * Also ends the game if the player hits the wall (next position is outside the playing surface */
    inputKey match {
      case "r" =>
        if ((this.currentPos(1) + 1) < grid.length - 2) {
          this.currentPos(1) = this.currentPos(1) + 1
        } else {
          this.gameOver = true
        }
      case "l" =>
        if ((this.currentPos(1) - 1) >= 2) {
          this.currentPos(1) = this.currentPos(1) - 1
        } else {
          this.gameOver = true
        }
      case "d" =>
        if ((this.currentPos(0) + 1) < grid.length - 2) {
          this.currentPos(0) = this.currentPos(0) + 1
        } else {
          this.gameOver = true
        }
      case "u" =>
        if ((this.currentPos(0) - 1) >= 2) {
          this.currentPos(0) = this.currentPos(0) - 1
        } else {
          this.gameOver = true
        }
      case _ =>
        println("Saisie non valide")
    }
  }

  /** Counts the number of captured cells and returns the player's score */
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
