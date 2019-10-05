package Engine;

import java.util.ArrayList;

public abstract class GameObject {
	private String name = null;
	private int posX = 0;
	private int posY = 0; 
	private ArrayList<Component> coms = new ArrayList<Component>();
	
	public GameObject(String namein) {
		this.name = namein;
		// master list of game objects in game
		Engine.Main.gameObjs.add(this); 
	}
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	public int getX() { return this.posX; }
	public int getY() { return this.posY; }
	public void setX(int x) { this.posX = x; }
	public void setY(int y) { this.posY = y; }
 	
	public void addComponent(Component component) {
		if (!coms.contains(component)) {
			coms.add(component);
		}
	}
	
	public boolean removeComponent(Class c) {
		for (Component co : coms) {
			if (c == co.getClass()) {
				coms.remove(co);
			}
		}
		return true;
	}
	
	public Component getComponent(Class c) {
		for (Component co : coms) {
			if (c == co.getClass()) {
				return co;
			}
		}
		return null;
	}
}
