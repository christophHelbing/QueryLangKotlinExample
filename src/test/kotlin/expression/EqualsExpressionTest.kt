package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property
import java.time.OffsetDateTime

class EqualsExpressionTest {
    @Test
    fun `EqualsExpression will be created right with Property and Integer`() {
        val exp = Property.DOCUMENT_TYPE eq 5
        Assertions.assertEquals(Property.DOCUMENT_TYPE, exp.property)
        Assertions.assertEquals(5, exp.expectedValue)
    }

    @Test
    fun `EqualsExpression will created right with Property and String`() {
        val exp = Property.DOCUMENT_TYPE eq "String"
        Assertions.assertEquals(Property.DOCUMENT_TYPE, exp.property)
        Assertions.assertEquals("String", exp.expectedValue)
    }

    @Test
    fun `EqualsExpression will created right with Property and OffsetDateTime`() {
        val nowTimeStamp = OffsetDateTime.now()
        val exp = Property.DOCUMENT_DATE eq nowTimeStamp
        Assertions.assertEquals(Property.DOCUMENT_DATE, exp.property)
        Assertions.assertEquals(nowTimeStamp, exp.expectedValue)
    }
}