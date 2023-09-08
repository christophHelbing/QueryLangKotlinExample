package expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property
import java.time.OffsetDateTime

class BetweenExpressionTest {
    @Test
    fun `BetweenExpression contains 2 OffsetDateTimes`() {
        val from = OffsetDateTime.now().minusDays(1)
        val to = OffsetDateTime.now()
        val exp = Property.PAYMENT_DATE between BetweenParameter(from, to)

        Assertions.assertEquals(Property.PAYMENT_DATE, exp.property)
        Assertions.assertEquals(from, exp.betweenParameter.from)
        Assertions.assertEquals(to, exp.betweenParameter.to)
    }
}