package lab1;

import Model.Monom;
import Model.Polinom;
import Model.ProcessText;
import View.View;
import controller.Controller;

public class MainClass {
    public static void main(String[] args){

       /** int c;
        int d;
        Scanner s = new Scanner(System.in);
        c = s.nextInt();
        d = s.nextInt();

        Monom m = new Monom(c,d);
        System.out.println(m.toString());
        int[] coef =  {1, 2, 3};
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();


        p1.addMember(new Monom(12,4));
        p1.addMember(new Monom(-6,3));
        p1.addMember(new Monom(0,2));
        p1.addMember(new Monom(2,1));
        p1.addMember(new Monom(7,0));


        p2.addMember(new Monom(3,2));
        p2.addMember(new Monom(2,0));

        System.out.println("adunare");
        p1.printResult(p1.addPolynomials(p2));
        System.out.println();
        System.out.println("pana aici am incercat scadere");
        p1.printResult(p1.subtractPolynomials(p2));
        System.out.println();
        System.out.println("pana aici am incercat inmultieww");
        p1.printResult(p1.multiplyPolynomials(p2));
        System.out.println();
        System.out.println("pana aici am incercat impartiree");
        p1.printResult(p1.dividePolynomials(p2));
        System.out.println();
        System.out.println("pana aici am incercat integrare");
        p1.printResult(p1.integratePolynomial());
        System.out.println();
        System.out.println("pana aici am incercat integrare");
        p1.printResult(p1.derivatePolynomial());
        System.out.println();


        String s = "5x^3-2x^2+3x^1+2x^0";
        ProcessText p = new ProcessText();
        p.parseText(s);**/


        Controller c = new Controller();
        View v = new View(c);
   }
}
