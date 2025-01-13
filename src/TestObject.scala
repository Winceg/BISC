object TestObject extends App{

  val a = new TestingArena(20,20)
  a.grid(2)(2) = "1"
  a.grid(2)(3) = "t1"
  a.grid(2)(4) = "x1"
  a.grid(2)(5) = "2"
  a.grid(2)(6) = "t2"
  a.grid(2)(7) = "x2"

  a.displayGrid()

  val b = new GameDisplay(a,20)
  b.displayCroppedGrid()
  b.gamePaintClock()

}
