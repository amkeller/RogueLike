# RogueLike

<p>I just started writing stuff to try to get a sense of how things worked. It's not actually organized into logic & graphic categories or not even necessarily in the right classes yet.</p>

<p>I did work out the following basic ideas:</p>

<p>Events, like input keypresses, collisions and the creation/deletion of GameObjects, are managed in lists or collections. There's an input keypress ArrayList that only the Player object really needs to look at during its turn. There's a collisions ArrayList that the bullet, adversary & player objects need to look at during their turns. And then killing/removing something is removing all references to it, which basically means removing it from the gameObjects ArrayList, setting the color of its current cell to the free space color (white)  and changing the dead object's color to the empty cell color (just in case it isn't garbage collected immediately?), Main.grid.freeColor. So this implies the following:</p>

<p>-- All objects when they are created (including bullets) go into a global gameObjects list</p>

<p>-- All objects when they are removed/die get removed from the global gameObjects list & their ability to color squares might be turned off by changing their color attribute to the global free-grid-cell color, Main.grid.freeColor.</p>

<p>-- Collisions involve 2 objects, and there is a collision event that is generated & put into the global collisions list (I started one that isEngine.Main.collisions.</p> 

<p>-- When an object has its turn, it checks the collisions list & if there was a collision involving itself, it removes itself from the collision event it found itself in, and leaves the collision event in the list if the other object is still in it/hasn't handled its part of the collision yet.</p>

<p>-- I wrote some functions in the Collision class just to get some code in, but haven't hooked it all up yet or sorted it into logic/graphic parts.</p>

<p>-- There is a public static Collsion object created in the Main, called collisionsThrower, that can be used to generate collision events (called cEvents), but I don't think that's permanently necessary. It just helps with the coding right now. I made the collisions a Component so that the abilty to handle and throw collision events (create cEvents and put them into the Engine.Main.collisions list) can be added to the bullet, adversary & player classes.</p>

<p>-- I'm not sure Player needs a collision component as it doesn't really do anything when blocked by an obstacle, except get blocked from moving.</p>

<p>-- Bullets self-destruct when they collide with an obstacle and Adversaries get destroyed when a bullet collides with it</p>

<p>-- I wrote a Mover class that Bullet, Adversary & Player all derive from, so that I could keep the ideas about how Moving objects work in one place until it's all worked out. Mover objects have direction and move() abilitiies and also need to handle collisions. They're also the only objects that might get destroyed.</p>

<p>I just started writing stuff to work it all out. Nothing is organized or in a state ready to get all hooked up. And nothing is written in stone & can be replaced by functionality that is someone else's idea of how things should work!</p>

