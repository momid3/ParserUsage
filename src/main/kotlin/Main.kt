package com.momid

fun main() {
    val parameters = parseParameters("ooo:ooo, some:type, other:type")!!

    parameters.forEach {
        it.isParameter.then { parameter ->
            println(parameter.name.range)
            println(parameter.type.range)
        }
    }
}

fun <T> T?.then(block: (T) -> Unit) {
    if (this != null) {
        block(this)
    }
}
