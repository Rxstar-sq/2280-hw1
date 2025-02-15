package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.FileNotFoundException;

public class PlainTest {

    @Test
    public void testFileInitialization() throws FileNotFoundException {
        Plain p = new Plain("public3-10x10.txt");
        assertEquals(10, p.getWidth());
    }

    @Test
    public void testRandomInitialization() {
        Plain p = new Plain(5);
        p.randomInit();
        assertNotNull(p.grid[0][0]);
    }

    @Test
    public void testToString() {
        Plain p = new Plain(2);
        p.grid[0][0] = new Badger(p, 0, 0, 0);
        assertEquals("B0  E  \nE   E  ", p.toString());
    }
}