import hevs.graphics.FunGraphics
import java.awt.event.{KeyAdapter, KeyEvent}

object KeyboardInput extends App{
  /**
   * This class demonstrate how to implement keyboard events
   * using the FunGraphics library.
   */

  val fg: FunGraphics = new FunGraphics(640, 480)
  var yOffset = 0
  var xOffset = 0

  // Do something when a key has been pressed
  fg.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_RIGHT) yOffset += 2
      if (e.getKeyCode == KeyEvent.VK_LEFT) yOffset -= 2
      if (e.getKeyCode == KeyEvent.VK_UP) xOffset -= 2
      if (e.getKeyCode == KeyEvent.VK_DOWN) xOffset += 2
    }
  })

  while (true) {
    fg.clear()
    //draw our object
    fg.drawRect(50 + yOffset * 2, 50 + xOffset * 2, 75, 75)
    //refresh the screen at 60 FPS
    fg.syncGameLogic(60)
  }

}
