package property

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringPropertyRegistryTest {
    @Test
    fun `ResolveFromProperty return the string value for existing Property`() {
        val stringProperty = StringPropertyRegistry[Property.DOCUMENT_DATE]
        Assertions.assertEquals("documentDate", stringProperty)
    }
}
