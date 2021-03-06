/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LukaszF
 */
public class BowlingGame {
    
    final private int nSize = 10;
    
    // flaga, czy właśnie zaczynamy nową turę
    private boolean bNewFrame;
    
    // ile zostało słupków do zbicia w tej turze
    private int nPinFrameNumber;
    
    // którą mamy turę
    // -1 to koniec gry
    private int nActualFrame;
    
    // punkty w każdej turze
    private int [] nPoints;
    
    // czym zakończyła się każda tura (zawsze na tablicy widać punkty i ten typ)
    // 0 - normalny, 1 - spare, 2 - strike
    private int [] nEndType;
    
    // ile razy jeszcze mamy dodawać punkty
    // 0 - normalny, 1 - spare, 2 - strike
    private int [] nAddType;
    
    // można by było to zrobić strukturami FIFO (np. kolejka) i wstawiać rekordy na strike oraz spare, ale tablica typów nam już wszystko załatwia
    
    
    //tworząc nowy obiekt zakładamy nową grę
    BowlingGame () {
        bNewFrame = true;
        nPinFrameNumber = 10;
        nActualFrame = 1;

        // int jest z automatu 0
        nPoints = new int [nSize];
        nEndType = new int [nSize];
        nAddType = new int [nSize];
    }
    
    public void roll (int pins) {
        
        // sprawdzanie, czy gra skończona
        if (nActualFrame == -1)
            throw new IllegalArgumentException ("Nie można już rzucać, gra się skończyła.");
        
        // sprawdzanie oszustów
        if (pins > nPinFrameNumber)
            throw new IllegalArgumentException ("Oszukańczy oszust chciał zbić więcej niż się da.");
        
        // sprawdzanie, czy gra skończona
        if (pins < 0)
            throw new IllegalArgumentException ("Nie można przewrócić ujemnej liczby pachołków.");
        
        nPinFrameNumber -= pins;
        if (nActualFrame < 11 )
            nAddType[nActualFrame -1] = 1;
        
        // minimalna liczba przejść
        for (int i = nActualFrame - Math.min(nActualFrame, 3); i < Math.min(nActualFrame + 1, 10) ;i++ ) {
            
            if (nAddType[i] > 0) {
                nPoints[i] += pins; 
                nAddType[i]--;
            }
        }
        
        // zawsze mamy 2 ruchy w turze, chyba że strzelimy 10 za 1 razem
        if (nPinFrameNumber == 0) {
            
            // strike
            if (bNewFrame && nActualFrame - 1 < 10 ) {
                nEndType[nActualFrame - 1] = 2;
                nAddType[nActualFrame - 1] = 2;
            }
            // spare
            else if (!bNewFrame && nActualFrame - 1 < 10 ) {
                nEndType[nActualFrame - 1] = 1;
                nAddType[nActualFrame - 1] = 1;
            }
            bNewFrame = true;
            nPinFrameNumber = 10;
            nActualFrame++;
            
        }
        else {
            if (bNewFrame)
                bNewFrame = false;
            else  {
                bNewFrame = true;
                nPinFrameNumber = 10;
                nActualFrame++;
            }
        }
        
        // być może ten ruch zakończył grę
        if (nActualFrame > 10 && nAddType[9] == 0) {
            Logger.getLogger( BowlingGame.class.getName() ).log( Level.SEVERE, "Gra skończona.");
            nActualFrame = -1;
            return;
        }
        
    }
    
    public int calculateScore () {
        
        int nSum = 0;
        
        for (int nValue : nPoints) {
            nSum += nValue;
        }
        
//        if (nSum > 300) {
//            nSum = 0;
//            throw new Exception("Nie da się mieć więcej niż 300 pkt. Oszust.");
//        }
        
        return nSum;
    }
    
    public boolean isTheEnd () {
        return (nActualFrame == -1);
    }
    
    public void outputPoints () {
        System.out.println( Arrays.toString(nPoints) );
        System.out.println( Arrays.toString(nEndType) );
    }

    //<editor-fold defaultstate="collapsed" desc="gettery i settery">
    
    public boolean isbNewFrame() {
        return bNewFrame;
    }

    public void setbNewFrame(boolean bNewFrame) {
        this.bNewFrame = bNewFrame;
    }

    public int getnPinFrameNumber() {
        return nPinFrameNumber;
    }

    public void setnPinFrameNumber(int nPinFrameNumber) {
        this.nPinFrameNumber = nPinFrameNumber;
    }

    public int getnActualFrame() {
        return nActualFrame;
    }

    public void setnActualFrame(int nActualFrame) {
        this.nActualFrame = nActualFrame;
    }

    public int[] getnPoints() {
        return nPoints;
    }

    public void setnPoints(int[] nPoints) {
        this.nPoints = nPoints;
    }

    public int[] getnEndType() {
        return nEndType;
    }

    public void setnEndType(int[] nEndType) {
        this.nEndType = nEndType;
    }

    public int[] getnAddType() {
        return nAddType;
    }

    public void setnAddType(int[] nAddType) {
        this.nAddType = nAddType;
    }
    
    //</editor-fold>   
    
}
