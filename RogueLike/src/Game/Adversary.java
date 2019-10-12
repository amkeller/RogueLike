package Game;

import java.awt.Color;

import Engine.GameObject;

// the functionality for movement exists in mover
// except for the functionality for pathfinding movement
public class Adversary extends GameObject {
		
	public Adversary(String name) {
		super(name);
		myColor = Color.DARK_GRAY;
		this.addComponent(new Motion(this));
		this.addComponent(new Pathfinder(this));
	}
	public Adversary() {
		super("adversary");
		myColor = Color.DARK_GRAY;
		this.addComponent(new Motion(this));
		this.addComponent(new Pathfinder(this));
	}

}