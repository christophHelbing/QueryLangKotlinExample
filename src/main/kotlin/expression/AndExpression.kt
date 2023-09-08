package expression

import visitor.ExpressionVisitor

data class AndExpression(
    val expressionA: Expression,
    val expressionB: Expression,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun Expression.and(expressionB: Expression): AndExpression =
    AndExpression(
        this, expressionB
    )

