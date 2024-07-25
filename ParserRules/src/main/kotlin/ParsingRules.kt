package com.momid

import com.momid.parser.expression.*
import com.momid.parser.not
import com.momid.type.Type

@Type
val allowedName = condition { it.isLetter() } + some(condition { it.isLetterOrDigit() })

@Type
val parameter = spaces + allowedName["name"] + !":" + allowedName["type"] + spaces

@Type
val ooo = !"ooo"

val anyOfParameters = anyOf(parameter, allowedName, ooo)

@Type
val parameters = splitBy(anyOfParameters, ",")
