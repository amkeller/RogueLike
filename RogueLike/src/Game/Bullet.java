package Game;

import java.awt.Color;

import Engine.Component;
import Engine.GameObject;
import Engine.GridMap;
import Engine.Main;
import Game.Collision;

// the functionality for bullets are in Mover, except for the
// functionality of destructing when blocked by obstacle or
// putting adversary into Main.dead list if blocked by adversary
public class Bullet extends Mover {
	
	public final static Color myColor = Color.BLUE;
	static int bulletID = 0;
	int id;
	public Bullet() {
		super(null);  		// bullets need no name
		this.id = bulletID;
		bulletID++;
		// add components
		this.addComponent(new Move(this));
	}
	
	/** override Mover class shoot(), bullet destructs if blocked
	 * by obstacle and destructs the adversary if blocked by adversary
	 */
	@Override
	public void blocked() {
		if (Engine.Main.grid.getColor(this.getY(), this.getX()).equals(Adversary.myColor)) {
			Main.collisions.add(Main.collisionThrower.new cEvent(this, Engine.Main.thatGuy)); 
		} else {}
			Main.grid.setColor(this.getY(), this.getX(), Main.grid.freeColor);
			Main.gameObjs.remove(this); 
	}

}
