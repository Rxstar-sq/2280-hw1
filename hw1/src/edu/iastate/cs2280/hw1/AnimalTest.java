package edu.iastate.cs2280.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

public class AnimalTest {

    @Test
    public void testAnimalAge() {
        // 使用具体子类测试 myAge() 方法
        Animal badger = new Badger(new Plain(3), 0, 0, 2);
        assertEquals(2, badger.myAge());
        
        Animal fox = new Fox(new Plain(3), 0, 0, 5);
        assertEquals(5, fox.myAge());
        
     //   Animal rabbit = new Rabbit(new Plain(3), 0, 0, 3);
     //   assertEquals(0, rabbit.myAge());
    }
}