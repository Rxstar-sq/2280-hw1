package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FoxTest {
    private Plain pOld;
    private Plain pNew;

    @Before
    public void setup() {
        pOld = new Plain(3);
        pNew = new Plain(3);
    }

    @Test
    public void testFoxNext_SurvivalRules() {
        // 场景 1: 规则 a（年龄达到 6 → Empty）
        pOld.grid[1][1] = new Fox(pOld, 1, 1, 6);
        assertNextState(State.EMPTY, 0);

        // 场景 2: 规则 b（Badger > Fox → Badger）
        pOld.grid[1][1] = new Fox(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Badger(pOld, 0, 0, 0);
        pOld.grid[0][1] = new Badger(pOld, 0, 1, 0);
        assertNextState(State.BADGER, 0);

        // 场景 3: 规则 c（Badger + Fox > Rabbit → Empty）
        pOld.grid[1][1] = new Fox(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Badger(pOld, 0, 0, 0);
        pOld.grid[0][1] = new Rabbit(pOld, 0, 1, 0);
        assertNextState(State.EMPTY, 0);

        // 场景 4: 规则 d（存活 → 年龄 +1）
        pOld.grid[1][1] = new Fox(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Rabbit(pOld, 0, 0, 0);
        assertNextState(State.FOX, 3);
    }

    private void assertNextState(State expectedState, int expectedAge) {
        Living next = pOld.grid[1][1].next(pNew);
        assertEquals(expectedState, next.who());
        if (next instanceof Animal) {
            assertEquals(expectedAge, ((Animal) next).myAge());
        }
    }
}