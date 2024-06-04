My project is a game where you have to dig out sand to get water to the goal. The code basically has two files, one which is the home screen where you choose the levels
and the other is where the game is rendered. The home screen is a simple swing menu where you choose your level. It then makes a game object which opens the game panel.
This renders all the objects in the level, while also starting the gameloop. Then when you drag, the sand is removed and score is decreased the more sand you displace. 
After the water cannot move anywhere anymore, the game is ended and the score + how many targets you hit comes up.

To play you click the level you want and then press the play button. You drag or click the mouse to remove sand and you want to connect the blue water from the top to the 
green goals. Water doesn't run through the gray stone.

Home.java renders the home screen and its components
Game.java renders the game and whichever level you chose
Main.java main method to start

sources:
https://jvm-gaming.org/t/game-loops/36689 - used to help make my gameloop






a brief summary of what your project does and how the code works
instructions on how to use your program
a short description for each file in your project and its role
a list of any sources that you referenced throughout the project and how you utilized the information from that source
