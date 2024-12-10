package ru.nsu.mmikhalev.task3.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Valute", strict = false)
data class CurrencyValueDTO(
    @field:Attribute(name = "ID", required = false)
    var id: String? = null,

    @field:Element(name = "NumCode", required = false)
    var numCode: String? = null,

    @field:Element(name = "CharCode", required = false)
    var charCode: String? = null,

    @field:Element(name = "Value", required = false)
    var value: String? = null,

    @field:Element(name = "Name", required = false)
    var name: String? = null,

    @field:Element(name = "Nominal", required = false)
    var nominal: Int? = null,

    @field:Element(name = "VunitRate", required = false)
    var vunitRate: String? = null,
)