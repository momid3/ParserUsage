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
val parameters = splitByNW(anyOfParameters, ",")

/***
 * splitBy without "wanting" for each element
 */
fun splitByNW(expression: Expression, splitBy: String): CustomExpression {
    return CustomExpression(
        TypeInfo(expression, ListType(expression))
    ) { tokens, startIndex, endIndex, thisExpression ->
        var nextTokenIndex = startIndex
        val subExpressionResults = ArrayList<ExpressionResult>()
        val splitExpression = ExactExpression(splitBy)

        val firstEvaluation = eval(expression, startIndex, tokens, endIndex) ?: return@CustomExpression null
        nextTokenIndex = firstEvaluation.nextTokenIndex
        subExpressionResults.add(firstEvaluation)

        while (true) {
            val splitEvaluation = eval(splitExpression, nextTokenIndex, tokens, endIndex) ?: break
            nextTokenIndex = splitEvaluation.nextTokenIndex

            val nextEvaluation = eval(expression, nextTokenIndex, tokens, endIndex) ?: return@CustomExpression null
            nextTokenIndex = nextEvaluation.nextTokenIndex
            subExpressionResults.add(nextEvaluation)
        }

        return@CustomExpression MultiExpressionResult(
            ExpressionResult(thisExpression, startIndex..nextTokenIndex),
            subExpressionResults
        )
    }
}
