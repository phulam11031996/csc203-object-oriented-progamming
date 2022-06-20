package calculator;

public abstract class BinaryExpression implements Expression {

    private final Expression lft;
    private final Expression rht;
    private final String operator;
    private double result;

    public BinaryExpression(Expression lft, Expression rht, String operator) {
        this.lft = lft;
        this.rht = rht;
        this.operator = operator;
    }

    public String toString()
    {
        return "(" + lft + " " + operator + " " + rht + ")";
    }

    public double evaluate(final Bindings bindings)
    {
        if (this.operator == "+")
            this.result = lft.evaluate(bindings) + rht.evaluate(bindings);

        if (this.operator == "-")
            this.result = lft.evaluate(bindings) - rht.evaluate(bindings);

        if (this.operator == "*")
            this.result = lft.evaluate(bindings) * rht.evaluate(bindings);

        if (this.operator == "/")
            this.result = lft.evaluate(bindings) / rht.evaluate(bindings);

        return _applyOperator(this.result);
    }

    abstract protected double _applyOperator(double result);

}
