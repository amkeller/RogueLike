package Game;

import java.awt.Color;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import Engine.Component;
import Engine.GameObject;
import Engine.Main;
import Engine.GridMap;

public class Player extends Mover {
	
	public final static Color myColor = Color.RED;
	
	// constsructors
	public Player(String name) {
		super(name);
		this.direction = GridMap.NORTH;
		this.addComponent(new Move(this));
	};
	
	public Player() {
		super(null);
		this.direction = GridMap.NORTH;
		this.addComponent(new Move(this));
	};

}
