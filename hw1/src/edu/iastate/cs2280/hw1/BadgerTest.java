package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BadgerTest {
    private Plain pOld;
    private Plain pNew;

    @Before
    public void setup() {
        // 初始化 3x3 的平原，所有格子默认为 Empty
        pOld = new Plain(3);
        pNew = new Plain(3);
    }

    @Test
    public void testBadgerNext_SurvivalRules() {
        // 初始化中心格子为 Badger（年龄 2）
        pOld.grid[1][1] = new Badger(pOld, 1, 1, 2);

        // 设置邻居环境，触发不同规则
        // --------------------------------
        // 场景 1: 规则 a（年龄达到 4 → Empty）
        pOld.grid[1][1] = new Badger(pOld, 1, 1, 4);
        assertNextState(State.EMPTY, 0); // 预期变为 Empty

        // 场景 2: 规则 b（1 Badger + >1 Fox → Fox）
        pOld.grid[1][1] = new Badger(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Fox(pOld, 0, 0, 0);
        pOld.grid[0][1] = new Fox(pOld, 0, 1, 0);
        assertNextState(State.FOX, 0); // 预期变为 Fox（年龄 0）

        // 场景 3: 规则 c（Badger + Fox > Rabbit → Empty）
        pOld.grid[1][1] = new Badger(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Badger(pOld, 0, 0, 1);
        pOld.grid[0][1] = new Fox(pOld, 0, 1, 0);
        pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 0);
        assertNextState(State.EMPTY, 0); // 预期变为 Empty

        // 场景 4: 规则 d（存活 → 年龄 +1）
        pOld.grid[1][1] = new Badger(pOld, 1, 1, 2);
        pOld.grid[0][0] = new Rabbit(pOld, 0, 0, 0);
        pOld.grid[0][1] = new Grass(pOld, 0, 1);
        assertNextState(State.BADGER, 3); // 预期仍为 Badger（年龄 3）
    }

    /**
     * 通用断言方法：验证 next() 的结果是否符合预期
     * @param expectedState 预期状态
     * @param expectedAge   预期年龄（仅对 Animal 有效）
     */
    private void assertNextState(State expectedState, int expectedAge) {
        Living current = pOld.grid[1][1];
        Living next = current.next(pNew);
        
        // 验证状态
        assertEquals(expectedState, next.who());
        
        // 如果是动物，验证年龄
        if (next instanceof Animal) {
            assertEquals(expectedAge, ((Animal) next).myAge());
        }
    }
}