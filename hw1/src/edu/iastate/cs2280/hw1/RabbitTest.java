package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RabbitTest {
    private Plain pOld;
    private Plain pNew;

    @Before
    public void setup() {
        pOld = new Plain(3);
        pNew = new Plain(3);
    }

    @Test
    public void testRabbitNext_SurvivalRules() {
        // 场景 1: 规则 a（年龄达到 3 → Empty）
        pOld.grid[1][1] = new Rabbit(pOld, 1, 1, 3);
        assertNextState(State.EMPTY, 0);

        // 场景 2: 规则 b（无草 → Empty）
        pOld.grid[1][1] = new Rabbit(pOld, 1, 1, 2);
        assertNextState(State.EMPTY, 0);

        // 场景 3: 规则 c（Fox + Badger ≥ Rabbit 且 Fox > Badger → Fox）
        pOld.grid[1][1] = new Rabbit(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Fox(pOld, 0, 0, 0);
        pOld.grid[0][1] = new Fox(pOld, 0, 1, 0);
        pOld.grid[1][0] = new Badger(pOld, 1, 0, 0);
        // 添加草避免触发规则 b
        pOld.grid[2][2] = new Grass(pOld, 2, 2);
        assertNextState(State.FOX, 0); //此处出现问题

        // 场景 4: 规则 d（Badger > Rabbit → Badger）
        pOld.grid[1][1] = new Rabbit(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Badger(pOld, 0, 0, 0);
        pOld.grid[0][1] = new Badger(pOld, 0, 1, 0);
        assertNextState(State.BADGER, 0);

        // 场景 5: 规则 e（存活 → 年龄 +1）
        pOld.grid[1][1] = new Rabbit(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Grass(pOld, 0, 0);
        pOld.grid[0][1] = new Grass(pOld, 0, 1);
        assertNextState(State.RABBIT, 3);
    }

    private void assertNextState(State expectedState, int expectedAge) {
        Living next = pOld.grid[1][1].next(pNew);
        assertEquals(expectedState, next.who());
        if (next instanceof Animal) {
            assertEquals(expectedAge, ((Animal) next).myAge());
        }
    }
}