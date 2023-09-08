package expression

import visitor.ExpressionVisitor


interface Expression{
    fun accept(visitor: ExpressionVisitor)
}
