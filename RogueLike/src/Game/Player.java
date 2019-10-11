package Game;

import java.awt.Color;

import Engine.GameObject;
import Engine.GridMap;

public class Player extends GameObject {
	
	boolean shoot = false;
	
	// constsructors
	public Player(String name) {
		super("player");
		this.setDirection(GridMap.NORTH);
		myColor = Color.RED;
		this.addComponent(new Input(this));
		this.addComponent(new Motion(this));
		this.addComponent(new Collision(this)); // player doesn't need use this
	};
	
	public Player() {
		super(null);
		this.setDirection(GridMap.NORTH);
		myColor = Color.RED;
		this.addComponent(new Input(this));
		this.addComponent(new Motion(this));
		this.addComponent(new Collision(this)); // player doesn't need use this
	};
	
}
