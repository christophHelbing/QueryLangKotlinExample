package expression

import property.Property
import visitor.ExpressionVisitor

data class InExpression<T>(
    val property: Property,
    val values: List<T>
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun <T> Property.isIn(values: List<T>): InExpression<T> =
    InExpression(this, values)
