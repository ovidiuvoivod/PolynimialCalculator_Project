package ro.utcluj.Models;

public class Monomial implements Comparable<Monomial> {

    private Number coefficient;
    private int power;

    public Monomial(Number coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Monomial(){

    }

    public Number getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public int compareTo(Monomial o) {
        if (this.power < o.power) return 1;
        else return -1;
    }

    public boolean equals(Object o){
        if(this.coefficient.doubleValue()==((Monomial) o).getCoefficient().doubleValue()&&
                this.power==((Monomial) o).getPower()){
            return true;
        }
        return false;
    }

    public Monomial division(Monomial divisor){
        this.power= this.power-divisor.getPower();
        this.coefficient=this.getCoefficient().doubleValue()/divisor.getCoefficient().doubleValue();
        return this;
    }

    public String toString() {
        String outputString = "";
        if(this.coefficient.doubleValue()==0 && this.power==0){
            return "0";
        }
        else{
            if(this.coefficient.doubleValue()%1==0){
                this.coefficient=this.coefficient.intValue();
            }
            else{
                this.coefficient=Math.round(this.coefficient.doubleValue()* 1000.0) / 1000.0;
            }
            if (this.coefficient.doubleValue()> 0.0 && this.coefficient.doubleValue()!=1.0) {
                outputString = (this.power > 1) ? ("+" + this.coefficient + "x^" + this.power) :
                        ((this.power == 1) ? ("+" + this.coefficient + "x") : ("+" + this.coefficient + ""));
            } else if (this.coefficient.doubleValue() == 1.0) {
                outputString = (this.power > 1) ? ("+" + "x^" + this.power) : ((this.power == 1) ? ("+" + "x") : ("+1"));
            } else if (this.coefficient.doubleValue() == -1) {
                outputString = (this.power > 1) ? ("-x^" + this.power) : ((this.power == 1) ? ("-x") : ("-1"));
            } else if (this.coefficient.doubleValue() < 0.0 && this.coefficient.doubleValue()!=-1.0) {
                outputString = (this.power > 1) ? (this.coefficient + "x^" + this.power) :
                        ((this.power == 1) ? (this.coefficient + "x") : (this.coefficient + ""));
            }
            return outputString;
        }

    }


}
