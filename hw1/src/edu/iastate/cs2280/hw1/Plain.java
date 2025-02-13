package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Yingxuan Ye 
 *
 */

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
		
		
		String testDataDir = "src/public/"; // 根据实际目录调整路径
	    File file = new File(testDataDir + inputFileName); // 组合完整路径

        Scanner scanner = new Scanner(file);

        // 读取第一行确认场地长度
        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine().trim();
            width = firstLine.split("\\s+").length; 
        }
        
        grid = new Living[width][width];

        scanner.close();
        scanner = new Scanner(file);

        // 填充
        for (int i = 0; i < width; i++) {
            if (scanner.hasNextLine()) {
                String[] cells = scanner.nextLine().trim().split("\\s+");
                for (int j = 0; j < width; j++) {
                    String cell = cells[j];
                    char type = cell.charAt(0);
                    if (type == 'B') {
                        int age = Character.getNumericValue(cell.charAt(1));
                        grid[i][j] = new Badger(this, i, j, age);
                    } else if (type == 'F') {
                        int age = Character.getNumericValue(cell.charAt(1));
                        grid[i][j] = new Fox(this, i, j, age);
                    } else if (type == 'R') {
                        int age = Character.getNumericValue(cell.charAt(1));
                        grid[i][j] = new Rabbit(this, i, j, age);
                    } else if (type == 'G') {
                        grid[i][j] = new Grass(this, i, j);
                    } else if (type == 'E') {
                        grid[i][j] = new Empty(this, i, j);
                    }
                }
            }
        }

     
        scanner.close();
	    
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		// TODO 
		width = w;
        grid = new Living[width][width];
	}
	
	
	public int getWidth()
	{
		// TODO  
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		 
		// TODO 
		
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                int randomValue = generator.nextInt(5); // Random number between 0 and 4
                switch (randomValue) {
                    case 0:
                        grid[i][j] = new Badger(this, i, j, 0);
                        break;
                    case 1:
                        grid[i][j] = new Empty(this, i, j);
                        break;
                    case 2:
                        grid[i][j] = new Fox(this, i, j, 0);
                        break;
                    case 3:
                        grid[i][j] = new Grass(this, i, j);
                        break;
                    case 4:
                        grid[i][j] = new Rabbit(this, i, j, 0);
                        break;
                }
            }
        }
    }

	
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		// TODO
		 StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < width; j++) {
	                Living cell = grid[i][j];
	                if (cell != null) {
	                    State state = cell.who();
	                    sb.append(state.toString().charAt(0)); 
	                    if (cell instanceof Animal) {
	                        sb.append(((Animal) cell).myAge()).append(" ");
	                    } else {
	                        sb.append("  ");
	                    }
	                } else {
	                    sb.append("E  "); // Empty cell
	                }
	                if (j < width - 1) {
	                    sb.append(" ");
	                }
	            }
	            if (i < width - 1) {
	                sb.append("\n");
	            }
	        }
	        return sb.toString(); 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
		
		 PrintWriter writer = new PrintWriter(outputFileName);
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < width; j++) {
	                Living cell = grid[i][j];
	                if (cell != null) {
	                    State state = cell.who();
	                    writer.print(state.toString().charAt(0)); // First letter of the state
	                    if (cell instanceof Animal) {
	                        writer.print(((Animal) cell).myAge());
	                    } else {
	                        writer.print(" ");
	                    }
	                } else {
	                    writer.print("E ");
	                }
	                if (j < width - 1) {
	                    writer.print(" ");
	                }
	            }
	            if (i < width - 1) {
	                writer.println();
	            }
	        }
	        writer.close();
	    }
	}			

