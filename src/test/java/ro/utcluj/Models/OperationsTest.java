package ro.utcluj.Models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void addOperationTest() {
        Polynomial result = new Polynomial("2x^4-3x^3-14x^2+28x-24");
        assertAll(()->assertTrue(Operations.addOrSubtractOperation(new Polynomial("2x^4-3x^3-15x^2+32x-12"),
                    new Polynomial("x^2-4x-12"),0).equals(result),"The result of adding x^4-3x^3-15x^2+32x-12 and " +
                "x^2-4x-12 is 2x^4-3x^3-15x^2+32x-12"),
                ()->assertTrue(Operations.addOrSubtractOperation(new Polynomial("-5"),
                new Polynomial("4"),0).equals(new Polynomial("-1")),"The result of adding " +
                "-5 and 4 is -1"));
    }

    @Test
    void subtractOperationTest(){
        Polynomial result = new Polynomial("-x^7-4x^5+4x^4-3x-35");
        assertAll(() -> assertTrue(Operations.addOrSubtractOperation(new Polynomial("2x^5+3x^4-x^2-20"),
                    new Polynomial("x^7+6x^5-x^4-x^2+3x+15"),1).equals(result),"The result of subtracting " +
                        "2x^5+3x^4-x^2-20 and x^7+6x^5-x^4-x^2+3x+15 is -x^7-4x^5+4x^4-3x-35"),
                () -> assertTrue(Operations.addOrSubtractOperation(new Polynomial("7x^6-2x^3+2x+5"),
                       new Polynomial("5x^7+2x^6-3x^3+2x+7"),1).
                       equals(new Polynomial("-5x^7+5x^6+x^3-2")),"The result of subtracting 7x^6-2x^3+2x+5 " +
                       "with 5x^7+2x^6-3x^3+2x+7 is -5x^7+5x^6+x^3-2"));
    }

    @Test
    void derivativeTest() {
        assertAll(() -> assertTrue(Operations.derivative(new Polynomial("3x^5+7x^4-9x^2+10"))
                .equals(new Polynomial("15x^4+28x^3-18x")),"The derivative of the 3x^5+7x^4-9x^2+10 polynomial " +
                "is 15x^4+28x^3-18x"),
                () -> assertTrue(Operations.derivative(new Polynomial("-20x^3+17x-15"))
                        .equals(new Polynomial("-60x^2+17")),"The derivative of the -20x^3+17x-15 is " +
                        "-60x^2+17"));
    }

    @Test
    void integrateTest() {
        assertAll(() -> assertTrue(Operations.integrate(new Polynomial("12x^7-9x^3+7x^2+x+12")).toString()
                .equals("1.5x^8-2.25x^4+2.333x^3+0.5x^2+12x"),"The integral of 12x^7-9x^3+7x^2+x+12 " +
                "is 1.5x^8-2.25x^4+2.333x^3+0.5x^2+12x"),
                () -> assertTrue(Operations.integrate(new Polynomial("-5x^4+3x^2-x-1")).toString()
                        .equals("-x^5+x^3-0.5x^2-x"),"The integral of -5x^4+3x^2-x-1 is -x^5+x^3-0.5x^2-x"));
    }

    @Test
    void multiply() {
           assertAll(() -> assertTrue(Operations.multiply(new Polynomial("x^5+3x^4-7x^2+3x-1"),
                   new Polynomial("x^3+2x^2-x+1"))
                   .equals(new Polynomial("x^8+5x^7+5x^6-9x^5-8x^4+12x^3-12x^2+4x-1")),"The multiplication " +
                   "of x^5+3x^4-7x^2+3x-1 by x^3+2x^2-x+1 is x^8+5x^7+5x^6-9x^5-8x^4+12x^3-12x^2+4x-1"),
                   () -> assertTrue(Operations.multiply(new Polynomial("0"),new Polynomial("x^3+2x^2-x+1"))
                           .equals(new Polynomial("0"))," The multiplication of 0 by x^3+2x^2-x+1 is 0"));
    }

    @Test
    void division()  {
        List<Polynomial> result = new ArrayList<>();
        result.add(new Polynomial("3x^3-6x^2+7x-14"));
        result.add(new Polynomial("31"));

        List<Polynomial> result2 = new ArrayList<>();
        result2.add(new Polynomial("x^2+9x+60"));
        result2.add(new Polynomial("424"));
        assertAll(() -> assertTrue(Operations.division(new Polynomial("3x^4-5x^2+3"), new Polynomial("x+2"))
                .containsAll(result)),
                () -> assertTrue(Operations.division(new Polynomial("x^3+2x^2-3x+4"),new Polynomial("x-7"))
                        .containsAll(result2)));
    }
}