package com.example.visualization_uebung_1

class ResultUnit(){

    private var shape: String = ""
    private var trueScale: Int? = null;
    private var userScale: Int? = null;

    constructor(shape: String, trueScale: Int, userScale: Int ) : this() {
        this.shape = shape
        this.trueScale = trueScale
        this.userScale = userScale
    }

    fun getShape(): String{ return shape}
    fun getTrueScale(): Int{
        if(trueScale == null) return 0;
        return trueScale as Int
    }
    fun getUserScale(): Int{
        if(userScale == null) return 0;
        return userScale as Int
    }

}