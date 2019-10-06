package Game;

import java.awt.Color;

import Engine.Component;
import Engine.GameObject;

// the functionality for movement exists in mover
// except for the functionality for pathfinding movement
public class Adversary extends Mover {
		
	public Adversary(String name) {
		super(name);
		myColor = Color.DARK_GRAY;
		// add components here?
		this.addComponent(new Move(this));
	}
	public Adversary() {
		super(null);
		this.addComponent(new Move(this));
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
