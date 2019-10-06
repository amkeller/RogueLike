package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Engine.GameObject;
import Engine.GridMap;
import Engine.Main;

public class Mover extends GameObject {
	
	/**
	 * Mover is a base class for the types of GameObject that move
	 * @param name
	 */
	public Mover(String name) {
		super(name);
	}
	
	// direction of shooting/moving is direction of last keypress
	protected int direction;
	private int lastx = 0;
	private int lasty = 0;
	
	public void setDir(int c) { this.direction = c; }
	public int getDir() { return this.direction; }

	/** override in bullet subclass; bullet destructs if blocked
	 * by obstacle and destructs the adversary if blocked by 
	 * adversary
	 */
	public void blocked() {
	}
	
	// override to add functionality only in player class
	public void shoot() {
	}

	// gets direction from last keypress or current keypress
	public void direction() {
		if (Main.keyPresses.isEmpty() != true) {
			int keyCode = Main.keyPresses.remove(0);
			if (keyCode == KeyEvent.VK_W) {
				this.direction = GridMap.NORTH; // up
			}
			else if (keyCode == KeyEvent.VK_A) {
				this.direction = GridMap.WEST; // down
			}
			else if (keyCode == KeyEvent.VK_S) {
				this.direction = GridMap.SOUTH; // south
			}
			else if (keyCode == KeyEvent.VK_D) {
				this.direction = GridMap.EAST; // east
			}
			else if (keyCode == KeyEvent.VK_SPACE) {
				shoot();
			}
		}
	}

	// set the direction and maybe take one step
	private void move() {
		int nextx = this.getX(), nexty = this.getY();
		switch (this.direction) {
		case GridMap.NORTH :
			nexty -= 1;
			break;
		case GridMap.EAST :
			nextx += 1;
			break;
		case GridMap.SOUTH :
			nexty += 1;
			break;
		case GridMap.WEST :
			nextx -= 1;
			break;
		default :
		}
		// take a step if there's no obstacle or adversary there
		if (Main.grid.getColor(nexty, nextx).equals(Main.grid.freeColor)) {
			// no obstacle, free to move
			this.lastx = this.getX();
			this.lasty = this.getY();
			this.setX(nextx);
			this.setY(nexty);
			// grid color change associated with a move
			Main.grid.setColor(this.lastx, this.lasty, Main.grid.freeColor); 
			Main.grid.setColor(this.getX(), this.getY(), Color.RED);
		}
		else {
			blocked(); // derived classes process collisions differently
		}
	}
}
