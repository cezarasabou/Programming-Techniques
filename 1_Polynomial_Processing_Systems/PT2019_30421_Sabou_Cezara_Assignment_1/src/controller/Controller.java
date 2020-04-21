package controller;

import Model.Polinom;
import Model.ProcessText;
import exception.InvalidInputException;
import exception.PolinomialOperationException;

public class Controller  {



    public String add(String p1, String p2) throws InvalidInputException {
        Polinom res = new Polinom();
        Polinom firstPolinom = ProcessText.parseText(p1);
        Polinom secondPolinom = ProcessText.parseText(p2);
        res = firstPolinom.addPolynomials(secondPolinom);
        return res.toString(res);
    }

    public String subtract(String p1, String p2) throws InvalidInputException{
        Polinom res;
        Polinom firstPolinom = ProcessText.parseText(p1);
        Polinom secondPolinom = ProcessText.parseText(p2);
        res = firstPolinom.subtractPolynomials(secondPolinom);
        return res.toString(res);
    }

    public String multiply(String p1, String p2) throws InvalidInputException{
        Polinom res = new Polinom();
        Polinom firstPolinom = ProcessText.parseText(p1);
        Polinom secondPolinom = ProcessText.parseText(p2);
        res = firstPolinom.multiplyPolynomials(secondPolinom);
        return res.toString(res);
    }

    public String divide(String p1, String p2) throws InvalidInputException, PolinomialOperationException{
        Polinom res = new Polinom();
        Polinom firstPolinom = ProcessText.parseText(p1);
        Polinom secondPolinom = ProcessText.parseText(p2);
        res = firstPolinom.dividePolynomials(secondPolinom);
        return res.toString(res);
    }

    public String integrate(String p1) throws InvalidInputException {
        Polinom res = new Polinom();
        Polinom firstPolinom = ProcessText.parseText(p1);
        res = firstPolinom.integratePolynomial();
        return res.toString(res);
    }

    public String differentiate(String p1) throws InvalidInputException {
        Polinom res = new Polinom();
        Polinom firstPolinom = ProcessText.parseText(p1);
        res = firstPolinom.derivatePolynomial();
        System.out.println("am derivat" + res.toString(res));
        return res.toString(res);
    }


}
