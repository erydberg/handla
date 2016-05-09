package se.rydberg.handla.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class AnalyzeCentence {
    StringBuilder title;
    TitleDetail details;
    
        
    public AnalyzeCentence(){
        title = new StringBuilder();
        details = new TitleDetail();
    }
        
    public TitleDetail splitCentence(String sentence) {
        String[] words = sentence.split(" ");
        AnalyzeWord analyze = new AnalyzeWord();
        
        for(String word:words){
            System.out.println("Analyserar ordet: " + word);
            
            if(analyze.isNumeric(word)){
                details.setQuantity(word);
            }else if(analyze.isUnit(word)){
                details.setUnit(word);
            }else if(analyze.isNumericAndString(word)){
                //TODO - split into quantity and unit
            }
            else{
                title.append(word);
                title.append(" ");
            }
        }
        details.setTitle(title.toString().trim());
        return details;
    }

    //TODO fundera på om jag ska ha kvar, används ej
    private void setQuantityFromString(String word) {
        Locale swedishLocale = new Locale("sv", "SE");
        NumberFormat nf = NumberFormat.getInstance(swedishLocale);
        Number number;
        try {
            number = nf.parse(word);
           // details.setQuantity(number.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
