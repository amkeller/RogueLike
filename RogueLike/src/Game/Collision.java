package Game;

import Engine.Component;
import Engine.GameObject;
import Game.Adversary;

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
		private cEvent(GameObject g) {
			this.parent = g;
		}
		public GameObject getParent() { return this.parent; }
		public GameObject getOther() { return this.other; }
		public void setOther(GameObject g) { this.other = g; }
		
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
	
	// only called if a collision event for parent GameObject has been found.
	// removes the parent GameObject from the collision event, and removes the
	// collision event from the list of collisions to be handled if the other
	// GameObject in the collision has already been handled
	public void handleGameObjectCollision() {
		if (Engine.Main.collisions.contains(new cEvent(this.parent))) {
			int i = Engine.Main.collisions.indexOf(this);
			cEvent c = Engine.Main.collisions.get(i);
			// don't remove collision from list unless the other object in the
			// collision has already been handled & removed
			if (this.parent == c.other) {
				c.other = null; // remove other from the event, leaving it in list
				return;
			}
			if ((this.parent == c.parent) && (c.other == null)) {
				 // both colliders handled, can delete the event
				Engine.Main.collisions.remove(i);
				return;
			}
			if ((this.parent == c.parent) && (c.other != null)) {
				// replace with event where other is parent & other field is null
				Engine.Main.collisions.remove(i);
				Engine.Main.collisions.add(new cEvent(c.other));
			}
		}
	}

	@Override
	public void logic() {
		if (Engine.Main.collisions.contains(new cEvent(this.parent))) {
			// removes parent GameObject from collision event
			handleGameObjectCollision(); ;
			if ((this.parent.getClass() == (new Adversary()).getClass()) ||
					(this.parent.getClass() == (new Bullet()).getClass()))
			{
				Engine.Main.dead.add(this.parent);
			}
		}
	}
}
