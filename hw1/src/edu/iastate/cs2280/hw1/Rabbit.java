package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Yingxuan Ye
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		// TODO 
		plain = p;
	    row = r;
	    column = c;
	    age = a;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		// TODO  
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a rabbit. 
		//return null; 
		
		int[] population = new int[Living.NUM_LIFE_FORMS];
        census(population); // 统计邻居中的生命形式数量

        int badgerCount = population[Living.BADGER]; // 獾的数量
        int foxCount = population[Living.FOX];       // 狐狸的数量
        int rabbitCount = population[Living.RABBIT]; // 兔子的数量（包括自身）
        int grassCount = population[Living.GRASS];   // 草的数量

        // Rule a: 年龄达到3 → 变为 Empty
        if (age >= RABBIT_MAX_AGE) {
            return new Empty(pNew, row, column);
        }
        // Rule b: 邻居中无草 → 变为 Empty
        else if (grassCount == 0) {
            return new Empty(pNew, row, column);
        }
        // Rule c: 獾和狐狸总数 ≥ 兔子数量，且狐狸多于獾 → 变为 Fox(0)
        else if (badgerCount + foxCount >= rabbitCount && foxCount > badgerCount) {
            return new Fox(pNew, row, column, 0);
        }
        // Rule d: 獾数量 > 兔子数量 → 变为 Badger(0)
        else if (badgerCount > rabbitCount) {
            return new Badger(pNew, row, column, 0);
        }
        // Rule e: 存活 → 年龄 +1
        else {
            return new Rabbit(pNew, row, column, age + 1);
        }
    }
	
}
