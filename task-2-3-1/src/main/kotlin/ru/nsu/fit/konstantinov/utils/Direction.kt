package ru.nsu.fit.konstantinov.utils

import ru.nsu.fit.konstantinov.model.pixel.SnakePixel

enum class Direction
    (
    val movePixel: (SnakePixel, SnakePixel) -> Unit,
    val generatePixel: (SnakePixel) -> SnakePixel
) {
    LEFT(
        { head: SnakePixel, tail: SnakePixel -> tail.x = head.x - 1 },
        { head: SnakePixel -> SnakePixel(head.x - 1, head.y) }
    ),
    RIGHT(
        { head: SnakePixel, tail: SnakePixel -> tail.x = head.x + 1 },
        { head: SnakePixel -> SnakePixel(head.x + 1, head.y) }
    ),
    DOWN(
        { head: SnakePixel, tail: SnakePixel -> tail.y = head.y + 1 },
        { head: SnakePixel -> SnakePixel(head.x, head.y - 1) }
    ),
    UP(
        { head: SnakePixel, tail: SnakePixel -> tail.y = head.y - 1 },
        { head: SnakePixel -> SnakePixel(head.x, head.y + 1) }
    );
}
