package dsl1

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun Int?.isValid(): Boolean {
    contract {
        returns(true) implies (this@isValid != null)
    }

    return this != null && this > 0
}

fun main() {
    val nullableInt: Int? = 5

    if(nullableInt.isValid()) {
        val notNullInt: Int = nullableInt
    }
}
