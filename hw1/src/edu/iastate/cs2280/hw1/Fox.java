package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Yingxuan Ye
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		// TODO 
		plain = p;
	    row = r;
	    column = c;
	    age = a;
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		// TODO 
		return State.FOX;
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a fox. 
		//return null; 
		 int[] population = new int[5];
		    census(population);

		    int badgerCount = population[0];
		    int foxCount = population[2];
		    int rabbitCount = population[4];

		    if (age >= FOX_MAX_AGE) {
		        return new Empty(pNew, row, column);
		    } else if (badgerCount > foxCount) {
		        return new Badger(pNew, row, column, 0);
		    } else if (badgerCount + foxCount > rabbitCount) {
		        return new Empty(pNew, row, column);
		    } else {
		        return new Fox(pNew, row, column, age + 1);
		    }
		
			
	}
}
