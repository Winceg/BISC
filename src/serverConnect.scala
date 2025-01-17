/** Server code **/
import java.io._
import java.net._

class serverConnect {
  var serverSocket: ServerSocket = _ // Default initializer

  def startServer(port: Int): Unit = {
    try {
      serverSocket = new ServerSocket(port)

      println(s"Server started, listening on port $port")

      // Listen on port and wait until an incoming connection arrives. BLOCKING METHOD
      val clientSocket = serverSocket.accept()

      // Splits the socket as two streams (in and out) which are wrapped
      // for buffered text IO
      val out = new PrintWriter(clientSocket.getOutputStream, true)
      val in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream))

      // Parse the incoming greeting, which will be finished by a \n
      val lanPlayerInput = in.readLine()
      println(s"lanPlayerInput: $lanPlayerInput")

      // Send back a reply
      if (lanPlayerInput.nonEmpty) {
        println("Sending back grid")
        out.println("Hello client, this is the server !")
      }

    } catch {
      case ex: Exception =>
        println(s"An error occured ${ex.getMessage}")
    }
    finally {
      serverSocket.close()
    }
  }
}

/*
// Must go to main
object serverConnect extends App {
  val server = new serverConnect()
  server.startServer(6666)

  /**
   * You can now connect to the server using Telnet for instance
   * If your *client* is on WSL2, use for hostname --> $(hostname).local
   */
}*/