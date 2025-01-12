class Arena(var gridSizeX: Int, var gridSizeY: Int) {
  /** Class defining the whole arena, and functions to modify the cells on the arena */
  /** CONTENT : floodFill, arena state */

  /** Adding room for a double border around the grid
   * Border 1 with "-" limits the floodFill function, border 2 with "0" limits the movements of the player (this is the actual border of the arena)
   * The floodFill function is then able to move outside of the playing border of the arena in order to flood all empty cells, for example when a player's surface separates the arena in 2 */
  gridSizeX += 4
  gridSizeY += 4

  /** Filling the grid with "*" as empty cells */
  var grid: Array[Array[String]] = Array.fill(gridSizeX, gridSizeY)("*")

  /** Turning border "*" to "-" */
  for (i <- this.grid.indices) {
    for (j <- this.grid(i).indices) {
      if (i == 1 && j > 1 || i == 1 && j < grid(0).length - 1 || i == grid.length - 2 && j != 0 || i == grid.length - 2 && j != grid(0).length - 1 || i != 0 && j == 1 || i != 0 && j == grid(0).length - 2) {
        grid(i)(j) = "0"
      }
      if (i == 0 || i == grid.length - 1 || i != 0 && j == 0 || i != 0 && j == grid(0).length - 1) {
        grid(i)(j) = "-"
      }
    }
  }

  /** Sets current position of player with an "x" and the player's ID (head of the "snake") */
  def setCurrentPos(currentPos: Array[Int], playerID: Int): Unit = {
    this.grid(currentPos(0))(currentPos(1)) = "x" + playerID.toString
  }

  /** Sets cells on a not yet closed path to temporary captured, with a "t" and the player's ID */
  def setTemp(lastPos: Array[Int], playerID: Int): Unit = {
    if (this.grid(lastPos(0))(lastPos(1)) != playerID.toString) {
      this.grid(lastPos(0))(lastPos(1)) = "t" + playerID.toString
    }
  }

  /** Code donné pour le flood fill : */

  import scala.collection.mutable

  def floodFill(playerID: Int, startX: Int = 1, startY: Int = 1): Unit = {
    val grid: Array[Array[String]] = Array.fill(this.gridSizeX, this.gridSizeY)("0")
    val stack = mutable.Stack((startX, startY)) //not understood
    val visited = mutable.Set[(Int, Int)]() //not understood

    /** Populates the temporary flood array with already captured cells */
    for (i <- this.grid.indices) {
      for (j <- this.grid(i).indices) {
        if (this.grid(i)(j) == playerID.toString || this.grid(i)(j) == "t" + playerID.toString || this.grid(i)(j) == "x" + playerID.toString) {
          grid(i)(j) = playerID.toString
        } else {
          if (i == 0 || i == grid.length - 1 || i != 0 && j == 0 || i != 0 && j == grid(0).length - 1) {
            grid(i)(j) = "-"
          } else {
            grid(i)(j) = "0"
          }
        }
      }
    }

    //whole section not understood
    while (stack.nonEmpty) {
      val (x, y) = stack.pop()

      /** Tries to increment x and y in each direction, checking if the next cell is valid */
      if (getCell(grid, x, y) == "0" && getCell(grid, x, y) != "-" && !visited.contains((x, y))) {
        setCell(grid, x, y, "f" + playerID) // Use player trace (2) for filling
        visited.add((x, y))
        // Explore all four directions
        stack.push((x + 1, y))
        stack.push((x - 1, y))
        stack.push((x, y + 1))
        stack.push((x, y - 1))

        /*
                println("Tablu temporaire2")
                for (i <- this.grid.indices) {
                  for (j <- this.grid(i).indices) {
                    print(s" ${grid(i)(j)} ")
                  }
                  println()
                }

         */
      }
    }

    /** Repopulates the original array with the player's captured surfaces */
    for (i <- grid.indices) {
      for (j <- grid(i).indices) {
        if (grid(i)(j) != "f" + playerID && grid(i)(j) != "-") {
          setCell(this.grid, i, j, playerID.toString) // else this.grid(i)(j) = this.grid(i)(j)
        }
      }
    }

    /** Gets the value of the cell in the temporary flood array */
    def getCell(grid: Array[Array[String]], x: Int, y: Int): String = {
      grid(x)(y)
    }

    /** Sets the value of the cell in the temporary flood array */
    def setCell(grid: Array[Array[String]], x: Int, y: Int, fill: String): Array[Array[String]] = {
      grid(x)(y) = fill
      grid
    }
  }

  def action(pos: Array[Int], playerID: String, players: Array[Player]): String = {
    this.grid(pos(0))(pos(1)) match {
      case s if s == playerID => "ff" // Returns "ff" for floodFill
      case s if s == s"t$playerID" =>
        println(s"Player${playerID}, you hit your own tail !")
        "go" // Returns "go" for game over
      // case s if s.substring(1) => return "go2" // Returns "go" for game over
      case _ => "sp" // Returns "sp" for set positions
    }
  }

  /** Displays the grid */
  def displayGrid(): Unit = {
    for (i <- this.grid.indices) {
      for (j <- this.grid(i).indices) {
        print(s" ${this.grid(i)(j)} ${if (this.grid(i)(j).length < 2) " " else ""}")
      }
      println()
    }
  }

}
