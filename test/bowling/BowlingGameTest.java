/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LukaszF
 */
public class BowlingGameTest {
    
    public BowlingGameTest() {
    }
    
    /**
     * Test of roll method, of class BowlingGame.
     */
    //<editor-fold defaultstate="collapsed" desc="testowanie funkcji roll">
    
    // rzucanie ujemnych liczb
    @Test
    public void testRollNegativeNumber() {
        System.out.println("roll testRollNegativeNumber");
        BowlingGame instance = new BowlingGame();
        
        try {
            instance.roll(-8);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }
    
    // rzucanie więcej niż się da
    @Test
    public void testRollToMany() {
        System.out.println("roll testRollToMany");
        BowlingGame instance = new BowlingGame();
        instance.roll(0);
        instance.roll(5);
        instance.roll(6);
        
        try {
            instance.roll(6);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }
    
    @Test
    public void testRollToMany2() {
        System.out.println("roll testRollToMany2");
        BowlingGame instance = new BowlingGame();
        try {
            instance.roll(12);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }
    
    // rzucanie więcej razy niż się da
    @Test
    public void testRollToManyTimes() {
        System.out.println("roll testRollToManyTimes");
        BowlingGame instance = new BowlingGame();
        
        int i = 0;
        while (i++ < 20)
            instance.roll(4);
        
        try {
            instance.roll(4);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }
    @Test
    public void testRollToManyTimesSpare() {
        System.out.println("roll testRollToManyTimesSpare");
        BowlingGame instance = new BowlingGame();
        
        int i = 0;
        while (i++ < 19)
            instance.roll(4);
        instance.roll(6);
        instance.roll(5);
        
        try {
            instance.roll(5);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }
    
    //</editor-fold>  
    
    
    /**
     * Test of calculateScore method, of class BowlingGame.
     */
    //<editor-fold defaultstate="collapsed" desc="testowanie funkcji calculateScore">
    
    @Test
    public void testCalculateScore() {
        System.out.println("calculateScore");
        BowlingGame instance = new BowlingGame();
        int expResult = 0;
        int result = instance.calculateScore();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCalculateScoreAfterGame() {
        System.out.println("calculateScore");
        BowlingGame instance = new BowlingGame();
        while (!instance.isTheEnd())
            instance.roll(5);
        int expResult = 150;
            int result = instance.calculateScore();
            assertEquals(expResult, result);
    }
    
    @Test
    public void testCalculateScoreDefault1() {
        System.out.println("roll");
        int pins = 0;
        BowlingGame instance1 = new BowlingGame();
        instance1.roll(pins);
        assertEquals(0, instance1.calculateScore());
    }
    
    @Test
    public void testCalculateScoreDefault2() {
        System.out.println("roll");
        // mieszanina gry - strike i spare
        BowlingGame instance4 = new BowlingGame();
        instance4.roll(0);
        instance4.roll(5);
        instance4.roll(8);
        instance4.roll(2);
        instance4.roll(10);
        instance4.roll(2);
        instance4.roll(4);
        instance4.roll(2);
        assertEquals(49, instance4.calculateScore());
    }
    
    @Test
    public void testCalculateScoreDefault3() {
        System.out.println("roll");
        // rzucanie samych dyszek
        BowlingGame instance3 = new BowlingGame();
        while (!instance3.isTheEnd())
                instance3.roll(10);
        assertEquals(300, instance3.calculateScore());
        assertEquals(-1, instance3.getnActualFrame());
    }
    
    @Test
    public void testCalculateScoreDefault4() {
        System.out.println("roll");
        // rzucanie samych piątek
        BowlingGame instance2 = new BowlingGame();
        while (!instance2.isTheEnd())
                instance2.roll(5);
        assertEquals(150, instance2.calculateScore());
    }
    
    @Test
    public void testCalculateScoreDefault5() {
        System.out.println("roll");
        // na końcu śmieszne rzeczy
        BowlingGame instance1 = new BowlingGame();
        int i = 0;
        while (i++ < 16)
            instance1.roll(4);
        instance1.roll(10);
        instance1.roll(3);
        instance1.roll(7);
        instance1.roll(8);
        assertEquals(102, instance1.calculateScore());
    }
    
    //</editor-fold>
    
    /**
     * Test of isTheEnd method, of class BowlingGame.
     */
    //<editor-fold defaultstate="collapsed" desc="testowanie funkcji isTheEnd">
    @Test
    public void testIsTheEnd() {
        System.out.println("isTheEnd");
        BowlingGame instance = new BowlingGame();
        boolean expResult = false;
        assertEquals(expResult, instance.isTheEnd());
    }
    
    @Test
    public void testIsTheEnd2() {
        System.out.println("isTheEnd");
        BowlingGame instance = new BowlingGame();
        boolean expResult = false;
        int i = 0;
        while (i++ < 9)
            instance.roll(4);
        assertEquals(expResult, instance.isTheEnd());
    }
    
    @Test
    public void testIsTheEnd3() {
        System.out.println("isTheEnd");
        BowlingGame instance = new BowlingGame();
        boolean expResult = true;
        int i = 0;
        while (i++ < 20)
            instance.roll(4);
        assertEquals(expResult, instance.isTheEnd());
    }
    //</editor-fold>

    /**
     * Test of OutPutPoints method, of class BowlingGame.
     */
    @Test
    public void testOutputPoints() {
        System.out.println("OutputPoints");
        BowlingGame instance = new BowlingGame();
        instance.outputPoints();
        //nie wiem co tu testować? 
    }
    
}
