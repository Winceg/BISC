<h1>Game description</h1>
///////     insert game description here !      ///////

![Screenshot menu](https://github.com/user-attachments/assets/535c8fb2-f04e-4284-955a-009e0c4bbd2e)
![Screenshot start](https://github.com/user-attachments/assets/88017cba-ef6c-48f1-ad38-0389f93d868b)
![Screenshot in-game](https://github.com/user-attachments/assets/77eadaf9-4b90-4f16-9fe9-0ee62ea37e33)
![Screenshot game over](https://github.com/user-attachments/assets/1e8dd525-976d-4063-920e-81b7348ff035)

<h1>User manual</h1>
///////     insert user manual here !      ///////

<h1>Code explanation :</h1>

<h2>The Arena class</h2>
The arena is a grid, an array of arrays, containing values, representing the status of each cell (free, captured by a player, tail of a player, etc.)
An arena object is created with the wanted size.
<h3>action() function</h3>
Each player moves into the arena, by incrementing or decrementing x and y values of his position in the grid:
<ul>
  <li>If the player's position gets out of the array, the game is over for him.</li>
  <li>If the player's position is in a cell that contains his own tail, the game is over for him</li>
  <li>If the player's position is in a cell containing another player's tail, the game is over for the other player</li>
  <li>If the player's position corresponds to a cell already captured by himself, it triggers the floodFill() function</li>
</ul>
<h3>floodFill() function</h3>
The function runs through the entire array, by moving to the next free cell, which means that it can't go through the perimeter established by the player.
Every cell outside the perimeter is set to 0, and then every cell that is not a zero gets turned into the player's ID, as captured.

<h2>The Player class</h2>
This class contains all the player's attributes :
<ul>
  <li>starting position</li>
  <li>current position: position of the player after having moved</li>
  <li>last position</li>
  <li>direction: direction in which the player is moving</li>
  <li>last direction</li>
  <li>score: number of captured cells</li>
  <li>game over: if the player has lost, this value is true</li>
</ul>
<h3>playerMove() function</h3>
This function receives the keyboard input, checks if the input is opposite to the direction, and ignores the input if it's the case (otherwise the head hits
the tail and the game is over).
The function the increments or decrements the x and y values, and check if the next position is within the arena's boundaries.
<h3>getScore() function</h3>
This function runs through all the cells in the array and counts the cells containing the player's ID.
<h3>startDirection() function</h3>
Checks if the starting position is in the left or right side of the arena and determines in which direction the first movement will be.

<h2>The GameDisplay class</h2>

///////     insert code description here !      ///////
///////     insert code description here !      ///////

<h2>The KeyboardInput class</h2>
This class is responsible for listening to the keyboard inputs and returning the direction to each player.

<h2>Main</h2>
At first, the Arena object is created, with the desired size (only one value, the arena is always a square).
Then a new GameDisplay object is created : this object is responsible for the graphic part.
A mainKeyboard object is created : this object will listen to keyboard inputs not related to a specific player.
The players are now created, with their ID, starting position, and a keyboard listener.
<h3>Main loop</h3>
As long as we want to play, we stay in that loop.
The menu is displayed, followed by th launching sequence.
<h4>Game loop</h4>
As long as no player has made a terrible mistake, we stay in this loop.
<h5>Player loop</h5>
This code is where the main action of the game happens, and it loops through the array of players : for each player, the following actions are executed :
<ul>
  <li>displaying the grid</li>
  <li>getting the player's last direction and his keyboard input</li>
  <li>make the player move</li>
  <li>executing the action based on the position of the player and the content of the cell
    <ul>
    <li>floodFill if the player closes his perimeter</li>
    <li>game over if he hits his own tail</li>
    <li>game over for the other player if he hits the other player's tail</li>
    <li>sets the tail of the player (cells that are not yet captured)</li>
    </ul>
  </li>
</ul>
<h5>Game over loop</h5>
This loop checks through the array of players if one or more are game over.
<h4>Game over screen</h4>
A for loop checks which player has won (which player has not gameOver = true), and then the scores and winner are displayed on the screen.
If the "enter" key is pressed, a new game is started. If the "esc" key is pressed, we exit the game.
<h4>Resetting</h4>
The grid and the player are reset to the starting values, in order to start a new game.
