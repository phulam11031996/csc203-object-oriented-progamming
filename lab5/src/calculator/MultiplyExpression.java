package calculator;
public class MultiplyExpression
   extends BinaryExpression
{
   public MultiplyExpression(final Expression lft, final Expression rht, String operator)
   {
      super(lft, rht, operator);
   }

   protected double _applyOperator(double result) {
      return result;
   }
}

