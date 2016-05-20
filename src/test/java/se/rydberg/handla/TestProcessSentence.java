package se.rydberg.handla;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import se.rydberg.handla.model.AnalyzeCentence;
import se.rydberg.handla.model.TitleDetail;

public class TestProcessSentence {
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject(){
        String incomingItem = "potatis 2 kg";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("potatis"));
        assertThat(details.getQuantity(),equalTo("2"));
        assertThat(details.getUnit(),equalTo("kg"));
    }

    @Test
    public void shouldSplitAndReturnADetailedTitleObject2(){
        String incomingItem = "potatis 1,5 g";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("potatis"));
        assertThat(details.getQuantity(),equalTo("1,5"));
        assertThat(details.getUnit(),equalTo("g"));
    }
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject3(){
        String incomingItem = "1 kg potatis";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("potatis"));
        assertThat(details.getQuantity(),equalTo("1"));
        assertThat(details.getUnit(),equalTo("kg"));
    }
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject4(){
        String incomingItem = "1 flaska mjölk";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("mjölk"));
        assertThat(details.getQuantity(),equalTo("1"));
        assertThat(details.getUnit(),equalTo("flaska"));
    }
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject5(){
        String incomingItem = "Sandpapper";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("Sandpapper"));
        assertThat(details.getQuantity(),equalTo(null));
        assertThat(details.getUnit(),equalTo(null));
    }
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject6(){
        String incomingItem = "2 rullar sandpapper";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("rullar sandpapper"));
        assertThat(details.getQuantity(),equalTo("2"));
        assertThat(details.getUnit(),equalTo(null));
    }
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject7(){
        String incomingItem = "2kg potatis fasta";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("potatis fasta"));
        assertThat(details.getQuantity(),equalTo("2"));
        assertThat(details.getUnit(),equalTo("kg"));
    }
    
    @Test
    public void shouldSplitAndReturnADetailedTitleObject8(){
        String incomingItem = "2 paket potatis fasta";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
        assertThat(details.getTitle(),equalTo("potatis fasta"));
        assertThat(details.getQuantity(),equalTo("2"));
        assertThat(details.getUnit(),equalTo("paket"));
    }
}    
    


    
