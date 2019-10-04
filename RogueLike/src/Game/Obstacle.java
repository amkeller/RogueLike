package Game;

import Engine.Component;
import Engine.GameObject;

public class Obstacle extends GameObject {

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
