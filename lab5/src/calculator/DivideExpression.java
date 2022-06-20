package calculator;

public class DivideExpression
   extends BinaryExpression
{
   public DivideExpression(final Expression lft, final Expression rht, String operator)
   {
      super(lft, rht, operator);
   }

   protected double _applyOperator(double result) {
      return result;
   }
}

