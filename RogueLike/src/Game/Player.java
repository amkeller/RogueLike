package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Engine.Component;
import Engine.GameObject;
import Engine.Main;

public class Player extends GameObject {

	public Player(String name) {
		super(name);
	}

	public void graphics() {
		Main.grid.setColor(this.getX(), this.getY(), Color.RED); 
	}

	public void logic() {
		if (Main.l.list.isEmpty() != true) {

			int keyCode = Main.l.list.remove(0);

			if (keyCode == KeyEvent.VK_W) {
				//				
			}
			else if (keyCode == KeyEvent.VK_A) {

			}
			else if (keyCode == KeyEvent.VK_S) {

			}
			else if (keyCode == KeyEvent.VK_D) {

			}


		}
	}









}
