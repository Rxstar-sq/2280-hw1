package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmptyTest {

    @Test
    public void testEmptyRules() {
        Plain p = new Plain(3);
        Empty empty = new Empty(p, 1, 1);
        
        // 设置邻居：2 Rabbits → 变为 Rabbit
        p.grid[0][0] = new Rabbit(p, 0, 0, 0);
        p.grid[0][1] = new Rabbit(p, 0, 1, 0);
        Living next = empty.next(new Plain(3));
        assertTrue(next instanceof Rabbit);
    }
}