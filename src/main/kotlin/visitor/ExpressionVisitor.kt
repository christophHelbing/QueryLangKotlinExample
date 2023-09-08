package visitor

import expression.AndExpression
import expression.BetweenExpression
import expression.EqualsExpression
import expression.GreaterThanEqualsExpression
import expression.GreaterThanExpression
import expression.InExpression
import expression.IsNullExpression
import expression.LowerThanEqualsExpression
import expression.LowerThanExpression
import expression.OrExpression

interface ExpressionVisitor {
    fun visit(expression: AndExpression)
    fun visit(expression: OrExpression)
    fun visit(expression: IsNullExpression)
    fun<T> visit(expression: InExpression<T>)
    fun<T> visit(expression: BetweenExpression<T>)
    fun<T> visit(expression: EqualsExpression<T>)
    fun<T> visit(expression: GreaterThanExpression<T>)
    fun<T> visit(expression: GreaterThanEqualsExpression<T>)
    fun<T> visit(expression: LowerThanExpression<T>)
    fun<T> visit(expression: LowerThanEqualsExpression<T>)
}