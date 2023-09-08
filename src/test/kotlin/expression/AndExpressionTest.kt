package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property

class AndExpressionTest {
    @Test
    fun `AndExpression contains 2 EqualExpressions`() {
        val exp = (Property.DOCUMENT_TYPE eq 5) and (Property.DOCUMENT_DATE eq "01.01.2022 18:00:00")
        Assertions.assertEquals(
            EqualsExpression(Property.DOCUMENT_TYPE, 5),
            exp.expressionA
        )
        Assertions.assertEquals(
            EqualsExpression(Property.DOCUMENT_DATE, "01.01.2022 18:00:00"),
            exp.expressionB
        )
    }
}