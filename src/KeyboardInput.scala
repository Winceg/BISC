import hevs.graphics.FunGraphics
import java.awt.event.{KeyAdapter, KeyEvent}

class KeyboardInput(val fg: FunGraphics, val players: Array[Player], playerID: Int) {
  var returnString: String = ""

  /** Sets the return string that the game will need to perform actions */
  def setReturnString(returnString: String): Unit = {
    this.returnString = returnString
  }

  /** Returns the string that the game will need to perform actions */
  def getReturnString(): String = {
    this.returnString
  }

  // Do something when a key has been pressed
  fg.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (playerID == players(0).playerID) {
        /** Player 1 input */
        if (e.getKeyCode == KeyEvent.VK_RIGHT) setReturnString("r" + players(0).playerID)
        if (e.getKeyCode == KeyEvent.VK_LEFT) setReturnString("l" + players(0).playerID)
        if (e.getKeyCode == KeyEvent.VK_UP) setReturnString("u" + players(0).playerID)
        if (e.getKeyCode == KeyEvent.VK_DOWN) setReturnString("d" + players(0).playerID)
      }

      if (playerID == players(1).playerID) {
        /** Player 2 input */
        if (e.getKeyChar == 'd') setReturnString("r" + players(1).playerID)
        if (e.getKeyChar == 'a') setReturnString("l" + players(1).playerID)
        if (e.getKeyChar == 'w') setReturnString("u" + players(1).playerID)
        if (e.getKeyChar == 's') setReturnString("d" + players(1).playerID)
      }

      if (playerID == 0) {
        if (e.getKeyCode == KeyEvent.VK_ESCAPE) setReturnString("esc")
        if (e.getKeyCode == KeyEvent.VK_ENTER) setReturnString("enter")
      }
    }
  })
}
