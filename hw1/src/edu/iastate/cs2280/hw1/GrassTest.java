package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

public class GrassTest {

    @Test
    public void testGrassRules() {
        Plain p = new Plain(3);
        Grass grass = new Grass(p, 1, 1);
        
        // 设置邻居：3 Rabbits，且 Grass 数量为 1（满足规则 a: 3 ≥ 3×1）
        p.grid[0][0] = new Rabbit(p, 0, 0, 0);
        p.grid[0][1] = new Rabbit(p, 0, 1, 0);
        p.grid[1][0] = new Rabbit(p, 1, 0, 0);
        p.grid[2][2] = new Grass(p, 2, 2); // 仅 1 个 Grass
        
        Living next = grass.next(new Plain(3));
        assertTrue(next instanceof Empty); //此处出现问题

    }
}