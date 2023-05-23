package ru.nsu.fit.konstantinov.presenter

import ru.nsu.fit.konstantinov.converter.ConsoleConverter
import ru.nsu.fit.konstantinov.converter.Converter
import ru.nsu.fit.konstantinov.dto.console.ConsoleDto
import ru.nsu.fit.konstantinov.view.View

class ConsolePresenter(view: View) : DefaultPresenter<ConsoleDto>(view) {
    private val converter: Converter<ConsoleDto> = ConsoleConverter()
    fun getDtoList(): List<ConsoleDto> = super.getDtoList(converter)
}
