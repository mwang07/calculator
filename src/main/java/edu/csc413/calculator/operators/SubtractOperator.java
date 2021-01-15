package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator {
    @Override
    public int priority() {
        return 1;
    }

    public Operand execute(Operand op1, Operand op2) {
        Operand output = new Operand(op1.getValue() - op2.getValue());
        return output;
    }
}
