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

		// take a step if there's no obstacle or adversary there
		if (Main.gameMap.grid.getColor(nextX, nextY).equals(Main.gameMap.freeColor) &&
				Main.gameMap.grid.inBounds(nextX, nextY)) {
						
			// no obstacle, free to move
			lastx = parent.getX();
			lasty = parent.getY();
			parent.setX(nextX);
			parent.setY(nextY);
			
			// grid color change associated with a move
			Main.gameMap.grid.setColor(lastx, lasty, Main.gameMap.freeColor); 
			
			// if player, take only one step, so reset direction to STOP here
			if (parent.getName() == "player") parent.setDirection(GridMap.STOP);
		}
		else {			
			// get other GameObject in the collision
			GameObject other = null;
			for (GameObject go : Engine.Main.gameObjs) {
				if (go.getX() == nextX && go.getY() == nextY) {
					other = go;
					break;
				}
			}
			if (other != null) {  // sanity check
				if (parent.getName() == "bullet") {
					
				    if (other.getName() == "adversary") {
					   Main.collisions.add(Engine.Main.collisionThrower.new cEvent(other));
				    }
				    // remove bullet from the game if if hits anything
				    Main.gameObjs.remove(this.parent);
				    this.parent.setColor(Main.gameMap.freeColor);
				 
				}
			}		
		}
	}
	
	@Override
	public void render() {
		Main.gameMap.grid.setColor(parent.getX(), parent.getY(), this.parent.getColor());
	}

}
