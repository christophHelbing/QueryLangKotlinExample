package property

object StringPropertyRegistry {
    private val propertyRegistryMap: Map<Property, String> = mapOf(
        Property.DOCUMENT_TYPE to "documentType",
        Property.PAYMENT_DATE to "paymentDate",
        Property.DELIVERY_DATE to "deliveryDate",
        Property.DOCUMENT_DATE to "documentDate"
    )

    operator fun get(property: Property) = propertyRegistryMap[property] ?: ""
}