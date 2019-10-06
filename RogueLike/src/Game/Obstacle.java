package Game;

import java.awt.Color;

import Engine.Component;
import Engine.GameObject;

/*
 * What exactly is an obstacle supposed to do?
 */
public class Obstacle extends GameObject {

	public final static Color myColor = Color.GREEN;

	public Obstacle() {
		super(null); // obstacles need no name
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
