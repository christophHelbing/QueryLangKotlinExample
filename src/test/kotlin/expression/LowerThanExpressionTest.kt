package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property
import java.time.OffsetDateTime

class LowerThanExpressionTest {
    @Test
    fun `LowerThenExpression contains OffsetDateTime`() {
        val nowTimeStamp = OffsetDateTime.now()
        val exp = Property.DOCUMENT_DATE lt nowTimeStamp

        Assertions.assertEquals(Property.DOCUMENT_DATE, exp.property)
        Assertions.assertEquals(nowTimeStamp, exp.value)
    }

    @Test
    fun `LowerThenEqualsExpression contains OffsetDateTime`() {
        val nowTimeStamp = OffsetDateTime.now()
        val exp = Property.DOCUMENT_DATE lte nowTimeStamp

        Assertions.assertEquals(Property.DOCUMENT_DATE, exp.property)
        Assertions.assertEquals(nowTimeStamp, exp.value)
    }
}