package ru.nsu.fit.konstantinov.model

import ru.nsu.fit.konstantinov.model.pixel.WallPixel

class Wall(val width: Int, val height: Int) {
    val walls: MutableList<WallPixel> = ArrayList<WallPixel>().apply {
        for (i in 0 until height) {
            if (i == 0 || i == height - 1) {
                for (j in 0 until width) {
                    this.add(WallPixel(j, i))
                }
            } else {
                this.add(WallPixel(0, i))
                this.add(WallPixel(width - 1, i))
            }
        }
    }

    fun addWalls(walls: Collection<WallPixel>) {
        this.walls.addAll(walls)
    }
}
