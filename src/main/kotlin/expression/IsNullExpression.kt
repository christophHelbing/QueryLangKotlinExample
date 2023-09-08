package expression

import property.Property
import visitor.ExpressionVisitor

data class IsNullExpression(
    val property: Property,
): Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

fun Property.isNull(): IsNullExpression = IsNullExpression(property = this)
