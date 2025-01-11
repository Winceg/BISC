class TestingArena (val gridSizeX: Int, val gridSizeY: Int) {

  var grid: Array[Array[String]] = Array.fill(gridSizeX+2 , gridSizeY+2)("0") // dont forget the +2 later !!!!!!
  for (i <- grid.indices) {
    for (j <- grid(i).indices) {
      if (i == 0 || i == grid.length - 1 || i != 0 && j == 0 || i != 0 && j == grid(0).length - 1) {
        grid(i)(j) = "-"
      }
    }
  }

  def displayGrid(): Unit = {
    for (i <- this.grid.indices) {
      for (j <- this.grid(i).indices) {
        print(s" ${grid(i)(j)} ")
      }
      println()
    }
  }
}
