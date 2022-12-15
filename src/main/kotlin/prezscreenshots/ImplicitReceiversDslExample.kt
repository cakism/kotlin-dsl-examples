package prezscreenshots


private val explicit: Int.(Int) -> Int = {other -> this.plus(other)}

private val implicit: Int.(Int) -> Int = { other -> plus(other) }

private val functionTypeWithoutReceiver: (Int, Int) -> Int = {first, second -> first.plus(second)}


private fun performMath(doIntMath: (Int, Int) -> Int) {
    doIntMath(333, 777)
}

private fun main() {
    performMath(implicit)
}
