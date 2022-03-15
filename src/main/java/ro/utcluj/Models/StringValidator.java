package ro.utcluj.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {


    public boolean stringValidate(String input) throws Exception {
        if(input.equals("")){
            throw new Exception("Incorrect input!");
        }

        Pattern pattern = Pattern.compile("([a-zA-Z])|([^\\+\\-\\*^0-9])");
        Matcher matcher= pattern.matcher(input);
        while(matcher.find()){
            if(!matcher.group(1).equals("x") || matcher.group(2)!=null) throw new Exception("Incorrect input!");
        }
        return true;
    }
}
