package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * InputHandler class is a KeyListener that takes incoming
 * keypress events and adds them to a static list in Main()
 */
public class InputHandler implements KeyListener {
	
	public InputHandler(GridMap.Grid g) {
		g.addKeyListener(this);
	}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			// add the keypress to the keypress event list in main
			if (!Main.keyPresses.contains(keyCode) ) {
				Main.keyPresses.add(keyCode);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// do we want to do this for shooter game?
			int keyCode = e.getKeyCode();
			Main.keyPresses.remove(new Integer(keyCode));
		}
	}

