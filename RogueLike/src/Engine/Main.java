package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Game.Player;
import Game.Collision.cEvent;	// collision event

public class Main {

	//		Project 2
	//		Matthew Mills
	//		John Mattingly
	//		Annette Keller
	//      Devin Popa
	
	public static Grid grid;
	public static InputHandler inputHandler;

	public static final int GRIDSCALE = 20;
	
	// GameObject & event lists
	public static final ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
	public static final ArrayList<cEvent> collisions = new ArrayList<cEvent>();
	public static final ArrayList<Integer> keyPresses = new ArrayList<Integer>();
	public static final ArrayList<GameObject> dead = new ArrayList<GameObject>();

	public static void main(String[] args) throws InterruptedException {

		grid = new Grid(GRIDSCALE);						
		
		Player player = new Player("player");
		
		inputHandler = new InputHandler(grid);
		grid.setFocusable(true);

		
		// main game loop
		while (true) {
			grid.requestFocusInWindow();
			
//			grid.add(p);
	
			
			if (!keyPresses.isEmpty()) {
				
				int keyCode = keyPresses.remove(0);
				
				if (keyCode == KeyEvent.VK_W) {
//					if (grid.inBounds(cursor[0] - 1, cursor[1]) == true) {
//						grid.setColor(cursor[0], cursor[1], Color.WHITE);
//						cursor[0] = cursor[0] - 1;
//					}
				}
				
				else if (keyCode == KeyEvent.VK_DOWN) {
//					if (grid.inBounds(cursor[0] + 1, cursor[1]) == true) {
//						grid.setColor(cursor[0], cursor[1], Color.WHITE);
//						cursor[0] = cursor[0] + 1;
//					}
				}
				
				else if (keyCode == KeyEvent.VK_LEFT) {
//					if (grid.inBounds(cursor[0], cursor[1] - 1) == true) {
//						grid.setColor(cursor[0], cursor[1], Color.WHITE);
//						cursor[1] = cursor[1] - 1;
//					}
				}
				
				else if (keyCode == KeyEvent.VK_RIGHT) {
//					if (grid.inBounds(cursor[0], cursor[1] + 1) == true) {
//						grid.setColor(cursor[0], cursor[1], Color.WHITE);
//						cursor[1] = cursor[1] + 1;	
//					}
				}
				
				else if (keyCode == KeyEvent.VK_P) {
//					while (true) {
//						grid.setColor(cursor[0], cursor[1], Color.RED);
//		
//						if (!l.list.isEmpty()) {
//							
//							keyCode = l.list.remove(0);
//							
//							if (keyCode == KeyEvent.VK_UP) {
//								if (grid.inBounds(cursor[0] - 1, cursor[1]) == true) {
//									grid.setColor(cursor[0], cursor[1], Color.WHITE);
//									cursor[0] = cursor[0] - 1;
//								}
//							}
//							
//							else if (keyCode == KeyEvent.VK_DOWN) {
//								if (grid.inBounds(cursor[0] + 1, cursor[1]) == true) {
//									grid.setColor(cursor[0], cursor[1], Color.WHITE);
//									cursor[0] = cursor[0] + 1;
//								}
//							}
//							
//							else if (keyCode == KeyEvent.VK_LEFT) {
//								if (grid.inBounds(cursor[0], cursor[1] - 1) == true) {
//									grid.setColor(cursor[0], cursor[1], Color.WHITE);
//									cursor[1] = cursor[1] - 1;
//								}
//							}
//							
//							else if (keyCode == KeyEvent.VK_RIGHT) {
//								if (grid.inBounds(cursor[0], cursor[1] + 1) == true) {
//									grid.setColor(cursor[0], cursor[1], Color.WHITE);
//									cursor[1] = cursor[1] + 1;				
//								}
//							}
//							
//							else if (keyCode == KeyEvent.VK_SPACE) {
////								System.out.println("yeet");
//								if (grid.inBounds(cursor[0], cursor[1]) == true) {
//									
//									// check to see if cell is alive, then toggle the opposite
//									if (grid.isAlive[cursor[0]][cursor[1]] == true) {
//										grid.isAlive[cursor[0]][cursor[1]] = false;
//									}
//									
//									// check to see if cell is dead, then toggle the opposite
//									if (grid.isAlive[cursor[0]][cursor[1]] == false) {
//										grid.isAlive[cursor[0]][cursor[1]] = true;
//									}
//								}
//									
//							}
							
//							else if (keyCode == KeyEvent.VK_P) {
//								break;								
//							}
//						}
//						
//					}
				}
				
			}
			
			Thread.sleep(100);

			grid.nextStep();
		}


	}
}
