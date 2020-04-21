package Model;

public class Monom implements Comparable<Monom>{

    private double Coefficient;
    private int Degree;


    public Monom() {
    }

    public Monom(double coefficient,int degree){

        this.setCoefficient(coefficient);
        this.setDegree(degree);
    }

    public double getCoefficient() {
        return Coefficient;
    }

    public void setCoefficient(double coefficient) {
        Coefficient = coefficient;
    }

    public int getDegree() {
        return Degree;
    }

    public void setDegree(int degree) {
        Degree = degree;
    }
    public Monom getMonom(){
        return this;
    }

    @Override
    public int compareTo(Monom o) {
        return o.getDegree() - this.getDegree();
    }

    @Override
    public String toString() {
        String res = "";

        if(Coefficient > 0){
            res = " + ";
        }

        if(Degree == 0){
            res = res + Coefficient + " ";
        }
        else if(Coefficient == 0){
            res = "";
        }
        else{
            res =  res + Coefficient + "x^" +Degree + " ";
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monom monom = (Monom) o;
        return Double.compare(monom.Coefficient, Coefficient) == 0 &&
                Degree == monom.Degree;
    }


}
