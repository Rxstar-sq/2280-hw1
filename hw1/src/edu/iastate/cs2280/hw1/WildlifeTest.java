package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

public class WildlifeTest {

    @Test
    public void testUpdatePlain() {
        Plain pOld = new Plain(3);
        pOld.grid[1][1] = new Badger(pOld, 1, 1, 0);
        pOld.grid[0][0] = new Grass(pOld, 0, 0);  // 添加草避免饿死
        pOld.grid[0][1] = new Rabbit(pOld, 0, 1, 0);
        Plain pNew = new Plain(3);
        
        Wildlife.updatePlain(pOld, pNew);
        assertTrue(pNew.grid[1][1] instanceof Badger);//此处出现问题
        assertEquals(1, ((Badger) pNew.grid[1][1]).myAge());
    }
}