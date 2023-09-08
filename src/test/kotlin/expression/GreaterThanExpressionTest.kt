package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property
import java.time.OffsetDateTime

class GreaterThanExpressionTest {
    @Test
    fun `GreaterThenExpression contains OffsetDateTime`() {
        val nowTimeStamp = OffsetDateTime.now()
        val exp = Property.DOCUMENT_DATE gt nowTimeStamp

        Assertions.assertEquals(Property.DOCUMENT_DATE, exp.property)
        Assertions.assertEquals(nowTimeStamp, exp.value)
    }

    @Test
    fun `GreaterThenEqualsExpression contains OffsetDateTime`() {
        val nowTimeStamp = OffsetDateTime.now()
        val exp = Property.DOCUMENT_DATE gte nowTimeStamp

        Assertions.assertEquals(Property.DOCUMENT_DATE, exp.property)
        Assertions.assertEquals(nowTimeStamp, exp.value)
    }
}