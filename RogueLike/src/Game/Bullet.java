package Game;

import Engine.Component;
import Engine.GameObject;
import Engine.GridMap;
import Engine.Main;
import Game.Collision.cEvent;

// the functionality for bullets are in Mover, except for 
// the functionality of destructing when blocked by obstacle
// or putting adversary into Main.dead list if blocked by
// adversary
public class Bullet extends Mover {
	
	static int bulletID = 0;
	int id;

	public Bullet() {
		super(null);  		// bullets need no name
		this.id = bulletID;
		bulletID++;
		this.addComponent(new Move(this));
	}
	
	/** override Mover class shoot(), bullet destructs if blocked
	 * by obstacle and destructs the adversary if blocked by adversary
	 */
	@Override
	public void blocked() {
		if (Engine.Main.grid.getColor(this.getY(), this.getX()).equals(Adversary.myColor)) {
			// TODO put the static final Adversary object into collisions list
			// Main.collisions.add(new cEvent(this, ref_to_adversary)); 
		} else {}
			// TODO: figure out how to throw collision events from Collision Handler?
			/* add(new Engine.Main.collisionThrower.cEvent(this)) to Main.collision list; 
			 * // add self to dead list
			 */
	}

}
