package Game;


//import java.util.*;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.List;

/**
  The AStarSearch class, along with the AStarNode class,
  implements a generic A* search algorithm. The AStarNode
  class should be subclassed to provide searching capability.
  ref: 
  http://www.peachpit.com/articles/article.aspx?p=101142&seqNum=2
*/
public class Pathfinder<T> {


	/* inner node class
	 * 
	 */
	public abstract class Node implements Comparable {

		  Node predecessor;
		  float costFromStart;
		  float estimatedCostToGoal;


		  public float getCost() {
		    return costFromStart + estimatedCostToGoal;
		  }


		  public int compareTo(Object other) {
		    float thisValue = this.getCost();
		    float otherValue = ((Node)other).getCost();

		    float v = thisValue - otherValue;
		    return (v>0)?1:(v<0)?-1:0; // sign function
		  }


		  /**
		    Gets the cost between this node and the specified
		    adjacent (AKA "neighbor" or "child") node.
		  */
		  public abstract float getCost(Node node);


		  /**
		    Gets the estimated cost between this node and the
		    specified node. The estimated cost should never exceed
		    the true cost. The better the estimate, the more
		    effecient the search.
		  */
		  public abstract float getEstimatedCost(Node node);


		  /**
		    Gets the children (AKA "neighbors" or "adjacent nodes")
		    of this node.
		  */
		  public abstract List getNeighbors();
		}  

  /**
    Construct the path, not including the start node.
  */
  protected List constructPath(Node node) {
    LinkedList path = new LinkedList();
    while (node.predecessor != null) {
      path.addFirst(node);
      node = node.predecessor;
    }
    return path;
  }


  /**
    Find the path from the start node to the end node. A list
    of AStarNodes is returned, or null if the path is not
    found. 
  */
  public List<T> findPath(Node startNode, Node goalNode) {

    PriorityQueue<Node> openList = new PriorityQueue<Node>();
    LinkedList closedList = new LinkedList();

    startNode.costFromStart = 0;
    startNode.estimatedCostToGoal =
      startNode.getEstimatedCost(goalNode);
    startNode.predecessor = null;
    openList.add(startNode);

    while (!openList.isEmpty()) {
      Node node = (Node)openList.poll();
      if (node == goalNode) {
        // construct the path from start to goal
        return constructPath(goalNode);
      }

      List neighbors = node.getNeighbors();
      for (int i=0; i<neighbors.size(); i++) {
        Node neighborNode =
          (Node)neighbors.get(i);
        boolean isOpen = openList.contains(neighborNode);
        boolean isClosed =
          closedList.contains(neighborNode);
        float costFromStart = node.costFromStart +
          node.getCost(neighborNode);

        // check if the neighbor node has not been
        // traversed or if a shorter path to this
        // neighbor node is found.
        if ((!isOpen && !isClosed) ||
          costFromStart < neighborNode.costFromStart)
        {
          neighborNode.predecessor = node;
          neighborNode.costFromStart = costFromStart;
          neighborNode.estimatedCostToGoal =
            neighborNode.getEstimatedCost(goalNode);
          if (isClosed) {
            closedList.remove(neighborNode);
          }
          if (!isOpen) {
            openList.add(neighborNode);
          }
        }
      }
      closedList.add(node);
    }

    // no path found
    return null;
  }

}