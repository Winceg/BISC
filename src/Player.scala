class Player(val playerID: Int, startPosX: Int, startPosY: Int) {

  /** CONTENT : player movements + player score */


  /** Class defining the player attributes and functions */
    val startPos: Array[Int] = Array(startPosX, startPosY)
    var currentPos: Array[Int] = startPos //.clone
    var lastPos: Array[Int] = currentPos //.clone
    var score: Int = 0
    var win: Boolean = false //might be useless
    var gameOver: Boolean = false

    /** Gets user input and sets new current position */
    def playerMove(grid: Array[Array[Int]]): Unit = {
      this.lastPos = Array(this.currentPos: _*)
      println(s"Player ${this.playerID}, enter direction :")
      val input: String = Input.readString()

      input match {
        case "d"
        => if ((this.currentPos(1) + 1) < grid.length) this.currentPos(1) = this.currentPos(1) + 1 else this.gameOver = true
        case "a"
        => if ((this.currentPos(1) - 1) >= 0) this.currentPos(1) = this.currentPos(1) - 1 else this.gameOver = true
        case "s"
        => if ((this.currentPos(0) + 1) < grid.length) this.currentPos(0) = this.currentPos(0) + 1 else this.gameOver = true
        case "w"
        => if ((this.currentPos(0) - 1) >= 0) this.currentPos(0) = this.currentPos(0) - 1 else this.gameOver = true
        case _
        => println("Saisie non valide")
      }
    }

    /** we need to implement setTemp to the last position of the player */

    /** Counts the number of captured cells */
    def getScore(grid: Array[Array[Int]]): Int = {
      this.score = 0
      for (i <- grid.indices) {
        for (j <- grid(i).indices) {
          if (grid(i)(j) == this.playerID) {
            this.score += 1
          }
        }
      }
      this.score
    }
  }
