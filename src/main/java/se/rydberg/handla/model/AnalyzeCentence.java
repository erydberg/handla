package se.rydberg.handla.model;

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
                TextNumber textNumber = analyze.splitNumericAndStingIfFirstLetterIsDigit(word);
                details.setQuantity(textNumber.getNumber());
                if(analyze.isUnit(textNumber.getText())){
                    details.setUnit(textNumber.getText());
                }else{
                    title.append(textNumber.getText());
                    title.append(" ");
                }
            }
            else{
                title.append(word);
                title.append(" ");
            }
        }
        details.setTitle(title.toString().trim());
        return details;
    }
}
