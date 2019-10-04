package Game;

import Engine.Component;
import Engine.GameObject;

public class Bullet extends GameObject {

	public Bullet() {
		super(null);  // bullets need no name
	}

	@Override
	public void addComponent(Component component) {
		
	}

	@Override
	public boolean removeComponent(Class Object) {
		return false;
	}

	@Override
	public Component getComponent(Class Object) {
		return null;
	}

}
