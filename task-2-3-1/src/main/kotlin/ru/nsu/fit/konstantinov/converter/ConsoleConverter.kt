package ru.nsu.fit.konstantinov.converter

import ru.nsu.fit.konstantinov.dto.console.ConsoleDto
import ru.nsu.fit.konstantinov.model.pixel.DefaultPixel
import ru.nsu.fit.konstantinov.model.pixel.PixelType

class ConsoleConverter : Converter<ConsoleDto> {
    private val objectsToVisual = HashMap<PixelType, Char>().apply {
        this[PixelType.FOOD] = '*'
        this[PixelType.SNAKE] = '□'
        this[PixelType.WALL] = '#'
        this[PixelType.EMPTY] = ' '
    }

    override fun convert(element: DefaultPixel): ConsoleDto {
        return ConsoleDto(element.x, element.y, objectsToVisual[element.pixelType] ?: ' ')
    }
}
