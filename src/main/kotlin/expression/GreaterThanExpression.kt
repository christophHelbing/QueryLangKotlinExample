package expression

import property.Property
import visitor.ExpressionVisitor

data class GreaterThanExpression<T>(
    val property: Property,
    val value: T,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun <T> Property.gt(value: T): GreaterThanExpression<T> =
    GreaterThanExpression(this, value)

data class GreaterThanEqualsExpression<T>(
    val property: Property,
    val value: T,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun <T> Property.gte(value: T): GreaterThanEqualsExpression<T> =
    GreaterThanEqualsExpression(this, value)