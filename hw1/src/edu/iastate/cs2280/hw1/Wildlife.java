package edu.iastate.cs2280.hw1;

import java.io.FileNotFoundException; 
import java.util.Scanner; 

/**
 *  
 * @author Yingxuan Ye

 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */


public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
		
		int width = pOld.getWidth();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // 对每个格子调用 next() 生成下一周期的生命形式
                Living current = pOld.grid[i][j];
                pNew.grid[i][j] = current.next(pNew);
            }
        }
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		
		Scanner scanner = new Scanner(System.in);
        int trial = 1; // 记录当前试验次数

        System.out.println("Simulation of Wildlife of the Plain");
        System.out.println("keys: 1 (random plain) 2 (file input) 3 (exit)");

        while (true) {
            System.out.print("Trial " + trial + ": ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                break; // 退出程序
            }
		
		
		
		
		
		
		
		Plain even;   				 // the plain after an even number of cycles 
		Plain odd;                   // the plain after an odd number of cycles
		int cycles = 0;
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
		
		
				// 生成初始平原
	            if (choice == 1) {
	                System.out.println("Random plain");
	                System.out.print("Enter grid width: ");
	                int width = scanner.nextInt();
	                even = new Plain(width);
	                even.randomInit();
	            } else if (choice == 2) {
	                System.out.println("Plain input from a file");
	                System.out.print("File name: ");
	                String filename = scanner.next();
	                even = new Plain(filename);
	            } else {
	                System.out.println("Invalid choice. Try again.");
	                continue;
	            }

	            // 输入周期数
	            System.out.print("Enter the number of cycles: ");
	            cycles = scanner.nextInt();
	            while (cycles <= 0) {
	                System.out.print("Enter a positive number of cycles: ");
	                cycles = scanner.nextInt();
	            }

	            // 打印初始平原
	            System.out.println("Initial plain:\n" + even.toString());

	            // 模拟周期
	            odd = new Plain(even.getWidth());
	            for (int i = 0; i < cycles; i++) {
	                if (i % 2 == 0) {
	                    updatePlain(even, odd);
	                } else {
	                    updatePlain(odd, even);
	                }
	            }

	            // 打印最终平原
	            Plain finalPlain = (cycles % 2 == 0) ? even : odd;
	            System.out.println("Final plain:\n" + finalPlain.toString());

	            trial++;
	        }

	        scanner.close();
	        System.out.println("Simulation ended.");
	    
		
		
		
		
		
		
		
		
	}
}
