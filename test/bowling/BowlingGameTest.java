/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LukaszF
 */
public class BowlingGameTest {
    
    public BowlingGameTest() {
    }

    public boolean equalsTab (int [] tab1, int [] tab2) {
        if (tab1 == null || tab2 == null)
            return false;
        
        if (tab1.length != tab2.length)
            return false;
        
        for (int i = 0; i < tab1.length; i++)
            if (tab1[i] != tab2[i])
                return false;
            
        return true;
    }
    
    @Test
    public void testNewGameAfterGame() {
        System.out.println("newGame");
        BowlingGame instance = new BowlingGame();
        instance.roll(5);
        instance.roll(5);
        instance.roll(10);
        instance.roll(2);
        instance.newGame();
        int [] newTab = new int [10];
        
        if ( ! (
                instance.isbNewFrame() == true &&
                instance.getnPinFrameNumber() == 10 &&
                instance.getnActualFrame() == 1 &&
                equalsTab(instance.getnPoints(), newTab) &&
                equalsTab(instance.getnEndType(), newTab) &&
                equalsTab(instance.getnAddType(), newTab)
                )
            )
                fail("nie działa nowa gra po grze");
    }
    
    /**
     * Test of calculateScore method, of class BowlingGame.
     */
    @Test
    public void testCalculateScore() {
        System.out.println("calculateScore");
        BowlingGame instance = new BowlingGame();
        int expResult = 0;
        try {
            int result = instance.calculateScore();
            assertEquals(expResult, result);
        }
        catch (Exception e) {
            Logger.getLogger( BowlingGame.class.getName() ).log( Level.SEVERE, null, e );
        }
    }
    
    @Test
    public void testCalculateScore2() {
        System.out.println("calculateScore");
        BowlingGame instance = new BowlingGame();
        instance.getnPoints()[2] = 230;
        instance.getnPoints()[3] = 100;
        int expResult = 330;
        try {
            int result = instance.calculateScore();
            assertEquals(expResult, result);
        }
        catch (Exception e) {
            Logger.getLogger( BowlingGame.class.getName() ).log( Level.SEVERE, null, e );
        }
    }
    
    @Test
    public void testCalculateScoreAfterGame() {
        System.out.println("calculateScore");
        BowlingGame instance = new BowlingGame();
        while (!instance.isTheEnd())
            instance.roll(5);
        int expResult = 150;
        try {
            int result = instance.calculateScore();
            assertEquals(expResult, result);
        }
        catch (Exception e) {
            Logger.getLogger( BowlingGame.class.getName() ).log( Level.SEVERE, null, e );
        }
    }

    /**
     * Test of roll method, of class BowlingGame.
     */
    @Test
    public void testRoll() throws Exception {
        System.out.println("roll");
        int pins = 0;
        BowlingGame instance = new BowlingGame();
        instance.roll(pins);
        assertEquals(0, instance.calculateScore());
        
        // rzucanie samych piątek
        instance.newGame();
        while (!instance.isTheEnd())
                instance.roll(5);
        assertEquals(150, instance.calculateScore());
        
        // rzucanie samych dyszek
        instance.newGame();
        while (!instance.isTheEnd())
                instance.roll(10);
        assertEquals(300, instance.calculateScore());
        assertEquals(-1, instance.getnActualFrame());
        
        // mieszanina gry - strike i spare
        instance.newGame();
        instance.roll(0);
        instance.roll(5);
        instance.roll(8);
        instance.roll(2);
        instance.roll(10);
        instance.roll(2);
        instance.roll(4);
        instance.roll(2);
        assertEquals(49, instance.calculateScore());
        assertEquals(5, instance.getnActualFrame());
    }
    
    // rzucanie więcej niż się da
    @Test
    public void testRollToMany() throws Exception {
        System.out.println("roll");
        BowlingGame instance = new BowlingGame();
        instance.roll(0);
        instance.roll(5);
        instance.roll(6);
        instance.roll(6);
        assertEquals(11, instance.calculateScore());
    }
    
    // rzucanie więcej razy niż się da
    @Test
    public void testRollToManyTimes() throws Exception {
        System.out.println("roll");
        BowlingGame instance = new BowlingGame();
        
        int i = 0;
        while (i++ < 50)
            instance.roll(4);
        assertEquals(80, instance.calculateScore());
    }

    /**
     * Test of isTheEnd method, of class BowlingGame.
     */
    @Test
    public void testIsTheEnd() {
        System.out.println("isTheEnd");
        BowlingGame instance = new BowlingGame();
        boolean expResult = false;
        boolean result = instance.isTheEnd();
        assertEquals(expResult, result);
        
        expResult = true;
        instance.setnActualFrame(-1);
        result = instance.isTheEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of OutPutPoints method, of class BowlingGame.
     */
    @Test
    public void testOutputPoints() {
        System.out.println("OutputPoints");
        BowlingGame instance = new BowlingGame();
        instance.OutputPoints();
        //nie wiem co tu testować? 
    }

    /**
     * Test of newFrame method, of class BowlingGame.
     */
    @Test
    public void testNewFrame() {
        System.out.println("newFrame");
        BowlingGame instance = new BowlingGame();
        int nOldActualFrame = instance.getnActualFrame();
        instance.newFrame();
        
        if ( ! (
                instance.isbNewFrame() == true &&
                instance.getnPinFrameNumber() == 10 &&
                instance.getnActualFrame() == (nOldActualFrame + 1)
                )
            )
                fail("nie działa nowa tura");
        
        //przykład nierealny
        instance.setnActualFrame(40);
        nOldActualFrame = instance.getnActualFrame();
        instance.newFrame();
        
        if ( ! (
                instance.isbNewFrame() == true &&
                instance.getnPinFrameNumber() == 10 &&
                instance.getnActualFrame() == (nOldActualFrame + 1)
                )
            )
                fail("nie działa nowa tura");
    }
    
}
