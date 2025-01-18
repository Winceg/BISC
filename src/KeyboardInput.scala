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

      if (players.isDefinedAt(1)) {
        if (playerID == players(1).playerID) {
          /** Player 2 input */
          if (e.getKeyChar == 'd') setReturnString("r" + players(1).playerID)
          if (e.getKeyChar == 'a') setReturnString("l" + players(1).playerID)
          if (e.getKeyChar == 'w') setReturnString("u" + players(1).playerID)
          if (e.getKeyChar == 's') setReturnString("d" + players(1).playerID)
        }
      }

      if (players.isDefinedAt(2)) {
        if (playerID == players(2).playerID) {
          /** Player 3 input */
          if (e.getKeyChar == 'l') setReturnString("r" + players(2).playerID)
          if (e.getKeyChar == 'j') setReturnString("l" + players(2).playerID)
          if (e.getKeyChar == 'i') setReturnString("u" + players(2).playerID)
          if (e.getKeyChar == 'k') setReturnString("d" + players(2).playerID)
        }
      }

      if (players.isDefinedAt(3)) {
        if (playerID == players(3).playerID) {
          /** Player 4 input */
          if (e.getKeyCode == KeyEvent.VK_NUMPAD6) setReturnString("r" + players(3).playerID)
          if (e.getKeyCode == KeyEvent.VK_NUMPAD4) setReturnString("l" + players(3).playerID)
          if (e.getKeyCode == KeyEvent.VK_NUMPAD8) setReturnString("u" + players(3).playerID)
          if (e.getKeyCode == KeyEvent.VK_NUMPAD5) setReturnString("d" + players(3).playerID)
        }
      }

      if (playerID == 0) {
        if (e.getKeyCode == KeyEvent.VK_ESCAPE) setReturnString("esc")
        if (e.getKeyCode == KeyEvent.VK_ENTER) setReturnString("enter")
        if (e.getKeyCode == KeyEvent.VK_NUMPAD1) setReturnString("1")
        if (e.getKeyCode == KeyEvent.VK_NUMPAD2) setReturnString("2")
        if (e.getKeyCode == KeyEvent.VK_NUMPAD3) setReturnString("3")
        if (e.getKeyCode == KeyEvent.VK_NUMPAD4) setReturnString("4")
      }

    }
  })
}

class MenuKeyboardInput(val fg: FunGraphics) {
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
      if (e.getKeyCode == KeyEvent.VK_ESCAPE) setReturnString("esc")
      if (e.getKeyCode == KeyEvent.VK_ENTER) setReturnString("enter")
      if (e.getKeyCode == KeyEvent.VK_NUMPAD1) setReturnString("1")
      if (e.getKeyCode == KeyEvent.VK_NUMPAD2) setReturnString("2")
      if (e.getKeyCode == KeyEvent.VK_NUMPAD3) setReturnString("3")
      if (e.getKeyCode == KeyEvent.VK_NUMPAD4) setReturnString("4")
      if (e.getKeyCode == KeyEvent.VK_1) setReturnString("1")
      if (e.getKeyCode == KeyEvent.VK_2) setReturnString("2")
      if (e.getKeyCode == KeyEvent.VK_3) setReturnString("3")
      if (e.getKeyCode == KeyEvent.VK_4) setReturnString("4")
    }
  })
}
