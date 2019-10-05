package Game;

import Engine.Component;
import Engine.GameObject;

public class Adversary extends GameObject {

	public Adversary() {
		super(null);
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
