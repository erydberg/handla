package se.rydberg.handla;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.rydberg.handla.model.AnalyzeWord;

public class TestFindUnit {
    
    @Test
    public void shouldReturnUnitIfItIsAunit(){
        String word1 = "gram";
        String word2 = "kg";
        String word3 = "kalle";
        String word4 = "5kg";
        
        AnalyzeWord analyze = new AnalyzeWord();
        
        assertTrue(analyze.isUnit(word1));
        assertTrue(analyze.isUnit(word2));
        assertFalse(analyze.isUnit(word3));
        assertFalse(analyze.isUnit(word4));
    }
}
