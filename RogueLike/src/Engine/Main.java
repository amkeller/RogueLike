package Engine;

import java.util.ArrayList;

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
	// Event lists
	public static final ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
	public static final ArrayList<cEvent> collisions = new ArrayList<cEvent>();
	public static final ArrayList<Integer> keyPresses = new ArrayList<Integer>();
	public static final ArrayList<GameObject> dead = new ArrayList<GameObject>();
	
	public static final int GRIDSCALE = 20;
//	public static final int SPEED = 10;

//	public static GridMap.Grid grid;
	
	// GameObjects
	public static Player player = new Player("thisGuy");
	public static Adversary enemy = new Adversary("thatGuy");
	
	// Events
	public static InputHandler inputHandler;
	public static Collision collisionThrower;
	
	public static enum games { ANT, SHOOTER };
	
	//static GridMap antMap = new GridMap(GRIDSCALE);
	public static GridMap gameMap = new GridMap(GRIDSCALE);

	public static void main(String[] args) throws InterruptedException {
				
		// randomly generates about 10-20% obstacles
		gameMap.init("random");
		gameObjs.add(player);
		gameObjs.add(enemy);

		
		run();
		
		
		/* ------------------------- shooter game stuff ------------------------------- */
		
	}
	
	public static void initialize() {
	}
	
	private static void run() throws InterruptedException {
		
		inputHandler = new InputHandler(gameMap.grid);

		
		gameMap.grid.setFocusable(true);

		
		while (true) {
			
			gameMap.grid.requestFocusInWindow();
			
			update();
		}
	}
	
	private static void update() throws InterruptedException {
		player.direction();
		player.move();
		Thread.sleep(50);
	}
}
