package Engine;

public class Component {
	public GameObject parent = null; 
	public boolean active = true;
	public int Priority = 0;
	
	public Component(GameObject object) {
		parent = object;
	}
	
	public void update() {
	}
	public void render() {
	}
	
}