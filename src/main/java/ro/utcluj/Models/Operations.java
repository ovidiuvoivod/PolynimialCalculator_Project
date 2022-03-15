package ro.utcluj.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {
    private static final PolynomialService polynomialService = new PolynomialService();

    public static void sort(Polynomial polynomial) {
        Collections.sort(polynomial.getMonomials());
    }

    public static Polynomial addOrSubtractOperation(Polynomial firstPolynomial, Polynomial secondPolynomial, int operation) {
        Polynomial resultPolynomial = new Polynomial();
        List<Monomial> monomialsToRemove = new ArrayList<>();
        polynomialService.fixRedundantTerms(firstPolynomial);
        polynomialService.fixRedundantTerms(secondPolynomial);
        resultPolynomial.getMonomials().addAll(firstPolynomial.getMonomials());
        for (Monomial resultMonomial : resultPolynomial.getMonomials()) {
            for (Monomial secondMonomial : secondPolynomial.getMonomials()) {
                if (secondMonomial.getPower() == resultMonomial.getPower()) {
                    double auxiliaryCoefficient = (operation == 0) ? (resultMonomial.getCoefficient().doubleValue() +
                            secondMonomial.getCoefficient().doubleValue()) : (resultMonomial.getCoefficient().doubleValue() -
                            secondMonomial.getCoefficient().doubleValue());
                    if (auxiliaryCoefficient != 0.0) {
                        resultMonomial.setCoefficient(auxiliaryCoefficient);
                        monomialsToRemove.add(secondMonomial);
                    }
                }
            }
        }
        secondPolynomial.getMonomials().removeAll(monomialsToRemove);
        if (operation != 0) {
            for (Monomial monomial : secondPolynomial.getMonomials()) {
                monomial.setCoefficient(-monomial.getCoefficient().doubleValue());
            }
        }
        resultPolynomial.getMonomials().addAll(secondPolynomial.getMonomials());
        polynomialService.fixRedundantTerms(resultPolynomial);
        polynomialService.checkForZeros(resultPolynomial);
        return resultPolynomial;
    }

    public static Polynomial derivative(Polynomial polynomial) {
        Polynomial resultPolynomial = new Polynomial();
        polynomialService.fixRedundantTerms(polynomial);
        List<Monomial> monomialsToRemove = new ArrayList<>();
        resultPolynomial.getMonomials().addAll(polynomial.getMonomials());
        for (Monomial monomial : resultPolynomial.getMonomials()) {
            if (monomial.getPower() == 0) {
                monomialsToRemove.add(monomial);
            } else {
                monomial.setCoefficient(monomial.getCoefficient().doubleValue() * monomial.getPower());
                monomial.setPower(monomial.getPower() - 1);
            }
        }
        resultPolynomial.getMonomials().removeAll(monomialsToRemove);
        return resultPolynomial;
    }

    public static Polynomial integrate(Polynomial polynomial) {
        Polynomial resultPolynomial = new Polynomial();
        polynomialService.fixRedundantTerms(polynomial);
        resultPolynomial.getMonomials().addAll(polynomial.getMonomials());
        for (Monomial monomial : resultPolynomial.getMonomials()) {
            if (monomial.getPower() != 0) {
                monomial.setPower(monomial.getPower() + 1);
                Number result = Math.round((monomial.getCoefficient().doubleValue() / monomial.getPower()) * 1000.0) / 1000.0;
                monomial.setCoefficient(result);
            } else {
                monomial.setPower(1);
            }
        }
        return resultPolynomial;
    }

    public static Polynomial multiply(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        Polynomial resultPolynomial = new Polynomial();
        for (Monomial firstMonomial : firstPolynomial.getMonomials()) {
            for (Monomial secondMonomial : secondPolynomial.getMonomials()) {
                Monomial monomial = new Monomial();
                monomial.setPower(firstMonomial.getPower() + secondMonomial.getPower());
                monomial.setCoefficient(firstMonomial.getCoefficient().doubleValue() * secondMonomial.getCoefficient().doubleValue());
                resultPolynomial.getMonomials().add(monomial);
            }
        }
        polynomialService.fixRedundantTerms(resultPolynomial);
        polynomialService.checkForZeros(resultPolynomial);
        if (resultPolynomial.getMonomials().size() == 0) {
            resultPolynomial.getMonomials().add(new Monomial(0, 0));
        }
        return resultPolynomial;

    }

    public static List<Polynomial> division(Polynomial firstPolynomial, Polynomial secondPolynomial) throws Exception {
        polynomialService.checkForZeros(firstPolynomial);
        polynomialService.checkForZeros(secondPolynomial);
        if (secondPolynomial.getMonomials().size() == 0) throw new Exception("Error! Division by zero!");
        else if (firstPolynomial.getMonomials().size() == 0) return null;
        else {
            Operations.sort(firstPolynomial);
            Operations.sort(secondPolynomial);
            List<Polynomial> result = new ArrayList<>();
            Polynomial copyOfSecondPolynomial = new Polynomial();
            copyOfSecondPolynomial.getMonomials().addAll(secondPolynomial.getMonomials());
            Polynomial remainderPolynomial = new Polynomial();
            Polynomial quotientPolynomial = new Polynomial();
            remainderPolynomial.getMonomials().addAll(firstPolynomial.getMonomials());
            while (remainderPolynomial.getMonomialFromPolynomial(0).getPower() >= secondPolynomial.getMonomialFromPolynomial(0).getPower()) {
                Polynomial auxiliaryPolynomial = new Polynomial();
                Monomial auxiliaryMonomial = new Monomial(remainderPolynomial.getCoefficientFromMonomial(0), remainderPolynomial.getPowerFromMonomial(0));
                auxiliaryPolynomial.addMonomial(auxiliaryMonomial);
                auxiliaryPolynomial.getMonomialFromPolynomial(0).division(secondPolynomial.getMonomialFromPolynomial(0));
                quotientPolynomial.addMonomial(auxiliaryPolynomial.getMonomialFromPolynomial(0));
                Polynomial subtractFromRemainder = Operations.multiply(auxiliaryPolynomial, copyOfSecondPolynomial);
                copyOfSecondPolynomial.getMonomials().clear();
                copyOfSecondPolynomial.getMonomials().addAll(secondPolynomial.getMonomials());
                Polynomial auxSubtract = Operations.addOrSubtractOperation(remainderPolynomial, subtractFromRemainder, 1);// scad din rest
                remainderPolynomial.getMonomials().clear();
                remainderPolynomial.getMonomials().addAll(auxSubtract.getMonomials());
                if (remainderPolynomial.getMonomials().size() == 0) break;
            }
            result.add(quotientPolynomial);
            result.add(remainderPolynomial);
            return result;
        }
    }
}
