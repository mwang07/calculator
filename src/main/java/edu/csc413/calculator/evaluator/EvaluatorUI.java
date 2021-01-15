package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression (or entry), clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param eventObject Event object generated when a
     *                    button is pressed.
     */
    boolean cal = false;
    public void actionPerformed(ActionEvent eventObject) {
        Evaluator ev = new Evaluator();

        //operand or operator show on textField when button is clicked
        txField.setText(txField.getText() + eventObject.getActionCommand());

        //auto clear textfield when pressing number button after "="
        if (cal) {
            if((int)eventObject.getActionCommand().charAt(0) >= 48 && (int)eventObject.getActionCommand().charAt(0) <= 57) {
                txField.setText(eventObject.getActionCommand());
                cal = true;
            }else{
                cal =false;
            }
        }

        //button =
        if(eventObject.getActionCommand().equals("=")){
            int result = ev.eval(txField.getText().substring(0, txField.getText().length() - 1));   //remove = from string
            txField.setText(String.valueOf(result));
            cal = true;
        }
        //button C
        if(eventObject.getActionCommand().equals("C")){
            txField.setText("");
        }
        //button CE
        if(eventObject.getActionCommand().equals("CE")){
            String input = txField.getText().substring(0, txField.getText().length() - 2);  //remove CE from string
            int i = input.length()-1;
            while((int)input.charAt(i) >= 48 && (int)input.charAt(i) <= 57 && i > 0) {      //between 0-9 ascii
                System.out.println(i+" : "+input.charAt(i));
                i--;
            }
            if(i==0)
                txField.setText("");
            else
                txField.setText(input.substring(0, input.length() - (input.length()-i-1)));

        }


    }
}
