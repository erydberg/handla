package se.rydberg.handla.model;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeWord {



    public AnalyzeWord(){

    }

    public boolean isNumeric(String word) {
        System.out.println("testar om " + word + " är numeriskt");
        return word.matches("-?\\d+(\\,\\d+)?");
    }

    public boolean isUnit(String word){
        //lägga till mer logik för att hitta om det är ett känt begrepp
        List<String> approvedUnits = Arrays.asList("gram","g","kg","hg","hekto","dl","l","liter","deciliter","ml","styck","st","stycken","burk","burkar","mililiter", "flaska","flaskor"); 
        for(String unit:approvedUnits){
            if(unit.equalsIgnoreCase(word)){
                return true;
            }
        }
        return false;
    }
    //To find all words like 12g and 5kg
    public boolean isNumericAndString(String word){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])([a-z0-9_-]+)$");
        Matcher m = p.matcher(word);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }

    public TextNumber splitNumericAndStingIfFirstLetterIsDigit(String word){
        TextNumber textNumber = new TextNumber();
        if(Character.isDigit(word.charAt(0))){
            return textNumber = splitNumericAndString(word);
        }else{
            textNumber.setText(word);
            textNumber.setNumber("");
            return textNumber;
        }
    }



    public TextNumber splitNumericAndString(String word) {
        StringBuilder number = new StringBuilder();
        StringBuilder text = new StringBuilder();

        for(Character character:word.toCharArray()){
            if(Character.isDigit(character)){
                number.append(character);
            }else{
                text.append(character);
            }
        }

        TextNumber textNumber = new TextNumber();
        textNumber.setNumber(number.toString());
        textNumber.setText(text.toString());;

        return textNumber;
    }
}
