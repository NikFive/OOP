package ru.nsu.fit.konstantinov.converter

import ru.nsu.fit.konstantinov.dto.DefaultDto
import ru.nsu.fit.konstantinov.model.pixel.DefaultPixel

interface Converter<T : DefaultDto?> {
    fun convert(element: DefaultPixel): T
}