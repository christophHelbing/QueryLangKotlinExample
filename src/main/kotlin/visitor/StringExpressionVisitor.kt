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
import property.StringPropertyRegistry

class StringExpressionVisitor(private val propertyRegistry: StringPropertyRegistry) : ExpressionVisitor {
    private val stringBuilder = StringBuilder()

    fun getStringExpression() = stringBuilder.toString()

    override fun visit(expression: AndExpression) {
        stringBuilder.append("(")
        expression.expressionA.accept(this)
        stringBuilder.append(") AND (")
        expression.expressionB.accept(this)
        stringBuilder.append(")")
    }

    override fun visit(expression: OrExpression) {
        stringBuilder.append("(")
        expression.expressionA.accept(this)
        stringBuilder.append(") OR (")
        expression.expressionB.accept(this)
        stringBuilder.append(")")
    }

    override fun visit(expression: IsNullExpression) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString IS NULL")
    }

    override fun <T> visit(expression: InExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        val valueString = expression.values.joinToString{
            "\"${it.toString()}\""
        }
        stringBuilder.append("$propertyString IN ($valueString)")
    }

    override fun <T> visit(expression: BetweenExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString >= ${expression.betweenParameter.from}")
        stringBuilder.append(" AND ")
        stringBuilder.append("$propertyString <= ${expression.betweenParameter.to}")
    }

    override fun <T> visit(expression: EqualsExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString = \"${expression.expectedValue}\"")
    }

    override fun <T> visit(expression: GreaterThanExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString > ${expression.value}")
    }

    override fun <T> visit(expression: GreaterThanEqualsExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString >= ${expression.value}")
    }

    override fun <T> visit(expression: LowerThanExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString < ${expression.value}")
    }

    override fun <T> visit(expression: LowerThanEqualsExpression<T>) {
        val propertyString = propertyRegistry[expression.property]
        stringBuilder.append("$propertyString <= ${expression.value}")
    }
}