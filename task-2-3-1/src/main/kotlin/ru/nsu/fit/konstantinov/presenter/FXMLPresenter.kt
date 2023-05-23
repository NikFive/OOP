package ru.nsu.fit.konstantinov.presenter

import ru.nsu.fit.konstantinov.converter.Converter
import ru.nsu.fit.konstantinov.converter.FXMLConverter
import ru.nsu.fit.konstantinov.dto.fxml.FXMLDto
import ru.nsu.fit.konstantinov.view.View

class FXMLPresenter(view: View) : DefaultPresenter<FXMLDto>(view) {
    private val converter: Converter<FXMLDto> = FXMLConverter()
    fun getDtoList(): List<FXMLDto> = super.getDtoList(converter)
}
