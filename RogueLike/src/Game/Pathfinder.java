package Game;


import java.util.PriorityQueue;
import java.util.Random;

import Engine.Component;
import Engine.GameObject;
import Engine.Main;
import Game.Pathfinder.AStar.Cell;

import java.awt.Color;
import java.util.ArrayList;

public class Pathfinder extends Component {

	public static ArrayList<Cell> path = new ArrayList<Cell>();
	public static final int MARGINS = Main.gameMap.grid.MARGIN_SIZE;
	public static final int HEIGHT = Main.GRIDSCALE;
	public static final int WIDTH = Main.GRIDSCALE*2;

	public Pathfinder(GameObject parent) {
		super(parent);
	}


	public class AStar {
		public static final int DIAGONAL_COST = 11;
		public static final int V_H_COST = 10;

		Cell [][] grid = new Cell[HEIGHT][WIDTH];

		PriorityQueue<Cell> open = new PriorityQueue<Cell>((Object o1, Object o2) -> {
			Cell c1 = (Cell)o1;
			Cell c2 = (Cell)o2;

			return c1.finalCost<c2.finalCost?-1:
				c1.finalCost>c2.finalCost?1:0;
		});

		boolean closed[][] = new boolean[HEIGHT][WIDTH];

		int startRow, startCol;
		int endRow, endCol;

		class Cell{  
			int heuristicCost = 0; //Heuristic cost
			int finalCost = 0; //G+H
			int r, c;
			Cell parent;

			Cell(int i, int j){
				this.r = i;
				this.c = j;
			}

			@Override
			public String toString(){
				return "["+this.r+", "+this.c+"]";
			}
		}



		public void setBlocked(int i, int j){
			grid[i][j] = null;
		}

		public void setStartCell(int i, int j){
			startRow = i;
			startCol = j;
			parent = null;
		}

		public void setEndCell(int i, int j){
			endRow = i;
			endCol = j;
		}

		void checkAndUpdateCost(Cell current, Cell t, int cost){
			if(t == null || closed[t.r][t.c])return;
			int t_final_cost = t.heuristicCost+cost;

			boolean inOpen = open.contains(t);
			if(!inOpen || t_final_cost<t.finalCost){
				t.finalCost = t_final_cost;
				t.parent = current;
				if(!inOpen)open.add(t);
			}
		}

		public AStar(int startX, int startY, int endX, int endY){

			startRow = startX;
			startCol = startY;

			endRow = endX;
			endCol = endY;

			// add the start location to open list.
			open.add(new Cell(startX, startY));

			// adds heuristicCost to each cell in the grid
			for(int i = 0; i < HEIGHT; ++i){
				for(int j = 0; j < WIDTH; ++j){
					grid[i][j] = new Cell(i, j);
					grid[i][j].heuristicCost = Math.abs(i-endRow)+Math.abs(j-endCol);
					//                  System.out.print(grid[i][j].heuristicCost+" ");
				}
				//              System.out.println();
			}

			// add obstacles to the astar
			for (int row = 0; row < HEIGHT; row++) {
				for (int col = 0; col < WIDTH; col++) {
					if (Main.gameMap.grid.getColor(row, col).equals(Color.green)) {
						setBlocked(row, col);
					}
				}
			}
		}



		public ArrayList<Cell> findPath() {
			Cell current;
			//path = new ArrayList<Cell>();

			while(true) {

				current = open.poll();
				if(current == null) {
					break;
				}
				closed[current.r][current.c] = true;

				Cell t;  
				if(current.r-1>=0){
					t = grid[current.r-1][current.c];
					checkAndUpdateCost(current, t, current.finalCost+V_H_COST);

					if(current.c-1>=0){                      
						t = grid[current.r-1][current.c-1];
						checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
					}

					if(current.c+1<grid[0].length){
						t = grid[current.r-1][current.c+1];
						checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
					}
				}

				if(current.c-1>=0){
					t = grid[current.r][current.c-1];
					checkAndUpdateCost(current, t, current.finalCost+V_H_COST);
				}

				if(current.c+1<grid[0].length){
					t = grid[current.r][current.c+1];
					checkAndUpdateCost(current, t, current.finalCost+V_H_COST);
				}

				if(current.r+1<grid.length){
					t = grid[current.r+1][current.c];
					checkAndUpdateCost(current, t, current.finalCost+V_H_COST);

					if(current.c-1>=0){
						t = grid[current.r+1][current.c-1];
						checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
					}

					if(current.c+1<grid[0].length){
						t = grid[current.r+1][current.c+1];
						checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
					}  
				}
			}


			// return the correct path in the right order
			if (closed[endRow][endCol]) {
				Cell curr = grid[endRow][endCol];
				while (curr.r != startRow && curr.c != startCol) {
					path.add(0, curr);
					curr = curr.parent;
				}
				return path;
			}
			else {
				return null;
			}
		}


	}

	public void update() {

		// if it is at start / needs a path, activate A*
		if (path.size() == 0) {
			Random rand = new Random();
			int endX, endY;

			// keep finding a potential end destination if it is not free
			do {
				endX = rand.nextInt(Main.gameMap.grid.getHt());
				endY = rand.nextInt(Main.gameMap.grid.getWd());
			} while (Main.gameMap.grid.getColor(endX, endY).equals(Color.green));

//			System.out.println(endX + " , " + endY);

			AStar a = new AStar(parent.getX(), parent.getY(), endX, endY);

			path = a.findPath();

			if(path.size() == 0) {
				return;
			}

			Cell nextStep = path.remove(0);
			Main.gameMap.grid.setColor(parent.getX(), parent.getY(), Main.gameMap.freeColor);

			// move adversary to next step of the path
			parent.setX(nextStep.r);
			parent.setY(nextStep.c);
		}

		// if path is already in existence
		else {
			Cell nextStep = path.remove(0);
			//Color prev = Main.gameMap.grid.color;
			Main.gameMap.grid.setColor(parent.getX(), parent.getY(), Main.gameMap.freeColor);

			parent.setX(nextStep.r);
			parent.setY(nextStep.c);
		}
	}

	public void render() {
		// rendering will be handled via motion
	}

}