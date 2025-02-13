package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Yingxuan Ye
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		// TODO 
		plain = p;
	    row = r;
	    column = c;
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for grass. 
		//return null; 
		int[] population = new int[Living.NUM_LIFE_FORMS];
	    census(population);

	    int grassCount = population[Living.GRASS];
	    int rabbitCount = population[Living.RABBIT];

	    if (rabbitCount >= 3 * grassCount) {
	        return new Empty(pNew, row, column);
	    } else if (rabbitCount >= 3) {
	        return new Rabbit(pNew, row, column, 0);
	    } else {
	        return new Grass(pNew, row, column);
	    }
	}
}
