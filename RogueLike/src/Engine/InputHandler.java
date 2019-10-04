package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class InputHandler implements KeyListener {
	
	public InputHandler(Grid g) {
		g.addKeyListener(this);
	}
	
	public ArrayList<Integer> list = new ArrayList<Integer>();


		@Override
		public void keyTyped(KeyEvent e) {
//			System.out.println(e.toString());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (!list.contains(keyCode) ) {
				list.add(keyCode);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			list.remove(new Integer(keyCode));
		}
	}

