package Engine;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GridMap.Grid;

public abstract class GameObject {
	private static int idCount = 0;
	private int id;
	private String name = null;
	private int posX = 0;
	private int posY = 0; 
	private int direction=GridMap.STOP;
	public Color myColor = null; // objects start off invisible
	private ArrayList<Component> coms = new ArrayList<Component>();
	
	public GameObject(String namein) {
		this.name = namein;
		this.id = idCount;
		idCount++;
		// master list of game objects in game
		// Engine.Main.gameObjs.add(this);
	}
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	public int getX() { return this.posX; }
	public int getY() { return this.posY; }
	public int getDirection() { return this.direction; };
	public void setX(int x) { this.posX = x; }
	public void setY(int y) { this.posY = y; }
	public void setDirection(int dir) { this.direction = dir; };
	public int getId() { return this.id; }
	public Color getColor() { return this.myColor; }
	public void setColor(Color cin)  { this.myColor = cin; }
 	
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
	
	// Unique id is a super easy way to identify GameObjects in lists
	public boolean equals(Object o) {
		if (this == o) return true;
		if ((o == null) || !(o instanceof GameObject)) return false;
		GameObject g = (GameObject) o;
		if (this.id == g.getId()) return true;
		return false;
	}
}
