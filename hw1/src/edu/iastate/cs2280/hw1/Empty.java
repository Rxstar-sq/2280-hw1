package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Yingxuan Ye
 * 
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		// TODO  
		plain = p;
	    row = r;
	    column = c;
	}
	
	public State who()
	{
		// TODO 
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for corresponding survival rules. 
		//return null;
		
		int rabbitCount = 0;
        int foxCount = 0;
        int badgerCount = 0;
        int grassCount = 0;

        // 遍历以当前格子为中心的 3x3 邻居区域（排除自身）
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                // 跳过自身格子
                if (i == row && j == column) {
                    continue;
                }
                // 检查边界
                if (i >= 0 && i < plain.getWidth() && j >= 0 && j < plain.getWidth()) {
                    Living neighbor = plain.grid[i][j];
                    State state = neighbor.who();
                    // 统计邻居中的各生命形式数量
                    if (state == State.RABBIT) {
                        rabbitCount++;
                    } else if (state == State.FOX) {
                        foxCount++;
                    } else if (state == State.BADGER) {
                        badgerCount++;
                    } else if (state == State.GRASS) {
                        grassCount++;
                    }
                }
            }
        }

        // 按优先级顺序判断规则
        // 规则 a: Rabbit 数量 > 1 → 生成 Rabbit(0)
        if (rabbitCount > 1) {
            return new Rabbit(pNew, row, column, 0);
        }
        // 规则 b: Fox 数量 > 1 → 生成 Fox(0)
        else if (foxCount > 1) {
            return new Fox(pNew, row, column, 0);
        }
        // 规则 c: Badger 数量 > 1 → 生成 Badger(0)
        else if (badgerCount > 1) {
            return new Badger(pNew, row, column, 0);
        }
        // 规则 d: 至少有一个 Grass → 生成 Grass
        else if (grassCount >= 1) {
            return new Grass(pNew, row, column);
        }
        // 规则 e: 其他情况保持 Empty
        else {
            return new Empty(pNew, row, column);
        }
	}
}
