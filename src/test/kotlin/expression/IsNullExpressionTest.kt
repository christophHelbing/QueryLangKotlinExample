package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property

class IsNullExpressionTest {
    @Test
    fun `IsNullExpression will correctly be created`(){
        val exp = Property.PAYMENT_DATE.isNull()
        Assertions.assertEquals(Property.PAYMENT_DATE, exp.property)
    }
}