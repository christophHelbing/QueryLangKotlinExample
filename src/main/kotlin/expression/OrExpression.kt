package expression

import visitor.ExpressionVisitor

//data class OrExpression<T>(

data class OrExpression(
    val expressionA: Expression,
    val expressionB: Expression,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun Expression.or(expressionB: Expression): OrExpression =
    OrExpression(this, expressionB)