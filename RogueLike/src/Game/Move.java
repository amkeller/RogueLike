package Game;

import Engine.Component;
import Engine.GameObject;

/**
 * This component exists to plug the functionality of moving
 * into the various GameObjects' action logic sequences. To
 * avoid having individual components for each mover type, 
 * functionality that has only slight difference between 
 * moving GameObjects are defined in their game subclasses.
 * @author annette
 *
 */
public class Move extends Component {

	public Move(GameObject object) {
		super(object);
	}

	@Override
	public void graphics() {
	}

	@Override
	public void logic() {
	}
}
