package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

public class LivingTest {

    @Test
    public void testCensus() {
        Plain p = new Plain(3);
        p.grid[1][1] = new Badger(p, 1, 1, 0);
        p.grid[0][0] = new Fox(p, 0, 0, 0);
        p.grid[2][2] = new Rabbit(p, 2, 2, 0);
        
        int[] population = new int[Living.NUM_LIFE_FORMS];
        p.grid[1][1].census(population);
        
        assertEquals(1, population[Living.BADGER]);
        assertEquals(1, population[Living.FOX]);
        assertEquals(1, population[Living.RABBIT]);
    }
}