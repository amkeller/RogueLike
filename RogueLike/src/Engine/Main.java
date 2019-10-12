package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Game.Player;
import Game.Adversary;
import Game.Input;
import Game.Motion;
import Game.Pathfinder;
import Engine.GridMap;

	//		Project 2
	//		Matthew Mills
	//		John Mattingly
	//		Annette Keller
	//      Devin Popa

public class Main {
	// Event lists
	public static final ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
	public static final ArrayList<Integer> keyPresses = new ArrayList<Integer>();
	public static ArrayList<Integer> pendingKeyPresses = new ArrayList<Integer>();
	public static final ArrayList<GameObject> addGameObjs = new ArrayList<GameObject>();
	public static final ArrayList<GameObject> removeGameObjs = new ArrayList<GameObject>();
	
	public static final int GRIDSCALE = 20;
	
	public static GridMap gameMap = new GridMap(GRIDSCALE);
	
	// GameObjects
	public static Player player = new Player("player");
	public static Adversary adversary = new Adversary("adversary");
	public static boolean Hpressed = false;
	
	// Events
	public static InputHandler inputHandler = new InputHandler(gameMap.grid);
	
	public static enum games { ANT, SHOOTER };
	

	public static void main(String[] args) throws InterruptedException {

		// randomly generates about 10-20% obstacles
		gameMap.init("random", gameObjs);
		gameObjs.add(player);
		gameObjs.add(adversary);
		
		
		// Add Component here----------------------------------------------------------
		
		
		
		
		// ----------------------------------------------------------------------------
		
		
		run();
		
		/* ------------------------- shooter game stuff ------------------------------- */
		
	}
	
	public static void initialize() {
		player.setX(gameMap.scaleH-1);
		player.setY(0);
		adversary.setX(0);
		adversary.setY(gameMap.scaleW-1);
	}
	

	private static void processInputs() {
		// iterate thru gameObjs list & for every gameObj that contains an Input component,  
		// execute update() & render()
		for (GameObject go : gameObjs) {
			for (Component c : go.components) {
				if (c.getClass() == Input.class) {
					c.update();
					c.render();
				}
			}
		}
	}
	
	private static void processMotion() {
		// iterate thru gameObjs list & for every gameObj that contains a Motion component,  
		// execute update() & render()
  		for (GameObject go : gameObjs) {
  			for (Component c : go.components) {
				if (c.getClass() == Motion.class) {
					c.update();
					c.render();
				}
			}
		}
	}
	
	private static void processPath() {
		// iterate thru gameObjs list & for every gameObj that contains a Pathfinder component,  
		//  execute update() & render()
		for (GameObject go : gameObjs) {
			for (Component c : go.components) {
				if (c.getClass() == Pathfinder.class) {
					c.update();
					c.render();
				}
			}
		}
	}
	
	private static void processGameObjects() {
		for (GameObject go : addGameObjs) {
			for (Component c : go.components) {
				gameObjs.add(go);
			}
		}
		for (GameObject go : removeGameObjs) {
			for (Component c : go.components) {
				gameObjs.remove(go);
			}
		}
	}

	
	
	private static void run() throws InterruptedException {
		
		initialize();
		gameMap.grid.setFocusable(true);
		
		while (true) {
			/*
			processInputs();
			processGameObjects();
			processMotion();
			processGameObjects();
			processCollisions();
			processGameObjects();
			*/
			
			for (GameObject gO : gameObjs) {
				if (gO.getName() == "adversary") {
					if (Hpressed ==  false) {
						gO.update();
					}
				} 
				
				else if (gO.getName() != "obstacle") {
					gO.update();
				}
			}
			processGameObjects();
			for (GameObject gO : gameObjs) {		
//				if (gO.getName() == "adversary") {
//					if (Hpressed == false) {
//						gO.update();
//					}
//				}
//					
//				else {
					gO.render();
//				}
			}
			
			// stuff for redrawing grid & etc needs to go here
			gameMap.grid.repaint();
 			gameMap.grid.requestFocusInWindow();
			Thread.sleep(100);
		}
		
	}
	

}


	
	
	
	