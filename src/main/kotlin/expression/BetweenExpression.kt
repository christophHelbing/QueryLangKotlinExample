package expression

import property.Property
import visitor.ExpressionVisitor

data class BetweenParameter<T>(
    val from: T,
    val to: T,
)

data class BetweenExpression<T>(
    val property: Property,
    val betweenParameter: BetweenParameter<T>
) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

infix fun<T> Property.between(betweenParameter: BetweenParameter<T>): BetweenExpression<T> =
    BetweenExpression(this, betweenParameter)


