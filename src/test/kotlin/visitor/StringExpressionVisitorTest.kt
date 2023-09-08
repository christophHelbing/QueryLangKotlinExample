package visitor

import expression.BetweenParameter
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
import property.StringPropertyRegistry
import where
import java.time.OffsetDateTime

class StringExpressionVisitorTest {
    @Test
    fun `InExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val where = where {
            Property.DOCUMENT_TYPE isIn listOf("Receipt", "Invoice")
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "documentType IN (\"Receipt\", \"Invoice\")",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `EqualsExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.PAYMENT_DATE eq nowTimeStamp
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate = \"$nowTimeStamp\"",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `GreaterThanExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.PAYMENT_DATE gt nowTimeStamp
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate > $nowTimeStamp",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `GreaterThanEqualsExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.PAYMENT_DATE gte nowTimeStamp
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate >= $nowTimeStamp",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `LowerThanExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.PAYMENT_DATE lt nowTimeStamp
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate < $nowTimeStamp",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `LowerThanEqualsExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val nowTimeStamp = OffsetDateTime.now()
        val where = where {
            Property.PAYMENT_DATE lte nowTimeStamp
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate <= $nowTimeStamp",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `BetweenExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val from = OffsetDateTime.now().minusDays(1)
        val to = OffsetDateTime.now()
        val where = where {
            Property.PAYMENT_DATE between BetweenParameter(from, to)
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate >= $from AND paymentDate <= $to",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `IsNullExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val where = where {
            Property.PAYMENT_DATE.isNull()
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "paymentDate IS NULL",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `AndExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val where = where {
            (Property.PAYMENT_DATE.isNull()) and (Property.DOCUMENT_TYPE eq "Receipt")
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "(paymentDate IS NULL) AND (documentType = \"Receipt\")",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `OrExpression will be successfully written as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
        val where = where {
            (Property.PAYMENT_DATE.isNull()) or (Property.DOCUMENT_TYPE eq "Receipt")
        }
        where.expression.accept(visitor)

        Assertions.assertEquals(
            "(paymentDate IS NULL) OR (documentType = \"Receipt\")",
            visitor.getStringExpression()
        )
    }

    @Test
    fun `More complex nested and and or expressions will be written successfully as text`() {
        val visitor = StringExpressionVisitor(StringPropertyRegistry)
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
        where.expression.accept(visitor)
        Assertions.assertEquals(
            """
               #(documentType IN ("Receipt", "Invoice", "Credit_Note")) AND (
                 #(
                   #(deliveryDate >= $from AND deliveryDate <= $to) AND (paymentDate IS NULL)
                 #) OR (
                   #paymentDate >= $from AND paymentDate <= $to
                 #)
               #)
            """.trimMargin("#").replace("\n", ""),
            visitor.getStringExpression() )
    }
}