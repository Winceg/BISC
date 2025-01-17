import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.net.Socket

class clientConnect {
  var clientSocket: Socket = _
  var out: PrintWriter = _
  var in: BufferedReader = _

  def connect(ip: String, port: Int): Unit = {
    clientSocket = new Socket(ip, port)
    out = new PrintWriter(clientSocket.getOutputStream, true)
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream))
    println(s"Client connected to $ip:$port")
  }

  def sendMessage(msg: String): String = {
    out.println(msg)
    val resp = in.readLine
    resp
  }

  def stopConnection(): Unit = {
    in.close()
    out.close()
    clientSocket.close()
  }
}

object clientConnect extends App {
  val c : clientConnect = new clientConnect()

  c.connect("localhost", 6666)

  // Blocking call as well, not perfect but working since we haven't seen threads
  val response = c.sendMessage("hello server")

  println(s"The server replied")
  println(s"	> '$response'")
  c.stopConnection()
}