# README #

## Group Information ##

**Team Members**
Karma Luitel
Shaurya Nair
Winfred Lin

**Group Number:** 5

**Period:** 4

**Game Title:** Iron Lung


## Game Proposal ##

<p>On a foreign planet, a mysterious fungus called corruption spreads through the underground
and the player has to get drill upgrades in order to finally mine the core corruption, the root
of the infection, in order to win the game, however if the player is too slow and the corruption 
spreads too much and takes over the planet, you lose. 

When you spawn in, you spawn in a default 3x3 spawn space. You can move around, and if
you hold the key in a direction of a block/ore, it will break. Different ores have 
different mining speeds. In addition, every time you break an ore, you lose fuel. In order
to get fuel, you need coal and go back to base in order to convert it to oil. Also at the
base there is an upgrade station for your drill and fuel tank size.
</p>

Game Controls:
 + Movement: W, A, S, D
 + Digging: move in the direction of a rock/ore
 + In order to go to the surface world, click one home button (top left corner)
 + upgrade station is a vending machine on right side
 + Middle is refinery
 + When opened upgrade station, scroll by clicking arrows at the bottom of the GUI
 + When opened refinery, click on refine to refine all of a type of material
 + In order to refine, you need to refine coal into coke to use to refine (althought be careful, as only coal can be used to fuel your vehicle, but not coke)
 + The Goal of the game is to break the core corruption (heart at the bottom of the map)


Game Elements:
 + Have player always at the center of the screen, only moving the background
 + At top of world is base with an upgrade shop, a refinery and a fuel station
 + Base has base upgrade module, a drill upgrade module, and fuel upgrade module
 + Upgrade module allows player to upgrade their drill, fuel tank, and the visibility they have underground
 + Default drill: can mine stone, copper, coal
 + Copper drill: can mine stone, copper, coal, iron, gold
 + Gold drill: can mine stone, copper, coal, iron, gold, diamond, uranium, corruption
 + Diamond drill: can mine stone, copper, coal, iron, gold, diamond, uranium, corruption, root (everything)
 + Fuel station allows the player to refuel their fuel tank using coal
 + Refinery allows the player to use coke (refined coal) to refine ores in order to use the materials gained in the upgrade shop
 + Refining costs 1 coke per material no matter how much of each material is refined
 + Can teleport in or out of the mines by going to the surface
 + Needs fuel to mine the ground/rocks (cant mine if no fuel)
 + Materials: Coal (for fuel), Coke (for refining), Uranium (for fuel), Copper (for drill), Gold (for drill), Iron (for drill chasis), Titanium (for drill chasis), Diamond (for drill)
 + Visibility of ores: ores are seperate actors 
How to Win:

 + Mine the core corruption before corruption takes over the default 3x3 spawning space

## Link Examples ##

 + Youtube video of motherload speedrun: [Speedrun](https://www.youtube.com/watch?v=4fxdCOtPs-0)
 + Example of similar digging game: [Deep Miners Idle](https://www.crazygames.com/game/deep-miners-idle)
 + Youtube video of dome base: [Dome Keeper Gameplay](https://www.youtube.com/watch?v=4sxBNAsQyOc&t=13s)

## Teacher Response ##

4/26 This game can work as long as there is some kind of opposition or goal that makes the game both fun and challenging.  For example, is this a resource puzzle game where players have limited fuel and must cleverly use that fuel to get through a level or is this a real-time game where the user must beat some kind of realtime event like an invasion of enemies or a countdown timer?  Basically, where is the challenge/fun in this game and how is it modulated over time?

>>> We decided to add some lore which is that you were a miner going to a planet to mine for materials. The ship you were on crashed and you find out a astroid is about to strike in 14 days, so you have to build a rocket to escape using materials you mine.
>>> Instead of an asteriod crashing, we've changed it to the planet being consumed by corruption. The end goal is to destroy the core corruption.

## Class Design and Brainstorm ##

### Brainstorm ###
 + For digging holes, instead of making holes in the background, have a tile appear behind player that looks like a hole
 + If not enough time, might not add base mechanic
 + Cannot go upwards, there is gravity in this game (use a gravity constant)
 + Round Player to a grid position 
 + Corruption. As the game progresses, the ores and stone are corrupted. The end goal is to destroy the core corruption block.
 + Make drill snap to middle of block when mining down
 + add diamond (replacing uranium drill) and titanium (for diamond drill chasis)
 + make corruption blocks transfer over when switching worlds
 + if corruption blocks spread into a hole, it breaks the hole
 + add corruption counter 
 + if corruption touches the default starting square, you lose
 + if you break the core corruption you win
 + add fortune levels for coal based on drill level
 + Make fuel station convert 1 coal to 50 fuel
 + maybe, add connected tunnel textures
 + add sound effects
 + make it so that only corruption on your screen gets added to the world (do this by adding all corruption to an array and only adding it to the world if it is within the player's screen
 + add breadth of light that increases your range of vision
 + Add a refinery for smelting raw ores into refined versions of themselves so that they can be used in the shop

### Class Design ###
 + Hole: will appear a grid position ahead of where the drill is after holding a direction key for long enough
 + Vehicle: is the player, and can move left, right and down
 + Ore: Will have many different subclasses for the different type of ores and will spawn randomly
 + Coal: Subclass of Ore, used for fuel, most common ore
 + Copper: Subclass of Ore, used for electronics/ as a metal, common to find
 + Gold: Subclass of Ore, used for electronics, rare to find
 + Iron: Subclass of Ore, building material, rare to find
 + Sand: Subclass of Ore, used for glass, very quick to break
 + Uranimum: Subclass of Ore, used as a long lasting fuel source, very rare
 + Fuel Bar: will display the amount of fuel the vehicle has left
 + Depth Counter: will display in meters the depth of the vehicle'
 + Breaking: used as a class to animate digging

***
***
