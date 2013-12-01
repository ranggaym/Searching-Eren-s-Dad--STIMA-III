package javasrcs;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 

import java.util.ArrayList;

/**
 *
 * @author RANGGA Y.MANGINDAAN
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String t = "aaaAbaaaaAbaaaAbaaAbaaAb";
        String p = "Ab";

        KMP kmp = new KMP (t, p);
        System.out.println("Text: " + t);
        System.out.println("Pattern: " + p);

        int posn = kmp.kmpMatch(t, p);
        if (posn == -1)
          System.out.println("Pattern not found");
        else 
          System.out.println("Pattern starts at posn " 
                                            + posn);
        ArrayList<Integer> r2 = new ArrayList<Integer>();
        r2 = kmp.matchAllPatternKMP(t, p);
        
        for (int x : r2){
            System.out.println("Pattern starts at posn " + x);
        }
    
    }
    
    
    
}