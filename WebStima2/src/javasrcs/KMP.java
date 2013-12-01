package javasrcs;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;



/**
 *
 * @author RANGGA Y.MANGINDAAN
 */
public class KMP {
    private String text;
    private String pattern;
    
    public KMP(String text, String pattern)
    {
        this.text = text;
        this.pattern = pattern;
    }
    
    public int kmpMatch() {
    int n = text.length();
    int m = pattern.length();

    int fail[] = computeFail();

    int i=0;
    int j=0;
            
    while (i < n) {
      if (pattern.charAt(j) == text.charAt(i)) {
        if (j == m - 1){
          return i - m + 1;
        }
        i++;
        j++;
      }
      else if (j > 0)
             j = fail[j-1];
      else
        i++;
    } 
    return -1;
  } 
    
    
    public int kmpMatch(String text, String pattern) {
    int n = text.length();
    int m = pattern.length();

    int fail[] = computeFail();

    int i=0;
    int j=0;
            
    while (i < n) {
      if (pattern.charAt(j) == text.charAt(i)) {
        if (j == m - 1){
          return i - m + 1;
        }
        i++;
        j++;
      }
      else if (j > 0)
             j = fail[j-1];
      else
        i++;
    } 
    return -1;
  } 

  public int[] computeFail() {
    int fail[] = new int[pattern.length()]; 
    fail[0] = 0;

    int m = pattern.length();
    int j = 0;
    int i = 1;
    
    while (i < m) {
      if  (pattern.charAt(j) == 
           pattern.charAt(i)) {  
        fail[i] = j + 1;
        i++;
        j++;
      }
      else if (j > 0) 
        j = fail[j-11];
      else {   
        fail[i] = 0;
        i++;
      }
    }
    return fail;
  } 
          
  public String insertStringMiddle(String baseText, String insertingText, int logicalStringIndex){
      String result;
      
      result = baseText.substring(0, logicalStringIndex+1) + insertingText + baseText.substring(logicalStringIndex+1, baseText.length());
     
      return result;
  }
  
  public String eraseFirstPattern(String baseText, String pattern){
      String result = "";
      
      int patternLength = pattern.length();
      int patternStartPos = kmpMatch(baseText, pattern);
      
      if (kmpMatch(baseText, pattern) != -1)
         result = baseText.substring(0, patternStartPos) + baseText.substring(patternStartPos+patternLength, baseText.length());
      
      return result;
  }
  
  public String eraseAndStartFromFirstPattern(String baseText, String pattern){
      String result = "";
      
      int patternLength = pattern.length();
      int patternStartPos = kmpMatch(baseText, pattern);
      
      if (kmpMatch(baseText, pattern) != -1)
         result = baseText.substring(patternStartPos+patternLength, baseText.length());
      
      return result;
  }
  
  public ArrayList<Integer> matchAllPatternKMP(String baseText, String pattern){
      ArrayList<Integer> result = new ArrayList<Integer>();
      
      int patternStartPos;
      int patternLength = pattern.length();
      
      int firstPatternStartPos = 0;
      while (kmpMatch(baseText, pattern) != -1){
          patternStartPos = kmpMatch(baseText, pattern);
          firstPatternStartPos += patternStartPos;
          result.add(firstPatternStartPos);
          baseText = eraseAndStartFromFirstPattern(baseText, pattern);
          firstPatternStartPos += patternLength ;
          System.out.println(baseText);
      }
      
      return result;
  }
  
  public String boldingFoundText(String text, String pattern){
      String result = "";
      
      
      return result;
  }
  
}