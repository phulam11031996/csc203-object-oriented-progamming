package calculator;

public class AddExpression
   extends BinaryExpression
{
   public AddExpression(final Expression lft, final Expression rht, String operator)
   {
      super(lft, rht, operator);
   }

   protected double _applyOperator(double result) {
      return result;
   }
}
