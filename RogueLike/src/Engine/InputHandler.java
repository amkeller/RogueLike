package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class InputHandler implements KeyListener {
	
	public InputHandler(Grid g) {
		g.addKeyListener(this);
	}

		@Override
		public void keyTyped(KeyEvent e) {
//			System.out.println(e.toString());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (!Main.keyPresses.contains(keyCode) ) {
				Main.keyPresses.add(keyCode);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			Main.keyPresses.remove(new Integer(keyCode));
		}
	}

