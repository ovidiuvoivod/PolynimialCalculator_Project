package ro.utcluj.Controllers;

import ro.utcluj.Models.*;
import ro.utcluj.Views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainController {
    private static final StringValidator stringValidator = new StringValidator();
    public MainController(MainView mainView){

        mainView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stringValidator.stringValidate(mainView.getFirstPolynomialField());
                    stringValidator.stringValidate(mainView.getSecondPolynomialField());
                    Polynomial firstPolynomial = new Polynomial(mainView.getFirstPolynomialField());
                    Polynomial secondPolynomial = new Polynomial(mainView.getSecondPolynomialField());
                    mainView.getRemainderLabel().setVisible(false);
                    mainView.getRemainderLabelText().setVisible(false);
                    Polynomial resultPolynomial= Operations.addOrSubtractOperation(firstPolynomial,secondPolynomial,0);
                    mainView.setResultLabel2(resultPolynomial.toString());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(new JOptionPane(),e1.getMessage());
                    mainView.refresh();

                }

            }
        });
        mainView.getSubtractButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stringValidator.stringValidate(mainView.getFirstPolynomialField());
                    stringValidator.stringValidate(mainView.getSecondPolynomialField());
                    Polynomial firstPolynomial = new Polynomial(mainView.getFirstPolynomialField());
                    Polynomial secondPolynomial = new Polynomial(mainView.getSecondPolynomialField());
                    mainView.getRemainderLabel().setVisible(false);
                    mainView.getRemainderLabelText().setVisible(false);
                    Polynomial resultPolynomial= Operations.addOrSubtractOperation(firstPolynomial,secondPolynomial,1);
                    mainView.setResultLabel2(resultPolynomial.toString());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(new JOptionPane(),e1.getMessage());
                    mainView.refresh();
                }
            }
        });
        mainView.getDerivateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stringValidator.stringValidate(mainView.getFirstPolynomialField());
                    Polynomial firstPolynomial = new Polynomial(mainView.getFirstPolynomialField());
                    Polynomial resultPolynomial = Operations.derivative(firstPolynomial);
                    mainView.getRemainderLabel().setVisible(false);
                    mainView.getRemainderLabelText().setVisible(false);
                    PolynomialService polynomialService = new PolynomialService();
                    polynomialService.checkForZeros(resultPolynomial);
                    mainView.setResultLabel2(resultPolynomial.toString());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(new JOptionPane(),e1.getMessage());
                    mainView.refresh();
                }

            }
        });
        mainView.getIntegrateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stringValidator.stringValidate(mainView.getFirstPolynomialField());
                    Polynomial polynomial=new Polynomial(mainView.getFirstPolynomialField());
                    Polynomial resultPolynomial = Operations.integrate(polynomial);
                    mainView.getRemainderLabel().setVisible(false);
                    mainView.getRemainderLabelText().setVisible(false);
                    mainView.setResultLabel2(resultPolynomial.toString());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(new JOptionPane(),e1.getMessage());
                    mainView.refresh();
                }

            }
        });
        mainView.getMultiplyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stringValidator.stringValidate(mainView.getFirstPolynomialField());
                    stringValidator.stringValidate(mainView.getSecondPolynomialField());
                    mainView.getRemainderLabel().setVisible(false);
                    mainView.getRemainderLabelText().setVisible(false);
                    Polynomial firstPolynomial = new Polynomial(mainView.getFirstPolynomialField());
                    Polynomial secondPolynomial = new Polynomial(mainView.getSecondPolynomialField());
                    Polynomial resultPolynomial = Operations.multiply(firstPolynomial,secondPolynomial);
                    mainView.setResultLabel2(resultPolynomial.toString());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(new JOptionPane(),e1.getMessage());
                    mainView.refresh();
                }

            }
        });

        mainView.getDivisionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stringValidator.stringValidate(mainView.getFirstPolynomialField());
                    stringValidator.stringValidate(mainView.getSecondPolynomialField());
                    Polynomial firstPolynomial = new Polynomial(mainView.getFirstPolynomialField());
                    Polynomial secondPolynomial = new Polynomial(mainView.getSecondPolynomialField());
                    List<Polynomial> result= Operations.division(firstPolynomial,secondPolynomial);
                    mainView.getRemainderLabel().setVisible(true);
                    mainView.getRemainderLabelText().setVisible(true);
                    if(result== null){
                        mainView.setResultLabel2("0");
                        mainView.setRemainderLabel("0");
                    }else{
                        mainView.setResultLabel2(result.get(0).toString());
                        mainView.setRemainderLabel(result.get(1).toString());
                    }

                }catch (Exception e1){
                    JOptionPane.showMessageDialog(new JOptionPane(),e1.getMessage());
                }

            }
        });

        mainView.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.refresh();
                mainView.setResultLabel2("");
                mainView.getRemainderLabel().setVisible(false);
                mainView.getRemainderLabelText().setVisible(false);
            }
        });

        mainView.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
