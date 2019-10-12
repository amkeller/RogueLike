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
 		if (Main.pendingKeyPresses.isEmpty() != true) {
			int keyCode = Main.pendingKeyPresses.remove(0);
			if (keyCode == KeyEvent.VK_W) {
				parent.setDirection(GridMap.NORTH); // up
				((Player)parent).setShootDir(GridMap.NORTH);
			}
			else if (keyCode == KeyEvent.VK_A) {
				parent.setDirection(GridMap.WEST); // down
				((Player)parent).setShootDir(GridMap.WEST);
			}
			else if (keyCode == KeyEvent.VK_S) {
				parent.setDirection(GridMap.SOUTH); // south
				((Player)parent).setShootDir(GridMap.SOUTH);
			}
			else if (keyCode == KeyEvent.VK_D) {
				parent.setDirection(GridMap.EAST); // east
				((Player)parent).setShootDir(GridMap.EAST);
			}
			else if (keyCode == KeyEvent.VK_SPACE) {
				// shoot a bullet
				if (parent.getClass() == (new Player()).getClass()) {
					Bullet b = new Bullet("bullet");
					b.setX(parent.getX());
					b.setY(parent.getY());
					b.setDirection(((Player)parent).getShootDir());
					Main.addGameObjs.add(b);
				}
			} 
			else if (keyCode == KeyEvent.VK_H) {
				Main.Hpressed = !(Main.Hpressed);
			}
		}
		else {
			parent.setDirection(GridMap.STOP);
		}
	}
	
	@Override
	public void render() {
		// graphical changes, if any, occur in mMtion component
	}

}