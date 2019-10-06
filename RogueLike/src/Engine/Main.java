package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;

import Game.Player;
import Game.Adversary;
import Game.Collision;
import Game.Collision.cEvent;	// collision event
import Engine.GridMap;

	//		Project 2
	//		Matthew Mills
	//		John Mattingly
	//		Annette Keller
	//      Devin Popa

public class Main {
	public static GridMap.Grid grid;
	
	// GameObjects
	public static Player thisGuy = new Player("thisGuy");
	public static Adversary thatGuy = new Adversary("thatGuy");
	
	// Events
	public static InputHandler inputHandler;
	public static Collision collisionThrower;
	
	
	// Event lists
	public static final ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
	public static final ArrayList<cEvent> collisions = new ArrayList<cEvent>();
	public static final ArrayList<Integer> keyPresses = new ArrayList<Integer>();
	public static final ArrayList<GameObject> dead = new ArrayList<GameObject>();
	
	public static final int GRIDSCALE = 20;
	public static final int SPEED = 10;
	
	public static enum games { ANT, SHOOTER };
	
	//static GridMap antMap = new GridMap(GRIDSCALE);
	static GridMap gameMap = new GridMap(GRIDSCALE);

	public static void main(String[] args) throws InterruptedException {
				
		// randomly generates about 10-20% obstacles
		gameMap.init("random");
		
		/* ------------------------- shooter game stuff ------------------------------- */
		
	}
	
	public static void initialize() {
	}
	
	private void run() {
	}
	
	private void update() {
	}
}
