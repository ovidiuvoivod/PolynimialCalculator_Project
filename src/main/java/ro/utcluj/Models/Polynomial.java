package ro.utcluj.Models;

import ro.utcluj.Models.Monomial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private List<Monomial> monomials = new ArrayList<>();

    public Polynomial(String polynomialInput) {
        polynomialInput = polynomialInput.replaceAll("\\s", "");
        Pattern pattern = Pattern.compile("([+-]?([0-9]+)?)\\*?x(\\^([0-9]+)?)?|([+-]?[0-9]+)");
        Matcher matcher = pattern.matcher(polynomialInput);
        while (matcher.find()) {
            Number auxiliaryCoefficient=0;
            int auxiliaryPower=0;
            if (matcher.group(5) != null) {
                auxiliaryCoefficient=Integer.parseInt(matcher.group(5));
                auxiliaryPower=0;
            } else {
                if (matcher.group(4) != null) {
                        auxiliaryPower= Integer.parseInt(matcher.group(4));
                        if(matcher.group(1).equals("")){
                            auxiliaryCoefficient=1;
                        } else if(matcher.group(1).equals("-")){
                            auxiliaryCoefficient=-1;
                        } else if(matcher.group(2)==null){
                            auxiliaryCoefficient=1;
                        } else auxiliaryCoefficient=Integer.parseInt(matcher.group(1));
                } else {
                    if (matcher.group(2) != null) {
                        auxiliaryCoefficient=Integer.parseInt(matcher.group(1));
                        auxiliaryPower=1;
                    } else {
                        auxiliaryCoefficient=Integer.parseInt(matcher.group(1)+"1");
                        auxiliaryPower=1;
                    }
                }
            }
            Monomial monomial=new Monomial(auxiliaryCoefficient,auxiliaryPower);
            monomials.add(monomial);
        }
    }

    public Polynomial(){

    }

    public List<Monomial> getMonomials() {
        return monomials;
    }

    public Monomial getMonomialFromPolynomial(int position){
        return this.getMonomials().get(position);
    }

    public Number getCoefficientFromMonomial(int position){
        return this.monomials.get(position).getCoefficient();
    }

    public int getPowerFromMonomial(int position){
        return this.monomials.get(position).getPower();
    }

    public void addMonomial(Monomial monomial){
        this.monomials.add(monomial);
    }

    public boolean equals(Object o){
        if(this.monomials.size()==((Polynomial)o).monomials.size()){
            Iterator<Monomial> iterator1 = monomials.iterator();
            Iterator<Monomial> iterator2 = ((Polynomial) o).getMonomials().iterator();
            while(iterator1.hasNext() && iterator2.hasNext()){
                if(!iterator1.next().equals(iterator2.next())){
                    return false;
                }
            }
            return true;
        }
        else return false;
    }

    public String toString(){
        String output="";
        if(this.monomials.size()==0)
            output= "0";
        else{
            Operations.sort(this);

            for (Monomial monomial: this.monomials){

                if(monomial.equals(this.monomials.get(0)) && monomial.getCoefficient().doubleValue()>0){
                    output=monomial.toString();
                    output=output.substring(1);
                }
                else
                    output=output + monomial.toString();
            }
        }

        return output;
    }
}
