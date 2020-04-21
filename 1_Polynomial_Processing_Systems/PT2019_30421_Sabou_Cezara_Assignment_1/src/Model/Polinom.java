package Model;

import exception.PolinomialOperationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class Polinom  {

    private final ArrayList<Monom> polinome1 =  new ArrayList<Monom>();

    public Polinom() {

    }

    public ArrayList<Monom> getPolinome1(){

        return polinome1;
    }

    public ArrayList<Monom> addMember(Monom newElement){

        polinome1.add(newElement);
        return polinome1;
    }
    public ArrayList<Monom> removeMember(int degree){
        polinome1.remove(polinome1.get(degree));
        return polinome1;
    }
    public Monom deepCopy(Monom m){
        Monom newCopiedMonom = new Monom(m.getCoefficient(),m.getDegree());
        return newCopiedMonom;
    }


    public void printResult(Polinom p){

        Collections.sort(p.getPolinome1());
        for(Monom m : p.getPolinome1()){
            System.out.print(m.toString());

        }
    }

    public String toString(Polinom p ){

        String res = "";
        Collections.sort(p.getPolinome1());
        for(Monom m : p.getPolinome1()){

        res = res + m.toString();
        }

        return res;
    }

    public boolean degreeAlreadyExists(Polinom p, Monom monom ){
        for(Monom m: p.getPolinome1()){
            if(m.getDegree() == monom.getDegree()){
                return true;
            }
        }
        return false;
    }

    public Monom getFirst(Polinom p){
        Collections.sort(p.getPolinome1());

        return p.getPolinome1().get(0);
    }


    public Polinom addPolynomials(Polinom polinome2){
        Polinom result =  new Polinom();
        for(Monom m1 : polinome1){
            result.addMember(deepCopy(m1));
        }

        for(Monom m1: result.getPolinome1()){
            for(Monom m2: polinome2.getPolinome1()){
                if(m1.getDegree() == m2.getDegree()) {
                    m1.setCoefficient(m1.getCoefficient() + m2.getCoefficient());
                }

            }
        }
        for(Iterator<Monom> it = result.getPolinome1().iterator(); it.hasNext();){
            Monom m = it.next();
            if(degreeAlreadyExists(result,m) == false){
                result.addMember(deepCopy(m));
            }
            if(m.getCoefficient() == 0){
                it.remove();
            }
        }

        return result;
    }

    public Polinom subtractPolynomials(Polinom polinome2){
        Polinom result =  new Polinom();
        Polinom negateP2 = new Polinom();
        for(Monom m1 : polinome1){
            result.addMember(deepCopy(m1));
        }
        for(Monom m2: polinome2.getPolinome1()){
            negateP2.addMember(new Monom(-m2.getCoefficient(),m2.getDegree()));
        }
        result = result.addPolynomials(negateP2);
        return result;

    }

    public Polinom multiplyPolynomials(Polinom polinome2){

        Polinom result = new Polinom();

        for(Monom m1: polinome1){
            for(Monom m2: polinome2.getPolinome1()){
                Monom newMonome = new Monom(m1.getCoefficient()*m2.getCoefficient(),m1.getDegree()+m2.getDegree());
                if(degreeAlreadyExists(result,newMonome) == false){
                    result.addMember(newMonome);
                }
                else{
                    for(Monom m: result.getPolinome1()){
                        if(m.getDegree() == newMonome.getDegree()){
                            m.setCoefficient(m.getCoefficient()+newMonome.getCoefficient());
                        }
                    }
                }
            }
        }

        Collections.sort(result.getPolinome1());
        for(Iterator<Monom> it = result.getPolinome1().iterator(); it.hasNext();){
            Monom m = it.next();
            if(m.getCoefficient() == 0){
                it.remove();
            }
        }
        return result;
    }


    public Polinom dividePolynomials(Polinom polinome2) throws PolinomialOperationException{
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();

        Polinom result =  new Polinom();
        for(Monom m1 : polinome1){
            p1.addMember(deepCopy(m1));
        }
        for(Monom m2 : polinome2.getPolinome1()){
            p2.addMember(deepCopy(m2));
        }
        if(p1.getFirst(p1).getDegree() <= polinome2.getFirst(p2).getDegree()){
            throw new PolinomialOperationException("Second polynomial should have a smaller degree than the first one. thanks!");
        }
        Collections.sort(p1.getPolinome1());
        Collections.sort(p2.getPolinome1());
        Polinom pioneer = new Polinom();
        while(getFirst(p1).getDegree()>=getFirst(polinome2).getDegree()){
            if(getFirst(p2).getCoefficient() != 0){
                pioneer.addMember(new Monom(getFirst(p1).getCoefficient()/getFirst(polinome2).getCoefficient(),getFirst(p1).getDegree()-getFirst(polinome2).getDegree()));
            }
            p2 = polinome2.multiplyPolynomials(pioneer);
            p1 = p1.subtractPolynomials(p2);
            pioneer.getPolinome1().clear();
        }

        for(Iterator<Monom> it = p1.getPolinome1().iterator(); it.hasNext();){
            Monom m = it.next();
            if(m.getCoefficient() == 0){
                it.remove();
            }
        }
        return p1;
    }

    public Polinom integratePolynomial(){
        Polinom result =  new Polinom();
        for(Monom m1 : polinome1){
            result.addMember(new Monom(m1.getCoefficient()/(m1.getDegree()+1), m1.getDegree()+1));
        }
        Collections.sort(result.getPolinome1());
        for(Iterator<Monom> it = result.getPolinome1().iterator(); it.hasNext();){
            Monom m = it.next();
            if(m.getCoefficient() == 0){
                it.remove();
            }
        }
        return result;
    }

    public Polinom derivatePolynomial(){
        Polinom result =  new Polinom();
        for(Monom m1 : polinome1){
            result.addMember(new Monom(m1.getCoefficient()*(m1.getDegree()), m1.getDegree()-1));
        }
        Collections.sort(result.getPolinome1());

        for(Iterator<Monom> it = result.getPolinome1().iterator(); it.hasNext();){
            Monom m = it.next();
            if(m.getCoefficient() == 0){
                it.remove();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polinom polinom = (Polinom) o;
        return Objects.equals(polinome1, polinom.getPolinome1());
    }

}
