import expression.AndExpression
import expression.BetweenExpression
import expression.BetweenParameter
import expression.EqualsExpression
import expression.GreaterThanEqualsExpression
import expression.GreaterThanExpression
import expression.InExpression
import expression.IsNullExpression
import expression.LowerThanEqualsExpression
import expression.LowerThanExpression
import expression.OrExpression
import expression.and
import expression.between
import expression.eq
import expression.gt
import expression.gte
import expression.isIn
import expression.isNull
import expression.lt
import expression.lte
import expression.or
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import property.Property
import java.time.OffsetDateTime

class WhereTest {
    @Test
    fun `Where contains one EqualsExpression`() {
        val where = where {
            Property.DOCUMENT_TYPE eq 5
        }

        Assertions.assertEquals(
            EqualsExpression(Property.DOCUMENT_TYPE, 5),
            where.expression
        )
    }

    @Test
    fun `Where clause contains an AndExpression with 2 EqualExpressions`() {
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            (Property.DOCUMENT_TYPE eq 5) and (Property.DOCUMENT_DATE eq nowTimeStamp)
        }
        Assertions.assertEquals(
            AndExpression(
                EqualsExpression(Property.DOCUMENT_TYPE, 5),
                EqualsExpression(Property.DOCUMENT_DATE, nowTimeStamp)
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains an AndExpression which contains a nested OrExpression`() {
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            (Property.DOCUMENT_TYPE eq 5) and
                    ((Property.DOCUMENT_DATE eq nowTimeStamp) or (Property.DELIVERY_DATE eq nowTimeStamp))
        }
        Assertions.assertEquals(
            AndExpression(
                EqualsExpression(Property.DOCUMENT_TYPE, 5),
                OrExpression(
                    EqualsExpression(Property.DOCUMENT_DATE, nowTimeStamp),
                    EqualsExpression(Property.DELIVERY_DATE, nowTimeStamp)
                )
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains an OrExpression which contains a nested AndExpression`() {
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            (Property.DOCUMENT_TYPE eq 5) or
                    ((Property.DOCUMENT_DATE eq nowTimeStamp) and (Property.DELIVERY_DATE eq nowTimeStamp))
        }
        Assertions.assertEquals(
            OrExpression(
                EqualsExpression(Property.DOCUMENT_TYPE, 5),
                AndExpression(
                    EqualsExpression(Property.DOCUMENT_DATE, nowTimeStamp),
                    EqualsExpression(Property.DELIVERY_DATE, nowTimeStamp)
                )
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains In and And Expression`(){
        val nowTimeStamp = OffsetDateTime.now()
        val list = listOf("Receipt", "Invoice", "Credit_Note")
        val where = where {
            (Property.DOCUMENT_TYPE isIn list) and (Property.DOCUMENT_DATE eq nowTimeStamp)
        }
        Assertions.assertEquals(
            AndExpression(
                InExpression(
                    Property.DOCUMENT_TYPE,
                    list,
                ),
                EqualsExpression(
                    Property.DOCUMENT_DATE,
                    nowTimeStamp
                )
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains lower than Expression`(){
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.DOCUMENT_DATE lt nowTimeStamp
        }

        Assertions.assertEquals(
            LowerThanExpression(
                Property.DOCUMENT_DATE,
                nowTimeStamp,
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains lower than equals Expression`(){
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.DOCUMENT_DATE lte nowTimeStamp
        }

        Assertions.assertEquals(
            LowerThanEqualsExpression(
                Property.DOCUMENT_DATE,
                nowTimeStamp,
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains greater than Expression`(){
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.DOCUMENT_DATE gt nowTimeStamp
        }

        Assertions.assertEquals(
            GreaterThanExpression(
                Property.DOCUMENT_DATE,
                nowTimeStamp,
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains greater than equals Expression`(){
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.DOCUMENT_DATE gte nowTimeStamp
        }

        Assertions.assertEquals(
            GreaterThanEqualsExpression(
                Property.DOCUMENT_DATE,
                nowTimeStamp,
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains lower than and greater than expression`(){
       val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            (Property.DELIVERY_DATE lt nowTimeStamp) and (Property.DOCUMENT_DATE gt nowTimeStamp)
        }
        Assertions.assertEquals(
            AndExpression(
                LowerThanExpression(
                    Property.DELIVERY_DATE,
                    nowTimeStamp,
                ),
                GreaterThanExpression(
                    Property.DOCUMENT_DATE,
                    nowTimeStamp,
                )
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains lower than equals and greater than equals expression`(){
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            (Property.DELIVERY_DATE lte nowTimeStamp) and (Property.DOCUMENT_DATE gte nowTimeStamp)
        }
        Assertions.assertEquals(
            AndExpression(
                LowerThanEqualsExpression(
                    Property.DELIVERY_DATE,
                    nowTimeStamp,
                ),
                GreaterThanEqualsExpression(
                    Property.DOCUMENT_DATE,
                    nowTimeStamp,
                )
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains in expression and between expression`() {
        val from = OffsetDateTime.now().minusDays(1)
        val to = OffsetDateTime.now()
        val list = listOf("Receipt", "Invoice", "Credit_Note")
        val where = where {
            (Property.DOCUMENT_TYPE isIn list) and (Property.PAYMENT_DATE between BetweenParameter(from, to))
        }

        Assertions.assertEquals(
            AndExpression(
                InExpression(
                    Property.DOCUMENT_TYPE,
                    list,
                ),
                BetweenExpression(
                    Property.PAYMENT_DATE,
                    BetweenParameter(from, to),
                )
            ),
            where.expression
        )
    }

    @Test
    fun `Where clause contains a more complex nested and and or expression`() {
        val from = OffsetDateTime.now().minusDays(1)
        val to = OffsetDateTime.now()
        val list = listOf("Receipt", "Invoice", "Credit_Note")
        val where = where {
            (Property.DOCUMENT_TYPE isIn list) and (
                    (
                            (Property.DELIVERY_DATE between BetweenParameter(from, to)) and
                            (Property.PAYMENT_DATE.isNull())
                    ) or (
                            (Property.PAYMENT_DATE between BetweenParameter(from, to))
                    )
            )
        }

        Assertions.assertEquals(
            AndExpression(
                InExpression(
                    Property.DOCUMENT_TYPE,
                    list,
                ),
                OrExpression(
                    AndExpression(
                        BetweenExpression(
                            Property.DELIVERY_DATE,
                            BetweenParameter(from, to),
                        ),
                        IsNullExpression(
                            Property.PAYMENT_DATE
                        ),
                    ),
                    BetweenExpression(
                        Property.PAYMENT_DATE,
                        BetweenParameter(from, to),
                    )
                )
            ),
            where.expression
        )
    }
}