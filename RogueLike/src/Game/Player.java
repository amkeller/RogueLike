package Game;

import java.awt.Color;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import Engine.Component;
import Engine.GameObject;
import Engine.Main;
import Engine.GridMap;

public class Player extends Mover {
	
	// constsructors
	public Player(String name) {
		super(name);
		this.direction = GridMap.NORTH;
		myColor = Color.RED;
		// add components here?
		this.addComponent(new Move(this));
	};
	
	public Player() {
		super(null);
		this.direction = GridMap.NORTH;
		this.addComponent(new Move(this));
	};
	
	@Override
	public void shoot() {
		Bullet b = new Bullet();
		b.setX(this.getX());
		b.setY(this.getY());
		b.setDir(this.getDir());
		Main.gameObjs.add(b);
	}

}
