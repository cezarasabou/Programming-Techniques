package Model;

import exception.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessText {

    public static Polinom parseText(String text) throws InvalidInputException{
        String regex = "(-?\\b\\d+)[xX]\\^(-?\\d+\\b)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(text);

//      if(!m.find()) {
//          throw new InvalidInputException("Invalid input! Please press the clear button and try again. thanks!");
//       }

        final Polinom freshPolynomial = new Polinom();
        int lastElement = 0;
        while(m.find()){

            System.out.println("coef: "+ m.group(1));
            System.out.println("degree: "+ m.group(2));
            lastElement = m.end();

            freshPolynomial.addMember(new Monom(Double.parseDouble(m.group(1)),Integer.parseInt(m.group(2))));
        }
        if(lastElement != text.length()){
            throw new InvalidInputException("Invalid input! Please press the clear button and try again. thanks!");
        }

        return freshPolynomial;

    }
}
