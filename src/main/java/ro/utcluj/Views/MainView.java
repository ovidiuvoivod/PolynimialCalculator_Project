package ro.utcluj.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainView extends JFrame {

    private JLabel titlu;
    private JPanel polynomialInput;
    private JTextField firstPolynomialField;
    private JTextField secondPolynomialField;
    private JLabel resultLabel2;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divisionButton;
    private JButton derivateButton;
    private JButton integrateButton;
    private JButton exitButton;
    private JButton resetButton;
    private JLabel remainderLabel;
    private JLabel remainderLabelText;


    public MainView(){
        this.setMinimumSize(new Dimension(500, 500));
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));

        titlu = new JLabel("Polynomial Calculator");
        titlu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        titlu.setHorizontalAlignment(SwingConstants.CENTER);
        titlu.setBorder(new EmptyBorder(10, 0, 10, 0));
        this.getContentPane().add(titlu, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 2, 0, 0));

        polynomialInput = new JPanel();
        polynomialInput.setBorder(new EmptyBorder(25, 0, 25, 10));
        panel.add(polynomialInput);
        polynomialInput.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel firstPolynomialLabel = new JLabel("First Polynomial =");
        firstPolynomialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstPolynomialLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        polynomialInput.add(firstPolynomialLabel);

        firstPolynomialField = new JTextField();
        firstPolynomialField.setColumns(10);
        firstPolynomialField.setText("");
        polynomialInput.add(firstPolynomialField);

        JLabel secondPolynomialLabel = new JLabel("Second Polynomial =");
        secondPolynomialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        secondPolynomialLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        polynomialInput.add(secondPolynomialLabel);

        secondPolynomialField = new JTextField();
        secondPolynomialField.setColumns(10);
        secondPolynomialField.setText("");
        polynomialInput.add(secondPolynomialField);

        JLabel resultLabel = new JLabel("Result =");
        resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        polynomialInput.add(resultLabel);

        resultLabel2 = new JLabel("");
        resultLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        polynomialInput.add(resultLabel2);
        //resultLabel2.setColumns(10);

        remainderLabelText = new JLabel("Remainder =");
        remainderLabelText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        remainderLabelText.setHorizontalAlignment(SwingConstants.RIGHT);
        polynomialInput.add(remainderLabelText);
        remainderLabelText.setVisible(false);

        remainderLabel = new JLabel("");
        remainderLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        polynomialInput.add(remainderLabel);
        remainderLabelText.setVisible(false);

        JPanel operationButtons = new JPanel();
        panel.add(operationButtons);
        operationButtons.setLayout(new GridLayout(4, 2, 5, 5));

        addButton = new JButton("Add");
        operationButtons.add(addButton);

        subtractButton = new JButton("Subtract");
        operationButtons.add(subtractButton);

        multiplyButton = new JButton("Multiply");
        operationButtons.add(multiplyButton);

        divisionButton = new JButton("Divide");
        operationButtons.add(divisionButton);

        derivateButton = new JButton("Derivate");
        operationButtons.add(derivateButton);

        integrateButton = new JButton("Integrate");
        operationButtons.add(integrateButton);

        resetButton = new JButton("Reset");
        operationButtons.add(resetButton);

        exitButton = new JButton("Exit");
        operationButtons.add(exitButton);

        this.setVisible(true);

    }


    public String getFirstPolynomialField() {
        return firstPolynomialField.getText();
    }

    public String getSecondPolynomialField() {
        return secondPolynomialField.getText();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivisionButton() {
        return divisionButton;
    }

    public JButton getDerivateButton() {
        return derivateButton;
    }

    public JButton getIntegrateButton() {
        return integrateButton;
    }

    public JLabel getRemainderLabel() {
        return remainderLabel;
    }

    public JLabel getRemainderLabelText() {
        return remainderLabelText;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void setFirstPolynomialField(String text) {
        this.firstPolynomialField.setText(text);
    }

    public void setSecondPolynomialField(String secondPolynomialField) {
        this.secondPolynomialField.setText(secondPolynomialField);
    }

    public void setResultLabel2(String text) {
        this.resultLabel2.setText(text);
    }

    public void setRemainderLabel(String remainderLabel) {
        this.remainderLabel.setText(remainderLabel);
    }

    public void refresh(){
        this.setFirstPolynomialField("");
        this.setSecondPolynomialField("");
    }


}
