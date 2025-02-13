package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Yingxuan Ye
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		// TODO
		plain = p;
	    row = r;
	    column = c;
	    age = a;
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		// TODO 
		return State.BADGER;
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger. 
		int badgerCount = 0;
        int foxCount = 0;
        int rabbitCount = 0;

        // 创建3x3场地
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                // 遍历确认场地格是否可用
                if (i >= 0 && i < plain.getWidth() && j >= 0 && j < plain.getWidth()) {
                    Living neighbor = plain.grid[i][j];
                    if (neighbor != null) {
                        State state = neighbor.who();
                        if (state == State.BADGER) {
                            badgerCount++;
                        } else if (state == State.FOX) {
                            foxCount++;
                        } else if (state == State.RABBIT) {
                            rabbitCount++;
                        }
                    }
                }
            }
        }

        // Rule a: Badger dies of old age (age >= 4)
        if (age >= 4) {
            return new Empty(pNew, row, column); // 变为 Empty
        }
        // Rule b: Badger is replaced by Fox if there is only 1 Badger and more than 1 Fox
        else if (badgerCount == 1 && foxCount > 1) {
            return new Fox(pNew, row, column, 0); // 变为 Fox，年龄为 0
        }
        // Rule c: Badger dies if Badgers and Foxes together outnumber Rabbits
        else if (badgerCount + foxCount > rabbitCount) {
            return new Empty(pNew, row, column); // 变为 Empty
        }
        // Rule d: Badger survives and ages by 1
        else {
            return new Badger(pNew, row, column, age + 1); // 年龄 +1
        }
		
		
		
		//return null; 
	}
}
