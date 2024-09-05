# Karma's Journal

## Tuesday 4/26/2022 in class - 1 hour
<p>I first learned the usese of Github, and created an account the usage of git pull/push. I learned how to resolve merge and diverging errors, and the usage of md filetype and its syntax. No overall work put into the project, just brainstormed ideas and chose a digging game.
</p>

## Wednesday 4/27/2022 at home - 1 hour
<p>Began work on the dig functunality of the game, encountered some issues with the coordinate system (diffrence between greenfoot and player coordinates). Going to make a fix for the strange issues where holes dont render in the correct spot soon. Other than that, brainstormed some ways to implement vertical dig, will write some prototype code for it soon. 
</p>

## Friday 4/29/2022 at home - 1 hour
<p>Fixed graphics issue from last commit, added ore hardness (impacts breaking time), internal ore counter, internal fuel system (more fuel required for harder ores), internal depth value, added an overall ore class, and lastly fixed a couple of rendering issues with the last commit.
</p>

## Friday 4/29/2022 at home - 1 hour
<p>Started work on adding a fuel bar, I also updated old ore textures and fixed an issue with ore hardness mining.
</p>

## Tuesday 5/3/2022 at home - 1 hour
<p>Added bedrock, which is an indestructible border block. It generates around the area that ore does, with a 4 wide gap. Along with this, I added a developer state to the game, which allows for easy movement when adding objects around the world. I also made sure that the new corruption required a root block to grow, and made sure that bedrock couldnt be corrupted.
</p>

## Thursday 5/5/2022 at home - 1 hour
<p>Added internal values for ore drill types, cleaned up some of the vehicle code.
</p>

## Friday 5/6/2022 in class - 1.5 hours
<p>Tweaked corruption mechanics, so that they will corrupt empty holes. I also added hardness for the corruption, so corrupted blocks take longer to mine. Lastly, I made sure that ores, corruption, and root tiles are deleted from the world wehn mined, rather than just being hidden. I also added color to the ore counters.
</p>

## Sunday 5/8/2022 at home - 3 hours
<p>Big push today. I first added a way to exit the shop and return to a world with the corruption still there, using a world parameter in the constructor. I then set up some framwwork for the shop, adding access to the vehicle class and main world class. I also made the 3 drill types purchasable, as a method to test said framwork. Lastly, I made it so that higher tier drills will not only break stronger tiles, but also mine other tiles quicker. 
</p>

## Tuesday 5/10/2022 at home - 0.5 hours
<p>I added icons for the new ores that were added, along with tweaking corruption spreading. We brainstrormed in class how to make corruption run better, and decided that we should implement some form of chunkloading.
</p>

## Wednesday 5/11/2022 at home - 2 hours
<p>I began adding the framework for chunkloading, just for corruption blocks. This took a while, and rather than using a 2D array, I decided to go with a 1D array of points, which is basically a 2D array. I pushed toa new branch, as to not mess up the main code by accident.
</p>

## Thursday 5/12/2022 at home - 2 hours
<p>I finished writing the first version of the corruption chunkloading today.It was filled with lots of issues, so i fixed most of the overlapping bugs by creating an overall array to hold all the corrupted points. I also merged the main branch with the corruption chunkloading, and pushed, as the chunkloading was now stable enought to go into the main version.
</p>

## Friday 5/13/2022 at home (morning) - 2 hours
<p>I found some new pixel art assets and compiled them with the surface world, to create 4 new images tha                         t the surface world changes to as the drill upgrades, to show the player industializing the world.
</p>

## Tuesday 5/17/2022 at school - 0.5 hours
<p>Fixed a corruption bug which caused corruption to spread to blocks that should have been blacklisted from becoming corrupted.
</p>

## Thursday 5/19/2022 at school - 1.5 hours
<p>I added basic sfx to the game, and updated the new surface images, so the changed backround assets would be visible. The sfx included BGM, heartbeat sounds, breaking sounds (unfinished), clicking sounds, and upgrading sounds.
</p>

## Friday 5/20/2022 at home - 1.5 hours
<p>I added a fade effect from switching between certain worlds.
</p>

## Saturday 5/21/2022 at home - 1.5 hours
<p>I added a fade effect from switching between certain worlds.
</p>

## Sunday 5/22/2022 at home - 1 hour
<p>I fixed some of the sound volumes, along with retuexturing the root tile.
</p>

## Monday 5/23/2022 at home - 2 hours
<p>Added beating heart to tile screen, along with fixing various refinery bugs.
</p>

## Tuesdat 5/24/2022 at home - 1 hour
<p>Fixed a bug with platinum being spelled incorrectly in the refined metal registry, and added a scrlling text effect when you lose/win. 
</p>




