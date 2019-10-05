package Game;

import Engine.Component;
import Engine.GameObject;
import Game.Adversary;
import Game.Player;
import Game.Bullet;
import Game.Obstacle;

/**
 * Collisions enable the game to act upon collision events by populating a static
 * list in the game with collision events. The Collision event objects contain names 
 * or ids of the objects that collide. Upon each iteration of a game loop, colliders 
 * that are identified by collision events in the list update themselves according
 * to whatever logic is associated with their being part of a collision.
 */
public class Collision extends Component {
	
	public class cEvent {
		private final GameObject parent; 	// collider 1
		private GameObject other;  			// collider 2
		
		// event to throw
		public cEvent(GameObject g) {
			this.parent = g;
		}
		
		/*
		 * This .equals enables an object to test whether its parent is a member of 
		 * a cEvent in a collection of cEvents. 
		 */
		@Override
		public boolean equals(Object other) {
			if (this == other) return true;
			if ((other == null) || !(other instanceof cEvent)) return false;
			cEvent c = (cEvent)other;
			// parent of this component matches the parent GameObject that created 
			// the other component or it's the other GameObject in the collision
			if (this.parent.equals(c.parent) || this.parent.equals(c.other))
				return true;
			return false;
		}
	}
	
	public Collision(GameObject parent) {
		super(parent);
	}
	
	// Only called if a collision event for parent GameObject found in list.
	// removes the parent GameObject from the collision event, and removes the
	// collision event from the list of collisions to be handled if the other
	// GameObject in the collision has already been handled & removed
	public void handleGameObjectCollision() {
		if (Engine.Main.collisions.contains(new cEvent(this.parent))) {
			int i = Engine.Main.collisions.indexOf(this);
			cEvent c = Engine.Main.collisions.get(i);
			// leave collision event in list if the other object in the
			// collision hasn't already been handled & removed
			if (this.parent == c.other) {
				c.other = null; // remove parent, leave unhandled other
				return;
			}
			 // both colliders have been handled, can delete the event
			if ((this.parent == c.parent) && (c.other == null)) {
				Engine.Main.collisions.remove(i);
				return;
			}
			// don't remove unhandled other from the collision event
			if ((this.parent == c.parent) && (c.other != null)) {
				// replace with event where other is parent & other field is null
				Engine.Main.collisions.remove(i);
				Engine.Main.collisions.add(new cEvent(c.other));
			}
		}
	}

	@Override
	public void logic() {
		if ((this.parent.getClass() == (new Bullet()).getClass())) {
			// process bullet colliding with obstacle or adversary.
			// add to event list if necessary
			// other collisions are ignored
		}
		// collisions are either ignored or result in death (bullet, adversary)
		if (Engine.Main.collisions.contains(new cEvent(this.parent))) {
			// remove parent from collision event
			handleGameObjectCollision(); ;
			if ((this.parent.getClass() == (new Adversary()).getClass()) ||
					(this.parent.getClass() == (new Bullet()).getClass()))
			{
				// TODO logic: look in GameObjects list, remove parent from it
				Engine.Main.gameObjs.remove(this.parent);
				Engine.Main.dead.add(this.parent);
			}
		}
	}
	
	@Override
	public void graphics() {
		if (Engine.Main.dead.contains(this.parent)) {
			// TODO remove it from grid display
			// TODO then remove it from the dead list
		} else {
			// no collision graphics, nothing to do here
		}
	}
}
