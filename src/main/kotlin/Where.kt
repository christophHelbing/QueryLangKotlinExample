import expression.Expression

data class Where(
    val expression: Expression
)

fun where(init: () -> Expression) = Where(init())