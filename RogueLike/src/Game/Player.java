package Game;

import java.awt.Color;

import Engine.GameObject;
import Engine.GridMap;

public class Player extends GameObject {
	
	int shootDirection = GridMap.NORTH;
	
	// constsructors
	public Player(String name) {
		super("player");
		myColor = Color.RED;
		this.addComponent(new Input(this));
		this.addComponent(new Motion(this));
		this.addComponent(new Collision(this)); // player doesn't need use this
	};
	
	public Player() {
		super(null);
		myColor = Color.RED;
		this.addComponent(new Input(this));
		this.addComponent(new Motion(this));
		this.addComponent(new Collision(this)); // player doesn't need use this
	};
	
	void setShootDir(int direction) {
		this.shootDirection = direction;
	}
	int getShootDir() {
		return this.shootDirection;
	}
	
}
