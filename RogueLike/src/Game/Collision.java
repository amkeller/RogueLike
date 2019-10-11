package Game;

import Engine.Component;
import Engine.GameObject;
import Engine.Main;


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
		
		public cEvent(GameObject o1, GameObject o2) {
			this.parent = o1;
			this.other = o2;
		}

		// needed to test whether parent is a member of a cEvent in a collisions list
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
	
	// helper function for update()
	// Called if a collision event for parent GameObject found in list.
	// removes the parent GameObject from the collision event, and removes the
	// collision event from the list of collisions to be handled if the other
	// GameObject in the collision has already been handled & removed
	public void manageCollisionsList() {
		if (Main.collisions.contains(new cEvent(this.parent))) {
			int i = Main.collisions.indexOf(new cEvent(this.parent));
			cEvent c = Main.collisions.get(i);
			// other collision object hasn't already been handled & removed
			if (this.parent == c.other) {
				c.other = null; // remove parent, leave unhandled other
				return;
			}
			 // both colliders have been handled, can delete the event
			if ((this.parent == c.parent) && (c.other == null)) {
				Main.collisions.remove(i);
				return;
			}
			// don't remove unhandled other from the collision event
			if ((this.parent == c.parent) && (c.other != null)) {
				// replace with event where other is parent & other field is null
				Main.collisions.remove(i);
				Main.collisions.add(new cEvent(c.other));
			}
		}
	}

	@Override
	public void update() {
		// check if a parent-related cEvent is in the list
		if (Main.collisions.contains(new cEvent(this.parent))) {
			// remove parent from collision event
			manageCollisionsList();
			// process parent's collision, currently only the adversary needs this
			if (this.parent.getName() == "adversary") {
				Main.gameObjs.remove(this.parent);
				this.parent.setColor(Main.gameMap.freeColor);
				Main.adversary = null;
			}
			// we're not actually generating bullet collision events right now
			if (this.parent.getName() == "bullet") {
				Main.gameObjs.remove(this.parent);
				this.parent.setColor(Main.gameMap.freeColor);
			}
		}
	}
	
	@Override
	public void render() {
		Main.gameMap.grid.setColor(parent.getX(), parent.getY(), this.parent.getColor());
	}
}
