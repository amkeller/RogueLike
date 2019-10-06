package Game;

import java.awt.Color;

import Engine.Component;
import Engine.GameObject;

/*
 * What exactly is an obstacle supposed to do except block people & stop bullets?
 */
public class Obstacle extends GameObject {
	
	// currently, obstacles doesn't exist yet. A function in GridMap, init(), is 
	// randomly putting green squares on the grid and moving objects are interacting
	// with obstacles by checking for green squares on the grid. But if we are
	// supposed to have obstacle objects, GridMap.init() should place Obstacles
	// instead of coloring green squares & the moving objects should interact with
	// the Obstacles instead of going off the color of the grid squares.

	public Obstacle() {
		super(null); // obstacles need no name
		myColor = Color.GREEN;
	}

	@Override
	public void addComponent(Component component) {
		
	}

	@Override
	public boolean removeComponent(Class Object) {
		return false;
	}

	@Override
	public Component getComponent(Class Object) {
		return null;
	}

}
