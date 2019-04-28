package com.example.visualization_uebung_1


class CumulativeResultUnit(){

    var shape: String = ""
    var scale: Int? = null
    var correctGuessed = 0
    var falseGuessed = 0

    constructor(shape: String, scale: Int) : this() {
        this.shape = shape
        this.scale = scale
    }
}