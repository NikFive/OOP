package ru.nsu.fit.konstantinov.model

import ru.nsu.fit.konstantinov.model.pixel.DefaultPixel
import ru.nsu.fit.konstantinov.model.pixel.EmptyPixel
import ru.nsu.fit.konstantinov.model.pixel.PixelType

class CollisionValidator(height: Int, length: Int) {
    private val pixels: Array<Array<DefaultPixel?>> =
        Array<Array<DefaultPixel?>>(length) { arrayOfNulls(height) }.apply {
            for (i in 0 until length) {
                for (j in 0 until height) {
                    this[i][j] = EmptyPixel(i, j)
                }
            }
        }

    fun validateCollision(obj: DefaultPixel?): DefaultPixel = pixels[obj!!.x][obj.y]!!

    fun addPixel(pixel: DefaultPixel): Boolean = if (pixels[pixel.x][pixel.y]!!.pixelType != PixelType.EMPTY) {
        false
    } else {
        pixels[pixel.x][pixel.y] = pixel
        true
    }

    fun addPixels(pixels: Collection<DefaultPixel>?) {
        for (pixel in pixels!!) {
            addPixel(pixel)
        }
    }

    fun removePixel(pixel: DefaultPixel?) {
        pixels[pixel!!.x][pixel.y] = EmptyPixel(pixel.x, pixel.y)
    }
}
