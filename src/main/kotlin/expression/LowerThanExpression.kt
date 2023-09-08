package expression

import property.Property
import visitor.ExpressionVisitor

data class LowerThanExpression<T>(
    val property: Property,
    val value: T,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun <T> Property.lt(value: T): LowerThanExpression<T> =
    LowerThanExpression(this, value)

data class LowerThanEqualsExpression<T>(
    val property: Property,
    val value: T,
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun <T> Property.lte(value: T): LowerThanEqualsExpression<T> =
    LowerThanEqualsExpression(this, value)