//game description
![Screenshot start](https://github.com/user-attachments/assets/88017cba-ef6c-48f1-ad38-0389f93d868b)

//user manual


<h1>Code explanation :</h1>
<h2>The arena class</h2>
The arena is a grid, an array of arrays, containing values, representing the status of each cell (free, captured by a player, tail of a player, etc.)
An arena object is created with the wanted size.
<h3>action() function</h3>
Each player moves into the arena, by incrementing or decrementing x and y values of his position in the grid:
If the player's position gets out of the array, the game is over for him.
If the player's position is in a cell that contains his own tail, the game is over for him
If the player's position is in a cell containing another player's tail, the game is over for the other player
If the player's position corresponds to a cell already captured by himself, it triggers the floodFill() function
<h3>floodFill() function</h3>
The function runs through the entire array, by moving to the next free cell, which means that it can't go through the perimeter established by the player.
Every cell outside the perimeter is set to 0, and then every cell that is not a zero gets turned into the player's ID, as captured.
