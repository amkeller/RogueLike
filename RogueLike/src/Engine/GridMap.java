package Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.io.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


/* Sina's Grid with some additional GridMap functionality below */
public class GridMap {
	
	/**
	 * -------------------------------------------------------------------------
	 *      Sina's Grid.java class is the inner class for GameMap
	 * -------------------------------------------------------------------------
	 */
	public class Grid extends JPanel {
		
		protected static final long serialVersionUID = 0L;
		protected static final int MARGIN_SIZE = 5;
		protected int scaleH;
		protected int scaleW;
		protected Color[][] colors;
		public final Color freeColor = Color.WHITE;
		private static final int SQUARE_SIZE = 15;  // pixel size of each square
		private JFrame frame;
		
		protected Grid() {
		}
		
		public Grid(int scale) {
			this.scaleH = scale; // has to match grid's scaleH
			this.scaleW = 2*scale; // has to match grid's scaleW
			int overallSizeH = scaleH + 2 * MARGIN_SIZE;
			int overallSizeW = scaleW + 2 * MARGIN_SIZE;
			colors = new Color[overallSizeH][overallSizeW];
			for (int i = 0; i < overallSizeH; i++)
				for (int j = 0; j < overallSizeW; j++)
					colors[i][j] = Color.LIGHT_GRAY;
			setGridColor(this.freeColor); // the color of an empty space
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					createAndShowFrame();
				}
			});	
		}
		
		public int getScale(){
			return scaleH;
		}
		
		public int getHt() {
			return scaleH;
		}
		
		public int getWd(){
			return scaleW;
		}
		
		// added amk 09/02/2019
		public int[] getCenter() {
			return new int [] {
					(scaleW)/2, // x coordinate of center
					(scaleH)/2  // y coordinate of center
					};
		}
		
		private void createAndShowFrame() {
			frame = new JFrame("Drawing Grid");
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setSize((scaleW + 2 * MARGIN_SIZE) * SQUARE_SIZE, 
					((scaleH) + 2 * MARGIN_SIZE) * SQUARE_SIZE);
			frame.setContentPane(this);
			frame.pack();
			frame.setVisible(true);
			frame.toFront();
			frame.setAlwaysOnTop(true);
		}
		
		public Color getColor(int row, int col) {
			return colors[row + MARGIN_SIZE][col + MARGIN_SIZE];
		}
		
		public void setColor(int row, int col, Color color) {
			colors[row + MARGIN_SIZE][col + MARGIN_SIZE] = color;
			this.repaint();
		}
		
		// set the color matrix for cells of the navigable grid to white
		public void setGridColor(Color color_in) {
			for (int i = 0; i < scaleH; i++) {
				for (int j = 0; j < scaleW; j++) {
					setColor(i, j, color_in);
				}
			}
		}
		
		public void kill() {
			if (frame != null)
				frame.dispose();
		}
		
		public Dimension getPreferredSize() {
			return new Dimension((scaleW + 2 * MARGIN_SIZE) * SQUARE_SIZE + 1, 
					((scaleH) + 2 * MARGIN_SIZE) * SQUARE_SIZE + 1);
		}
		
		public void paint(Graphics g) {
			super.paint(g);
		
			int offset = MARGIN_SIZE * SQUARE_SIZE;
			
			for (int i = 0; i < scaleH + 2 * MARGIN_SIZE; i ++)
				for (int j = 0; j < scaleW + 2 * MARGIN_SIZE; j++) {
					g.setColor(colors[i][j]);
					g.fillRect(j * SQUARE_SIZE + 1, i * SQUARE_SIZE + 1, 
							SQUARE_SIZE, SQUARE_SIZE);
				}
			g.setColor(Color.BLACK);
			for (int i = 0; i < scaleW + 1; i++) 
				g.drawLine(offset + i * SQUARE_SIZE, offset, 
						offset + i * SQUARE_SIZE, offset + scaleH * SQUARE_SIZE);
			for (int j = 0; j < scaleH + 1; j++) 
				g.drawLine(offset, offset + j * SQUARE_SIZE, 
						offset + scaleW * SQUARE_SIZE, offset + j * SQUARE_SIZE);
		}
		
	}
	/**
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------------------
	 */	
	
	// constants for direction switches
	// directions aligned to left handed Cartesiaon coordinates
	public static final int NORTH = 270; 
	public static final int EAST = 0;
	public static final int SOUTH = 90;
	public static final int WEST = 180;
	public static final int COMPASS = 360;
		
	public Grid grid = new Grid(Main.GRIDSCALE); // public for now 

	// test data for development
	private int[][] testdata1 = {
		    {1,	2,	2,	3,	3,	2,	2,	2,	2,	2},
			{1,	3,	2,	3,	3,	2,	2,	2,	2,	2},
			{1,	3,	0,	0,	3,	2,	2,	1,	1,	2},
			{1,	1,	1,	1,	1,	1,	1,	1,	1,	2},
			{2,	3,	0,	0,	1,	2,	2,	1,	1,	2},
			{3,	3,	2,	2,	1,	2,	2,	1,	1,	2},
			{0,	2,	2,	0,	1,	2,	2,	2,	2,	2},
			{0,	2,	2,	0,	1,	2,	2,	2,	2,	2},
			{0,	2,	2,	0,	1,	2,	2,	2,	2,	2},
			{0,	2,	2,	0,	1,	2,	2,	2,	2,	2} };
	
	// test data for development
	private int[][] testdata2 = {
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
		    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0}
		    };
	
	// set testdata to testdata2 for white game map
	private int[][] testdata = testdata1;
			
	protected static final long serialVersionUID = 0L;
	protected static final int MARGIN_SIZE = 5;
	protected int scaleH;
	protected int scaleW;
	protected int[][] terrain;  // map of landscape: water, trees, grass
	
	public GridMap(int scale) {
		this.scaleH = scale;
		this.scaleW = 2*scale;
		int overallSizeH = scaleH + 2 * MARGIN_SIZE;
		int overallSizeW = scaleW + 2 * MARGIN_SIZE;
		this.terrain = new int[overallSizeH][overallSizeW];
		for (int i = 0; i < overallSizeH; i++)
			for (int j = 0; j < overallSizeW; j++) {
				this.terrain[i][j] = Integer.MAX_VALUE;
			}
	}
	
	// right now just load the 1 map & use graph traversal weights for terrain too
	public void init(String filePath) {
		int[][] data = new int[scaleH][scaleW];
		if (filePath.equals("random")) {
			for (int i = 0; i < scaleH; i++) {
			    for (int j = 0; j < scaleW; j++) {
			        data[i][j] = (int)(Math.ceil(10*Math.random())/8);
			    }
			}			
		} else {
			data = readFile(filePath);
		}
		setMap(this.terrain, data); // set up the terrain map
		colorTheGrid(); // color the Grid/Jpanel
	}
	
	// this sets a map's playing region to the input array's values
	public void setMap(int[][] map, int[][] data_in) {
		for (int row = 0; row < scaleH; row++) {
			for (int col = 0; col < scaleW; col++) {
				map[row][col] = 
						data_in[row][col];
			}
		}
	}
		
	// this gets the map value for the selected cell
	public int getCell(int[][] map, int row, int col) {
		return map[row + MARGIN_SIZE][col + MARGIN_SIZE];
	}

	public int[][] readFile(String filePath) {
	    if (filePath == null) return testdata;;

		int index = 0;
		String[] terrain = new String[10];
		int data[][] = new int[10][10];
		try {
			BufferedReader csvReader = 
					new BufferedReader(new FileReader(filePath));
			try {
				String row = csvReader.readLine();
				while (row != null) {
					terrain[index] = row;
					index++;
					row = csvReader.readLine();
				}
				csvReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("Buffered Reader failed at "+ filePath);
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		for (int i = 0; i < terrain.length; i++) {
			String[] nums = terrain[i].split(",");
			for (int j = 0; j < nums.length; j++) {
				data[i][j] = Integer.parseInt(nums[j]);
				System.out.print(nums[j] + "\t");
			}
			System.out.print("\n");
		}
		return data;
		//return terrain;
	}

	
	// ************ For JPanel Grid ****************
	
	public void colorTheGrid() {
		Color color = Color.white;
		for (int row = 0; row < scaleH; row++) {
			for (int col = 0; col < scaleW; col++) {
				switch (this.terrain[row][col]) {
				case 0: color = Color.white; break;
				case 1: color = Color.green; break;
				case 2: color = Color.magenta; break;
				case 3: color = Color.cyan; break;
				default: break;
				}
				this.grid.setColor(row, col, color);
				
			}
		}
	}

}
