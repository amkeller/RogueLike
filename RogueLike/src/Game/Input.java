package Game;

import java.awt.event.KeyEvent;

import Engine.GameObject;
import Engine.Component;
import Engine.GridMap;
import Engine.Main;

public class Input extends Component {
	
	public Input(GameObject parent) {
		super(parent);
	}
	
	// gets direction from last keypress or current keypress
	@Override
	public void update() {
		if (Main.keyPresses.isEmpty() != true) {
			int keyCode = Main.keyPresses.remove(0);
			if (keyCode == KeyEvent.VK_W) {
				parent.setDirection(GridMap.NORTH); // up
			}
			else if (keyCode == KeyEvent.VK_A) {
				parent.setDirection(GridMap.WEST); // down
			}
			else if (keyCode == KeyEvent.VK_S) {
				parent.setDirection(GridMap.SOUTH); // south
			}
			else if (keyCode == KeyEvent.VK_D) {
				parent.setDirection(GridMap.EAST); // east
			}
			else if (keyCode == KeyEvent.VK_SPACE) {
				// shoot a bullet
				if (parent.getClass() == (new Player()).getClass()) {
					Bullet b = new Bullet("bullet");
					b.setX(parent.getX());
					b.setY(parent.getY());
					b.setDirection(parent.getDirection());
					Main.gameObjs.add(b);
					Main.gameObjs.add(b);
				}
			}
		}
		else {
			parent.setDirection(-1);
		}
	}
	
	@Override
	public void render() {
		// graphical changes, if any, occur in mMtion component
	}

}
