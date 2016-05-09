package se.rydberg.handla.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<String> approvedUnits = Arrays.asList("gram","g","kg","hg","dl","l","liter","deciliter","ml","styck","st","stycken","burk","burkar","mililiter", "flaska","flaskor"); 
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

        
//        Pattern p = Pattern.compile("-?[\\d\\,]+");
//        List<String> numbers = new ArrayList<String>();

//        while (m.find()) {
//            numbers.add(m.group());
//        }
//
//        Pattern patternText = Pattern.compile("[a-zA-Z]+");
//        List<String> text = new ArrayList<String>();
//        Matcher t = patternText.matcher(word);
//
//        while (t.find()) {
//            System.out.println("t.läggertill");
//            text.add(t.group());
//        }
//        System.out.println("text: " + text);
//        if(!numbers.isEmpty() && !text.isEmpty()){
//            return true;
//        }else{
//            return false;
//        }
    }



    public Map<Double,String> splitNumericAndString(String word) {
        //kollar om första tecknet är en siffra, annars kanske vi inte ska ta hänsyn till det hela
        
        if(Character.isDigit(word.charAt(0))){
            Pattern p = Pattern.compile("-?[\\d\\,]+");
            List<String> numbers = new ArrayList<String>();
            Matcher m = p.matcher(word);

            while (m.find()) {
                numbers.add(m.group());
            }

            Pattern patternText = Pattern.compile("[a-zA-Z]+");
            List<String> text = new ArrayList<String>();
            Matcher t = patternText.matcher(word);

            while (t.find()) {
                System.out.println("t.läggertill");
                text.add(t.group());
            }
            System.out.println("text: " + text);

        }


        return new HashMap<Double,String>();
    }
}
