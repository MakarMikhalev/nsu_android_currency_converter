package ru.nsu.mmikhalev.task3.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs", strict = false)
data class CurrencyDTO(
    @field:Attribute(name = "Date", required = false)
    var date: String? = null,

    @field:Attribute(name = "name", required = false)
    var name: String? = null,

    @field:ElementList(name = "Valute", inline = true, required = false)
    var currencies: List<CurrencyValueDTO>? = null
)