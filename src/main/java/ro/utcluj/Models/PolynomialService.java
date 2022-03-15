package ro.utcluj.Models;

import java.util.ArrayList;
import java.util.List;

public class PolynomialService {

    public void fixRedundantTerms(Polynomial polynomial) {
        Polynomial auxiliaryPolynomial = new Polynomial();
        Operations.sort(polynomial);
        auxiliaryPolynomial.getMonomials().addAll(polynomial.getMonomials());
        auxiliaryPolynomial.getMonomials().remove(polynomial.getMonomials().get(0));
        List<Monomial> monomialsToRemove = new ArrayList<>();
        for (Monomial monomial : polynomial.getMonomials()) {
            for (Monomial auxiliaryMonomial : auxiliaryPolynomial.getMonomials()) {
                if (!monomial.equals(auxiliaryMonomial) && auxiliaryMonomial.getPower() == monomial.getPower()) {
                    monomial.setCoefficient(monomial.getCoefficient().intValue() + auxiliaryMonomial.getCoefficient().intValue());
                    auxiliaryMonomial.setPower(-1);
                    monomialsToRemove.add(auxiliaryMonomial);
                }
            }
        }
        polynomial.getMonomials().removeAll(monomialsToRemove);
    }

    public void checkForZeros(Polynomial polynomial){
        List<Monomial> monomialsToRemove = new ArrayList<>();
        for(Monomial monomial: polynomial.getMonomials()){
            if(monomial.getCoefficient().doubleValue()==0.0){
                monomialsToRemove.add(monomial);
            }
        }
        polynomial.getMonomials().removeAll(monomialsToRemove);
    }
}
