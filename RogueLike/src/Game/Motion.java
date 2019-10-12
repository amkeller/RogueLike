package Game;

import Engine.Component;
import Engine.GameObject;
import Engine.GridMap;
import Engine.Main;

public class Motion extends Component {
	
	public Motion(GameObject parent) {
		super(parent);
	}
	
	// updates the position of a moving object
	@Override
	public void update() {
		int nextX = parent.getX(), nextY = parent.getY();
		switch (parent.getDirection()) {
		case GridMap.NORTH :
			nextX -= 1;
			break;
		case GridMap.EAST :
			nextY += 1;
			break;
		case GridMap.SOUTH :
			nextX += 1;
			break;
		case GridMap.WEST :
			nextY -= 1;
			break;
		case GridMap.STOP : 
			// no move this round
			return;
		default :
		}

		int lastx, lasty;

		// take a step if there's no obstacle or adversary there & we're in bounds
		if (Main.gameMap.grid.getColor(nextX, nextY).equals(Main.gameMap.freeColor) &&
				Main.gameMap.grid.inBounds(nextX, nextY)) {
			
			// no obstacle, free to move
			lastx = parent.getX();
			lasty = parent.getY();
			parent.setX(nextX);
			parent.setY(nextY);
			Main.gameMap.grid.setColor(this.parent.getX(), this.parent.getY(), this.parent.getColor());

			// color old grid cell free if parent made a move
			if ((lastx != parent.getX() || lasty != parent.getY())) {
				Main.gameMap.grid.setColor(lastx, lasty, Main.gameMap.freeColor); 
			}
			
			// if player, take only one step, so reset direction to STOP here
			if (parent.getName() == "player") parent.setDirection(GridMap.STOP);
			
			return;
		}
		// collision case: bullet with adversary
		else if ((parent.getName() == "bullet" && Main.gameMap.grid.inBounds(nextX, nextY)) &&
			Main.gameMap.grid.getColor(nextX, nextY).equals(Main.adversary.getColor())) { 	
			Main.removeGameObjs.remove(Main.adversary);
			Main.adversary.setColor(Main.gameMap.freeColor);
			Main.removeGameObjs.remove(this.parent);
			this.parent.setColor(Main.gameMap.freeColor);
		}
		// if we  got here we're probably at a grid border or out of bounds
		if (parent.getName() == "bullet") {
			Main.removeGameObjs.add(this.parent);
			this.parent.setColor(Main.gameMap.freeColor);
		}
	}
	
	@Override
	public void render() {
		Main.gameMap.grid.setColor(this.parent.getX(), this.parent.getY(), this.parent.getColor());
		Main.gameMap.grid.repaint();
	}
}
