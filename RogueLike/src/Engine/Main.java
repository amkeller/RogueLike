package Engine;

import java.awt.event.KeyEvent;

import Game.Player;


public class Main {

	//		Project 2
	//		Matthew Mills
	//		John Mattingly
	//		Annette Keller
	//      Devin Popa
	
	public static Grid grid;
	public static InputHandler l;

	public static final int GRIDSCALE = 20;

	public static void main(String[] args) throws InterruptedException {

		grid = new Grid(GRIDSCALE);
		
		// need to make a Player class
				
		//player.addComponent(new PlayerComponent(player));
		
		
//		Player player = new Player(player);
		
		l = new InputHandler(grid);
		grid.setFocusable(true);

		
		// main game loop
		while (true) {
			grid.requestFocusInWindow();
			
//			grid.add(p);
	
			
			if (!l.list.isEmpty()) {
				
				int keyCode = l.list.remove(0);
				
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