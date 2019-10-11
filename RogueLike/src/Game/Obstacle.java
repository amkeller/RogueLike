package Game;

import java.awt.Color;


import Engine.Component;
import Engine.GameObject;
import Game.Collision.cEvent;

/*
 * What exactly is an obstacle supposed to do except block people & stop bullets?
 */
public class Obstacle extends GameObject {
	
	public Obstacle() {
		super("obstacle"); // obstacles need no name
		myColor = Color.GREEN;
		this.addComponent(new Collision(this));
	}
	
	public Obstacle(String name) {
		super(name); // obstacles need no name
		myColor = Color.GREEN;
		this.addComponent(new Collision(this));
	}
	
	// used by gridMap initializer to set them in gred
	public Obstacle(String name, int x, int y) {
		super(name);
		this.setX(x);
		this.setY(y);
		this.addComponent(new Collision(this));
		
	}
	
	// needed to test whether parent is a member of a cEvent in a collisions list
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if ((other == null) || !(other instanceof Obstacle)) return false;
		// parent of this component matches the parent GameObject that created 
		// the other component or it's the other GameObject in the collision
		if (this.getX() == ((GameObject) other).getX() && this.getY() == ((GameObject) other).getY())
			return true;
		return false;
	}

}
