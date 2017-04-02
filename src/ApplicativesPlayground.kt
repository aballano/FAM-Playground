import Option.Some

fun main(args: Array<String>) {
    println(Some({ a: Int -> a + 3 }).apply(Some(2)))
    // => Some(5)

    println(Some(2).apply(Some({ a: Int -> a + 3 })))
    // => Some(5)

    val array = arrayOf<(Int) -> Int>({ it + 3 }, { it * 2 }) apply arrayOf(1, 2, 3)
    println("[ ${array.joinToString()} ]")
    // => [ 4, 5, 6, 2, 4, 6 ]

    // Option.Some(3) map ::curriedAddition map Option.Some(2)
    // => Compiler error!

    println(Some(3) map ::curriedAddition apply Some(2))
    // => Some(5)

    println(Some(3) map ::curriedTimes apply Some(5))
    // => Some(15)

    println(Some(3) map ::dopCurriedTimes apply Some(5) apply Some(4))
    // => Some(60)
}

infix inline fun <A, reified B> Array<(A) -> B>.apply(a: Array<A>) =
        Array(this.size * a.size) {
            this[it / a.size](a[it % a.size])
        }

fun curriedAddition(a: Int) = { b: Int ->
    a + b
}

fun curriedTimes(a: Int) = { b: Int ->
    a * b
}

// This is the curried version of:
// fun dopTimes(a: Int, b: Int, c: Int) = a * b * c
fun dopCurriedTimes(a: Int) = { b: Int ->
    { c: Int -> a * b * c }
}