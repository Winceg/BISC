object BISC {
  /** Class defining the player attributes and functions */
  class Player(val playerID: Int) {
    var startPos: Array[Int] = Array(4, 4)
    var currentPos: Array[Int] = startPos
    var score: Int = 0
    var win: Boolean = false
    var gameOver: Boolean = false

    /* Gets user input and returns next position */
    def move(grid: Array[Array[String]]): Array[Int] = {
      var nextPos: Array[Int] = Array(0, 0)
      var input: String = Input.readString()

      println(s"${this.currentPos(0)}, ${this.currentPos(1)}")

      input match {
        case "d" => if ((this.currentPos(0) + 1) < grid.length) this.currentPos(0) += 1 else gameOver = true
        case "a" => if ((this.currentPos(0) - 1) > 0) this.currentPos(0) -= 1 else gameOver = true
        case "w" => if ((this.currentPos(1) + 1) < grid.length) this.currentPos(1) += 1 else gameOver = true
        case "s" => if ((this.currentPos(1) - 1) > 0) this.currentPos(1) -= 1 else gameOver = true
      }
      this.currentPos
    }
    /*
      def getScore(grid: Array[Array[string]]): Int = {
        for (i <- grid.indices) {
          for (j <- grid(i).indices) {

          }
        }

      }*/
  }

  /** Class defining the whole arena, and functions to modify the cells on the arena */
  class Arena(val gridSizeX: Int, val gridSizeY: Int) {
    var grid: Array[Array[String]] = Array.fill(gridSizeX, gridSizeY)("*")
    /*
      /* Sets freshly captured cells to captured */
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

        for (i <- this.grid.indices) {
          for (j <- this.grid(i).indices) {

          }
        }
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

        }
      }
    }

  }

}
