package ru.nsu.fit.konstantinov.converter

import javafx.scene.paint.Color
import ru.nsu.fit.konstantinov.dto.fxml.FXMLDto
import ru.nsu.fit.konstantinov.dto.fxml.FXMLFoodDto
import ru.nsu.fit.konstantinov.dto.fxml.FXMLSnakeDto
import ru.nsu.fit.konstantinov.dto.fxml.FXMLWallDto
import ru.nsu.fit.konstantinov.model.pixel.DefaultPixel

class FXMLConverter : Converter<FXMLDto> {
    private val dtoList: MutableList<FXMLDto> = ArrayList<FXMLDto>().apply {
        this.add(FXMLSnakeDto(0, 0))
        this.add(FXMLFoodDto(0, 0))
        this.add(FXMLWallDto(0, 0))
    }

    override fun convert(element: DefaultPixel): FXMLDto {
        val cloneDto = dtoList.stream()
            .filter { x: FXMLDto -> element.pixelType == x.pixelType }
            .findFirst()
            .orElse(FXMLDto(0, 0, Color.WHITE)).clone() as FXMLDto
        cloneDto.x = element.x
        cloneDto.y = element.y
        return cloneDto
    }
}
