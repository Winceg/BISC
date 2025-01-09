object BISC {
  /** Class defining the player attributes and functions */
  class Player(val playerID: Int, startPosX: Int, startPosY: Int) {
    var startPos: Array[Int] = Array(startPosX, startPosY)
    var currentPos: Array[Int] = startPos
    var score: Int = 0
    var win: Boolean = false
    var gameOver: Boolean = false

    /** Gets user input and sets new current position */
    def playerMove(grid: Array[Array[String]]): Unit = {
      println(s"Player ${this.playerID}, enter direction :")
      var input: String = Input.readString()

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

    /** Counts the number of captured cells */
    def getScore(grid: Array[Array[String]]): Int = {
      this.score = 0
      for (i <- grid.indices) {
        for (j <- grid(i).indices) {
          if(grid(i)(j) == this.playerID.toString){
            this.score += 1
          }
        }
      }
      this.score
    }
  }

  /** Class defining the whole arena, and functions to modify the cells on the arena */
  class Arena(val gridSizeX: Int, val gridSizeY: Int) {
    var grid: Array[Array[String]] = Array.fill(gridSizeX, gridSizeY)("*")

    /** Sets current position of player */
    def setCurrentPos(currentPos: Array[Int], playerID: Int): Unit = {
      this.grid(currentPos(0))(currentPos(1)) = playerID.toString
    }

    /*
      /** Sets freshly captured cells to captured */
      def setCatpured(cellType: Int, playerID: Int): Unit = {

        for (i <- this.grid.indices) {
          for (j <- this.grid(i).indices) {
            if (grid(i)(j) == "t" + playerID) {
              grid(i)(j) == playerID
            }

          }
        }
      }

      /** Sets cells on a not yet closed path to temporary captured */
      def setTemp(playerID: Int): Unit = {

      }

      /** Gets the status of a cell : captured, temporary captured, empty, wall */
      def getState(playerID: Int): Unit = {

      }

      /** Fills the captured area with captured cells */
      def fillCaptured(playerID: Int): Unit = {

        for (i <- this.grid.indices) {
          for (j <- this.grid(i).indices) {

          }
        }
      }*/

    /** Displays the grid */
    def displayGrid(): Unit = {

      for (i <- this.grid.indices) {
        for (j <- this.grid(i).indices) {
          print(s" ${grid(i)(j)} ")
        }
        println()
      }
    }

  }

}
