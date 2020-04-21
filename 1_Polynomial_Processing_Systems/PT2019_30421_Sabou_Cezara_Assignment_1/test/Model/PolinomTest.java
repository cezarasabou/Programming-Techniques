package Model;

import exception.PolinomialOperationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PolinomTest {
    private Polinom p1 = new Polinom();
    private Polinom p2 = new Polinom();

    @Before
    public void setPolynomials() {


        p1.addMember(new Monom(12,4));
        p1.addMember(new Monom(-6,3));
        p1.addMember(new Monom(0,2));
        p1.addMember(new Monom(2,1));
        p1.addMember(new Monom(7,0));


        p2.addMember(new Monom(3,2));
        p2.addMember(new Monom(2,0));
    }

    @Test
    public void addPolynomials() {

        Polinom result = new Polinom();
        result.addMember(new Monom(12,4));
        result.addMember(new Monom(-6,3));
        result.addMember(new Monom(3,2));
        result.addMember(new Monom(2,1));
        result.addMember(new Monom(9,0));

        Assert.assertEquals(result.getPolinome1(),p1.addPolynomials(p2).getPolinome1());
    }

    @Test
    public void subtractPolynomials() {
        Polinom result = new Polinom();
        result.addMember(new Monom(12,4));
        result.addMember(new Monom(-6,3));
        result.addMember(new Monom(-3,2));
        result.addMember(new Monom(2,1));
        result.addMember(new Monom(5,0));

        Assert.assertEquals(result.getPolinome1(),p1.subtractPolynomials(p2).getPolinome1());
    }

    @Test
    public void multiplyPolynomials() {
        Polinom result = new Polinom();
        result.addMember(new Monom(36,6));
        result.addMember(new Monom(-18,5));
        result.addMember(new Monom(24,4));
        result.addMember(new Monom(-6,3));
        result.addMember(new Monom(21,2));
        result.addMember(new Monom(4,1));
        result.addMember(new Monom(14,0));

        Assert.assertEquals(result.getPolinome1(),p1.multiplyPolynomials(p2).getPolinome1());

    }

    @Test
    public void dividePolynomials() throws PolinomialOperationException {
        Polinom result = new Polinom();
        result.addMember(new Monom(6,1));
        result.addMember(new Monom(12.333333333333332,0));

        Assert.assertEquals(result.getPolinome1(),p1.dividePolynomials(p2).getPolinome1());

    }

    @Test
    public void integratePolynomial() {
        Polinom result = new Polinom();
        result.addMember(new Monom(2.4,5));
        result.addMember(new Monom(-1.5,4));
        result.addMember(new Monom(1,2));
        result.addMember(new Monom(7,1));

        Assert.assertEquals(result.getPolinome1(),p1.integratePolynomial().getPolinome1());
    }

    @Test
    public void derivatePolynomial() {
        Polinom result = new Polinom();
        result.addMember(new Monom(48,3));
        result.addMember(new Monom(-18,2));
        result.addMember(new Monom(2,0));

        Assert.assertEquals(result.getPolinome1(),p1.derivatePolynomial().getPolinome1());

    }
}