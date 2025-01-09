object BISC {
  /** Class defining the player attributes and functions */
  class Player(val playerID: Int, startPosX: Int, startPosY: Int) {
    val startPos: Array[Int] = Array(startPosX, startPosY)
    var currentPos: Array[Int] = startPos
    var lastPos: Array[Int] = currentPos
    var score: Int = 0
    var win: Boolean = false
    var gameOver: Boolean = false

    /** Gets user input and sets new current position */
    def playerMove(grid: Array[Array[String]]): Unit = {
      this.lastPos = Array(this.currentPos: _*)
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
          if (grid(i)(j) == this.playerID.toString) {
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

    /** Sets cells on a not yet closed path to temporary captured */
    def setTemp(lastPos: Array[Int], playerID: Int): Unit = {
      this.grid(lastPos(0))(lastPos(1)) = "t" /*+ playerID.toString*/
    }

    /** Code donné pour le flood fill : */

    import scala.collection.mutable

    def floodFill(startX: Int, startY: Int, playerID: Int): Unit = {
      var grid: Array[Array[String]] = Array.fill(this.gridSizeX, this.gridSizeY)("0")
      val stack = mutable.Stack((startX, startY))
      val visited = mutable.Set[(Int, Int)]()

      for (i <- this.grid.indices) {
        for (j <- this.grid(i).indices) {
          //println(s"${this.grid(i)(j)}")
          if (this.grid(i)(j) == playerID.toString || this.grid(i)(j) == "t") grid(i)(j) = playerID.toString else grid(i)(j) = "0"
        }
      }

      while (stack.nonEmpty) {
        val (x, y) = stack.pop()

        if (this.getStatus(Array(x, y)) != playerID.toString && !visited.contains((x, y))) {
          setCell(grid, x, y, "f" + playerID) // Use player trace (2) for filling
          visited.add((x, y))
          println("kikou")
          // Explore all four directions
          stack.push((x + 1, y))
          stack.push((x - 1, y))
          stack.push((x, y + 1))
          stack.push((x, y - 1))

          for (i <- this.grid.indices) {
            for (j <- this.grid(i).indices) {
              print(s" ${grid(i)(j)} ")
            }
            println()
          }
        }
      }
    }

    def setCell(grid: Array[Array[String]], x: Int, y: Int, fill: String): Array[Array[String]] = {
      grid(x)(y) = fill
      grid
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

*/

    /** Gets the status of a cell and return it : captured, temporary captured, empty */
    def getStatus(pos: Array[Int]): String = {
      grid(pos(0))(pos(1))
    }


    /*
    def action(status: String, playerID: Int): Unit = {
      match{
        case playerID.toString
        =>println("floodFill")
        case "*"
        =>
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
