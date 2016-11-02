package se.rydberg.handla.model;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArticleTest {
    
    @Test
    public void should_return_title_value_unit(){
        Article article = new Article();
        String incomingItem = "potatis 2 kg";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
    
        article.setTitle(details.getTitle());
        article.setQuantity(details.getQuantity());
        article.setUnit(details.getUnit());
        
        assertThat(article.getCompleteTitle(),equalTo(incomingItem));
    }
    
    @Test
    public void should_return_title_value(){
        Article article = new Article();
        String incomingItem = "potatis 2";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
    
        article.setTitle(details.getTitle());
        article.setQuantity(details.getQuantity());
        article.setUnit(details.getUnit());
        
        assertThat(article.getCompleteTitle(),equalTo(incomingItem));
    }
    
    @Test
    public void should_return_title(){
        Article article = new Article();
        String incomingItem = "potatis";
        AnalyzeCentence analyze = new AnalyzeCentence();
        TitleDetail details = analyze.splitCentence(incomingItem);
    
        article.setTitle(details.getTitle());
        article.setQuantity(details.getQuantity());
        article.setUnit(details.getUnit());
        
        assertThat(article.getCompleteTitle(),equalTo(incomingItem));
    }
}
