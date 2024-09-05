# README #

## Group Information ##

**Team Members**
Karma Luitel, Winfred Lin, and Tuhin Mondal

**Game Title:** Kenophobia

## Game Proposal ##

Exploration game. The player is in a black void, with no visible terrain. The level has no texture, yet the player does collide with the objects. Using their tool, the player can shoot out beams that leave a dot if they hit a surface. Diffrent surfaces (such as doors vs walls) will have a diffrent dot color, allowing for the player to better diffrentiate their goal and make their way towards it. This tool can shoot the beams in diffrent modes, such as a beam or a scan, and allow the player to "see" through the dots left behind, and navigate the level (based of LIDAR). The aim is to explore and eventually escape the level where the player is trapped. During this, an enemy is chasing you. The enemy will teleport behind the player at random times and will have a diffrent dot color when scanned, and scanning the enemy begins a short chase sequence. After this sequence, the enemy dissapears for another amount of time, until reappearing in some random location ahead of the player and restarting the chase sequence. Along with generated levels, 3d models of a level can also be imported from Blender or other 3d editors.

Game Controls:

+ WASD to move
+ Primary mouse click to shoot beams (scroll to increase/decrease beam radius)
+ R to do a full scan of screen
+ Go near pages to open them
+ (not part of game) - change godmode variable to true in RUN.java to view the map, false to play the game

Game Elements:

+ 3d raycasting and collision - basically a light physics engine
+ Exploration/world design
+ Sound effects
+ Enemy
+ Page system to escape
+ Godmode Boolean (in RUN.java) to see map and some behind the scenes stuff - set to true
+ Boolean for altenate dot rendering technique using texture interpolaton (inside lidar3d_World.java, read comments at top of class where global vars are set) - set to true
+ Alternate collision handling function in Raycast.Java - much more optimized yet has bugs (Propogate_NEW()), using collision grid and line to grid algorithm to reduce number of collision checks for rays

How to Win:

+ Find all 5 pages, and arrange the for the door code based on the red numbers in the corner (1,2,3,4,5).
+ Escape the map. This will be when the player collides with a blue door at someplace in the map, and types in the correct code.
+ Pages, when scanned will show up as brown
+ Doorways, when scanned will show up as white
+ The Enemy, when scanned will show up as red

How to Lose:

+ Get hit by the enemy in a chase sequence.

***
