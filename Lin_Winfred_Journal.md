# Winfred Lin's Journal #

## Tues 4/26/2022 in class - 1 hour ##
<p>My team and I discussed what game we want to play, and we ended up deciding on a tycoon, mining game.
My part is to create the player movement as well as the background. I will also try making some basic assets for the game
Each grid position will probably be around 50x50
</p>

## Tues 4/26/2022 at home - 2 hour ##
<p>Made images for the ores. Also added the mining vehicle. Decided that 50x50 for each grid position is too
small and made it 100x100. Added animation for mining vehicle so that it looks like the drill is actually spinning.
made the movement for the vehicle, planning to add gravity later.
</p>

## Wed 4/27/2022 in class - 45 minutes ##
<p>Resolved all merge issues with merging code with other group members. Also, redid all the images for the ores, as they were all 50x50. In addition to making them 100x100, I also made it so that they are all transparent (this way the ores can fit in better with the background). Waiting on Karma to finish digging to add fuel and digging cooldown.
</p>

## Wed 4/27/2022 at home - 3+ hours ##
<p>Previously, drill image was not symmetrical. Since the drill animation involved flip vertically, it made the animation look weird and janky. So, I made the base drill symmetrical. Also considering to make the drill a seperate class or subclass for more easy upgrading of the drill bit. In addition, when Karma finished digging
he had some issues with holes not spawning correctly, or not spawning in the exact middle of a grid. Turns out, it was because the rounding helper function we made always rounded down, so if the playerX was 99, it would round  to 0. Furthermore, turns out that using modulos on negative numbers in Greenfoot is different from the calculator on Google (on google -99 % 100 is 1, but on greenfoot it is -99). Finally, I also added a dig cooldown.
</p>

## Thurs 4/28/2022 at home - 1 hour ##
<p>Found bug with digging down when x < 0 and fixed it. Added a breaking animation to make digging look natural. Then found a bug with my digging code and my breaking animation, as when you quickly turned away before breaking something, the progress wouldn't reset. Fixed that bug.
</p>

## Fri 4/29/2022 in class - 1 hour ##
<p>I updated the README to be more descriptive and up to date to what we are planning. Shaurya gave a suggestion of having a "fade" effect to make the mining more realstic, so I added that. Also added the asset for fuel bar (the outline of it)
</p>

## Sat 4/30/2022 at home - 1 hour ##
<p>Added a depth counter that divides the playey y level by 10 and displays it in meters. Also adjusted ore bars up a bit to not cover the ore icons. Spent most of my time making area of vision smaller the lower you go (capping at a certain size). Also fixed a bug where if the starting fuel value was anything else than 100, the bar would break. Added a different background for the game as well.
</p>

## Sun 5/1/2022 at home - 45 minutes ##
<p>Added the assets needed to make the upgrade shop as well as the fuel station. Spent most of my time today modifying existing images to make those assets. Also added a Button subclass for the GUI's that are needed to made later on. Changed it so that the ore counter displays numbers instead of just a bar
</p>
  
## Mon 5/2/2022 at home - 30 minutes ##
<p>Added GUI elements for the upgrade shop, still bugged, but have set up the basic infastructure (such as GUI class and Button class) for finishing the GUI.
</p>

## Tues 5/3/2022 at home - 1 hour ##
<p>Added the scroll bar and made it working. Decided to change the active conflict from trying to build a rocket to escape an astroid to get a drill in order to mine a corruption root (it grows and spreads corruption that slowly corrupts the entire world). Added corruption root and corruption. Made it so that corruption tries to spread every 500 frames and only spreads if the corruption root is still present
</p>

## Wed 5/4/2022 at home - 1 hour ##
<p>Added the GUI elements for the drill shop (spent most of the time modifying assets to make the drill icon).
</p>

## Fri 5/6/2022 in class - 1 hour ##
<p>Added the three different types of drills (uranium, gold, and copper) and made the animation for them so that the animation shown depends on the drill type.
</p>

## Fri 5/6/2022 at home - 1 hour ##
<p>Finished the drill shop an added the prices for the three drills (they are probably going to change soon). I also added the assets for buying and the assets for the fuel tank shop
</p>

## Sat 5/7/2022 at home - 2 hour ##
<p>Finished the upgrade shop. Added the fuel tank page, as well as the light page. Also made it so that each page had the prices of the item. Also made it so that the scroll button actually works to go through the shop pages. Started adding the fuel station and made the convert coal into 50 fuel button.
</p>

## Sun 5/8/2022 at home - 1 hour ##
<p>Made it so that the ore bar also appears in the surface. Also fixed the bug that decreased ore spawn rates if you went to the surface and back multiple times
</p>

## Mon 5/9/2022 at home - 1.5 hour ##
<p>Made the user be able to buy fuel tanks, also adjusted the prices of a couple things to make the game more beatable. Also added fuel conversion station so that you can refuel your drill. 
</p>

## Tues 5/10/2022 in class - 30 minutes ##
<p>Mostly implemented new ores. Spent some time discussing how to optimize the game and decrease lag. Tested a few of them, but ended up deciding on a chunk loading solution (as suggested by Mr. Ferrante). It would work by using the world class and checking around the player to see if there is a corruption/ore in that radius, it will appear in the world, otherwise it will only be in an array.
</p>

## Teus 5/10/2022 at home - 1 hour ##
<p>Spent time editing new drill texture for the 3 other types of drills and implementing it. Found that when going left, it didnt flip the image so it looked off and fixed that
</p>

## Wed 5/11/2022 at home - 2 hours ##
<p> Spent all of this time revamping the textures of the game. Made new textures for all the ores so that they looked better and more of a pixel art style. Also remade the stone texture as I didn't want our game looking TOO similar to minecraft. Remade the stone texutres 2 times as I wasn't satisfied with the first draft. Also started making new hole textures.
</p>

## Thur 5/12/2022 at home - 1.5 hour ##
<p>Spent my time to continue polish the look of the game. Remade the hole texture 2 more times until I got a texture I liked. I also remade the fuel bar border so that it looked less flat and made the glass more apparent. Also replaced uranium drill with diamond drill (that requirees diamond and titanium).
</p>

## Fri 5/13/2022 in class - 1 hour ##
<p>Spent my time making the breadth of light upgrades work in the shop. I also tweaked the speed of shrinking of the breadth of vision as you go down to make it more balanced (did the same with the upgrades).
</p>

## Fri 5/13/2022 at home - 45 minutes ##
<p>Spent my time remaking the go to surface button. Also made it so that there is a cooldown for going to the surface so that the player can't repeatedly regenerate ores.
</p>

## Sat 5/14/2022 at home - 45 minutes ##
<p>Spent my time making digging down being less sensitive to how close you have to be to the center of the block. Also fixed some ore generation issues that caused less ore spawning than there should have been, although ore spawning still needs some balancing.
</p>

## Sun 5/15/2022 at home - 1 hour ##
<p>Spent my time making it so that the block you were facing toward had an outline. If you can mine the block, the outline will be green, but if you can't, it will be red, making finding a block you can mine much easier. 

## Tues 5/17/2022 at home - 1 hour ##
<p>Spent my time adding an animation for when you push down a button, and made it so that if you already had a higher tier of something, everything else lower than that tier's buy button will appear pushed. Also made it so that you can FINALLY dig upwards and tweaked the collision systems accordingly, so far, it seems like there are not any bugs caused by this change. Also made it so that mining costs much more fuel.
</p>

## Wed 5/18/2022 at home - 30 minutes ##
<p>Spent my time adding win and lose screens. When you mine the core corruption you win, but, if the corruption spreads into the default 3x3 spawn space before you do this, you lose. Also planning to make the lose/ win screen a popup instead (a bit unsure).
</p>

## Fri 5/20/2022 in class - 1 hour ##
<p>Spent my time starting to make assets for the title screen and adjusting sfx
</p>

## Sat 5/21/2022 at home - 1 hour ##
<p>Spent my time finishing up assets for the title screen and implementing it. Need to add a how to screen/button and make the play button work. 
</p>

## Sun 5/22/2022 at home - 2 hours ##
<p>Spent my time finishing the title screen and making a how to play screen. Made the play button work, now just need to add the lose and win screen.
</p>

## Mon 5/23/2022 at home - 2 hours ##
<p>Spent my time adding the win and lose screen as well as a score system (2500 - the number of corruption left at the end of the game). Also spent some time bug fixing.
</p>

## Tues 5/24/2022 at home - 1 hour ##
<p>Spent my time reworking how refinery works, and did some bug fixing.
</p>
