package calculator;
public class SubtractExpression
   extends BinaryExpression
{
   public SubtractExpression(final Expression lft, final Expression rht, String operator)
   {
      super(lft, rht, operator);
   }

   protected double _applyOperator(double result) {
      return result;
   }
}

