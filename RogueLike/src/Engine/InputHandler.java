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

	public void  updatePending() {
		ArrayList<Integer> temp1 = new ArrayList<Integer>(Main.pendingKeyPresses);
		for (Integer  i : Main.pendingKeyPresses) {
			if (!(Main.keyPresses.contains(i)) ) {
				temp1.remove(i);
			}
		}
		Main.pendingKeyPresses = temp1;
		ArrayList<Integer> temp2 = new ArrayList<Integer>(temp1);
		for (Integer  i : Main.keyPresses) {
			if (!(Main.pendingKeyPresses.contains(i))) {
				temp2.add(i);
			}
		}
		Main.pendingKeyPresses = temp2;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keystroke: " + e.getKeyCode());
		int keyCode = e.getKeyCode();
		// add the keypress to the keypress event list in main
		if (!Main.keyPresses.contains(keyCode) ) {
			Main.keyPresses.add(keyCode);
			Main.pendingKeyPresses.add(keyCode);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		Main.keyPresses.remove(new Integer(keyCode));
	}
}

