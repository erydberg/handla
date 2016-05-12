package se.rydberg.handla;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import se.rydberg.handla.model.AnalyzeWord;
import se.rydberg.handla.model.TextNumber;

public class TestSplitNumberAndTextInOneWord {
    
    @Test
    public void shouldReturnTextAndNumberSeperated(){
        String incomingText = "12g";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        TextNumber textNumber = analyzeWord.splitNumericAndString(incomingText);
        assertThat(textNumber.getText(),equalTo("g"));
        assertThat(textNumber.getNumber(),equalTo("12"));
    }
    
    @Test
    public void shouldReturnOnlyText(){
        String incomingText = "gram";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        TextNumber textNumber = analyzeWord.splitNumericAndString(incomingText);
        assertThat(textNumber.getText(),equalTo("gram"));
        assertThat(textNumber.getNumber(),equalTo(""));
    }
    
    @Test
    public void shouldReturnOnlyNumber(){
        String incomingText = "15";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        TextNumber textNumber = analyzeWord.splitNumericAndString(incomingText);
        assertThat(textNumber.getText(),equalTo(""));
        assertThat(textNumber.getNumber(),equalTo("15"));
    }
    
    @Test
    public void shouldReturnAsTextSinceNumberIsNotTheFirstLetter(){
        String incomingText = "bohus15gram";
        AnalyzeWord analyzeWord = new AnalyzeWord();
        TextNumber textNumber = analyzeWord.splitNumericAndStingIfFirstLetterIsDigit(incomingText);
        assertThat(textNumber.getText(),equalTo("bohus15gram"));
        assertThat(textNumber.getNumber(),equalTo(""));
    }
}

