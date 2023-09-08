package expression

import property.Property
import visitor.ExpressionVisitor

data class EqualsExpression<T>(
    val property: Property,
    val expectedValue: T,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun <T> Property.eq(expectedValue: T): EqualsExpression<T> =
    EqualsExpression(this, expectedValue)