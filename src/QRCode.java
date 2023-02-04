/**
 * Program 1
 *  This program creates a QR code through an instantiated object of class QRCode and uses the instance methods to generate a random pattern and set that pattern as a 2-D array. It uses
 *  the creation of arrays and for loops to achieve this result. 
 *  CS160-3
 *  2/5/22
 *  @author  Cameron Cobb
 * 
 */
import java.util.Random;

public class QRCode {
	
	private int[][] grid;
	
	
	/**
	 * This method creates a 1-D array with each element being a random number from 0 to 1. It returns this new array to the main method.
	 * @param dim
	 * @param seed
	 * @return
	 */
	public int[] createPattern(int dim, int seed) {
		Random randGen = new Random(seed);
		
		int[] patternArray = new int[dim * dim];
		
		for (int i = 0; i < patternArray.length; ++i) {
				patternArray[i] = randGen.nextInt(2);
		}

		return patternArray;
		
	}
	
	/**
	 * This method is a mutator for the private field grid. It uses the parameters dim and pattern to copy the elements from the 1-D pattern array to the 2-D instance field using a nested 
	 * for loop.
	 * @param dim
	 * @param pattern
	 */
	public void setGrid(int dim, int[] pattern) {
		if (pattern.length > 0) {
			int counter = 0;
			grid = new int[dim][dim];
			for (int i = 0; i < grid.length; ++i) {
				for (int j = 0; j < grid[i].length; ++j) {
					grid[i][j] = pattern[counter];
					counter++;
				}
			}
		}
	}
	
	/**
	 * This is a getter that retrieves the private instance field grid, a 2-D array created from pattern.
	 * @return
	 */
	public int[][] getGrid() {
		int[][] tempArray = grid;
		return tempArray;
	}
	
	/**
	 * This overloaded method prints the 2-D matrix passed as an argument. Uses a nested for loop and System.out.print to do so.
	 * @param matrix
	 */
	public void print(int[][] matrix) {
			for (int i = 0; i < matrix.length; ++i) {
				for (int j = 0; j < matrix[i].length; ++j) {
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
	
	/**
	 * This overloaded method prints the elements of the private instance field though a nested for loop.
	 */
	public void print() {
		if (grid.length >= 30) {
			for (int i = 0; i < grid.length; ++i) {
				for (int j = 0; j < grid[i].length; ++j) {
					System.out.print(grid[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	/**
	 * This overloaded method prints the parameter pattern as a 2-D in the output.
	 * @param pattern
	 */
	public void print(int[] pattern) {
			int counter = 0;
			
			for (int i = 0; i < Math.sqrt(pattern.length); ++i) {
				for (int j = 0; j < Math.sqrt(pattern.length); ++j) {
					System.out.print(pattern[counter]);
					counter++;
				}
				System.out.println();
			}
			System.out.println();
		}

	/**
	 * This method sets the finder boxes at the corners of the 2-D grid. It uses 4 nested for loops to do so. The xPos and yPos parameters are passed from main, which are the locations
	 * of each corner.
	 * @param xPos
	 * @param yPos
	 */
	public void setFinder(int xPos, int yPos) {
		
			// Finder 15x15 white box
			for (int i = xPos; i < xPos + 15; ++i) {
				for (int j = yPos; j < yPos + 15; ++j) {
					grid[i][j] = 1;
				}
			}
			// Finder 11x11 black box
			for (int i = xPos + 2; i < xPos + 13; ++i) {
				for (int j = yPos + 2; j < yPos + 13; ++j) {
					grid[i][j] = 0;
				}
			}
			
			//Finder 7x7 white box
			for (int i = xPos + 4; i < xPos + 11; ++i) {
				for (int j = yPos + 4; j < yPos + 11; ++j) {
					grid[i][j] = 2;
				}
			}
			
			//Finder 3x3 black box
			for (int i = xPos + 6; i < xPos + 9; ++i) {
				for (int j = yPos + 6; j < yPos + 9; ++j) {
					grid[i][j] = 3;
				}
			}
		}
		
	
	public static void main(String[] args) {
		QRCode code = new QRCode();
		
		int dim;
		int seed;
		final int DEFAULT_DIMENSION = 30;
		final int DEFAULT_SEED = 160;
		int[] pattern;
		int[][] matrix;
		
		if (args.length == 2) {
			dim = Integer.parseInt(args[0]);
			seed = Integer.parseInt(args[1]);
			
			pattern = new int[dim * dim];
		
			
			pattern = code.createPattern(dim, seed);
			code.setGrid(dim, pattern);
		}
		else {
			pattern = new int[DEFAULT_DIMENSION * DEFAULT_DIMENSION];
			pattern = code.createPattern(DEFAULT_DIMENSION, DEFAULT_SEED);
			code.setGrid(DEFAULT_DIMENSION, pattern);
			
		}
		matrix = code.getGrid();
		code.setFinder(0, 0);
		code.setFinder(matrix.length - 15, 0);
		code.setFinder(0, matrix[0].length - 15);
		code.print(matrix);

		
		
	}
}
