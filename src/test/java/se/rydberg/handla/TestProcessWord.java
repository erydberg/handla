package se.rydberg.handla;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.rydberg.handla.model.AnalyzeWord;

public class TestProcessWord {
    
    @Test
    public void shouldTestIfWordIsBothNumericAndLetters(){
        String incomingText = "12g";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        
        assertTrue(analyzeWord.isNumericAndString(incomingText));
    }
    
    @Test
    public void shouldTestIfWordIsBothNumericAndLettersButFail(){
        String incomingText = "12";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        
        assertFalse(analyzeWord.isNumericAndString(incomingText));
    }
    
    @Test
    public void shouldTestIfWordIsBothNumericAndLettersButFailAgain(){
        String incomingText = "g";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        
        assertFalse(analyzeWord.isNumericAndString(incomingText));
    }
}
