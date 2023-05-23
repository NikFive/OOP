package ru.nsu.fit.konstantinov.dto

open class DefaultDto(var x: Int, var y: Int) : Cloneable {
    public override fun clone(): DefaultDto = super.clone() as DefaultDto
}
