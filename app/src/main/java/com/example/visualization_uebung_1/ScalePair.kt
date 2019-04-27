package com.example.visualization_uebung_1

class ScalePair(){

    private var shape: String = ""
    private var trueScale: Int? = null;
    private var userScale: Int? = null;

    constructor(shape: String, trueScale: Int, userScale: Int ) : this() {
        this.shape = shape
        this.trueScale = trueScale
        this.userScale = userScale
    }



}