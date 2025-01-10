class Arena(val gridSizeX: Int, val gridSizeY: Int) {

  /** CONTENT : Floodfill, arena state */

  /** Class defining the whole arena, and functions to modify the cells on the arena */
  var grid: Array[Array[Int]] = Array.fill(gridSizeX, gridSizeY)(0: Int) // dont forget the +2 later !!!!!!

  /* its only a visual option --> for later
  for (i <- grid.indices) {
    for (j <- grid(i).indices) {
      if (i == 0 || i == grid.length - 1 || i != 0 && j == 0 || i != 0 && j == grid(0).length - 1) {
        grid(i)(j) = "-"
      }
    }
  }
  */

  /** Sets current position of player */
  def setCurrentPos(currentPos: Array[Int], playerID: Int): Unit = {
    this.grid(currentPos(0))(currentPos(1)) = playerID
  }

  /** Sets cells on a not yet closed path to temporary captured */
  def setTemp(lastPos: Array[Int]): Unit = {
    this.grid(lastPos(0))(lastPos(1)) = 2
  }

  /** Code donné pour le flood fill : */

  import scala.collection.mutable

  def floodFill(startX: Int, startY: Int, playerID: Int): Unit = {
    val grid: Array[Array[Int]] = Array.fill(this.gridSizeX, this.gridSizeY)(0: Int)
    val stack = mutable.Stack((startX, startY)) //not understood
    val visited = mutable.Set[(Int, Int)]() //not understood

    /** Populates the temporary flood array with already captured cells */
    for (i <- this.grid.indices) {
      for (j <- this.grid(i).indices) {
        if (this.grid(i)(j) == playerID || this.grid(i)(j) == 2) grid(i)(j) = playerID else grid(i)(j) = 0 //may add player position
        /* visual option , for later
        if (i == 0 || i == grid.length - 1 || i != 0 && j == 0 || i != 0 && j == grid(0).length - 1) {
          grid(i)(j) = "-" */
      }
    }

    /*
    println("Tablu temporaire")
    for (i <- this.grid.indices) {
      for (j <- this.grid(i).indices) {
        print(s" ${grid(i)(j)} ")
      }
      println()
    }
    */
    //whole section not understood
    while (stack.nonEmpty) {
      val (x, y) = stack.pop()
      //println(s"x: $x, y: $y, value = ${getCell(grid, x, y)}")

      if (getCell(grid, x, y) == 0 && !visited.contains((x, y))) {
        setCell(grid, x, y, 9) // + playerID) // Use player trace (2) for filling
        visited.add((x, y))
        // Explore all four directions
        stack.push((x + 1, y))
        stack.push((x - 1, y))
        stack.push((x, y + 1))
        stack.push((x, y - 1))

        /*
        println("Tablu temporaire")
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
        if (grid(i)(j) != 9) {
          setCell(this.grid, i, j, playerID) // else this.grid(i)(j) = this.grid(i)(j)
        }
      }
    }

    /** Gets the value of the cell in the temporary flood array */
    def getCell(grid: Array[Array[Int]], x: Int, y: Int): Int = {
      return grid(x)(y)
    }

    /** Sets the value of the cell in the temporary flood array */
    def setCell(grid: Array[Array[Int]], x: Int, y: Int, fill: Int): Array[Array[Int]] = {
      grid(x)(y) = fill
      return grid
    }

    /** Gets the status of a cell and return it : captured, temporary captured, empty */
    def getStatus(pos: Array[Int]): Int = {
      return grid(pos(0))(pos(1))
    }


    /*
  def action(status: String, playerID: Int): Unit = {
    match{
      case playerID.toString
      =>println("floodFill")
      }
  }

      /** Fills the captured area with captured cells */
      def fillCaptured(playerID: Int): Unit = {

        for (i <- this.grid.indices) {
          for (j <- this.grid(i).indices) {

          }
        }
      }*/
  }
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
