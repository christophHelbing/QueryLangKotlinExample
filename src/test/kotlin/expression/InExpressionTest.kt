package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property

class InExpressionTest {
    @Test
    fun `InExpression contains list of String`() {
        val list = listOf("Receipt", "Invoice", "Credit_Note")
        val exp = Property.DOCUMENT_TYPE isIn list
        Assertions.assertEquals(Property.DOCUMENT_TYPE, exp.property)
        Assertions.assertEquals(list, exp.values)
    }
}