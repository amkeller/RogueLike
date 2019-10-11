package Game;

import java.awt.Color;

import Engine.GameObject;
import Game.Collision;

// the functionality for bullets are in Mover, except for the
// functionality of destructing when blocked by obstacle or
// putting adversary into Main.dead list if blocked by adversary
public class Bullet extends GameObject {
	
	static int bulletID = 0;
	int id;
	
	public Bullet() {
		super("bullet");
		myColor = Color.BLUE;
		// add components here?
		this.addComponent(new Motion(this));
		this.addComponent(new Collision(this));	}
	
	public Bullet(String name) {
		super(name);  		// bullets need no name
		myColor = Color.BLUE;
		// add components here?
		this.addComponent(new Motion(this));
		this.addComponent(new Collision(this));
	}

}
