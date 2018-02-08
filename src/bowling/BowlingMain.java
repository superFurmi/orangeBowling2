/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import java.util.Scanner;

/**
 *
 * @author LukaszF
 */
public class BowlingMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Scanner scan = new Scanner(System.in);
        
        do {
            BowlingGame myGame = new BowlingGame();
            while (!myGame.isTheEnd()) {
                myGame.roll(scan.nextInt());
                myGame.outputPoints();
            }
            System.out.println("Gra skończona. Masz " + myGame.calculateScore() + " punktów." + ( (myGame.calculateScore() > 200) ? " Gratulacje!" : "") );
            System.out.println("Aby zacząć jeszcze raz napisz 'tak'.");
        }
        while (scan.next().equals("tak") );
        
    }
    
}
