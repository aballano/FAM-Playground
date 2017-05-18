import Option.None
import Option.Some

fun main(args: Array<String>) {
    println(Some(3).flatMap(::half))
    // None
    println(Some(4).flatMap(::half))
    // 2
    println(None.flatMap(::half))
    // None

    Some(20) flatMap ::half flatMap ::half flatMap ::half
    // => None
}

fun half(a: Int) = when {
    a % 2 == 0 -> Some(a / 2)
    else -> None
}
