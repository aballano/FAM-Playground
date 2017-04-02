fun main(args: Array<String>) {
    println(Option.Some(2).map(::sumThree))
    // => Some(5)

    println(Option.Some(2).map { it + 3 })
    // => Some(5)

    // This doesn't compile
    // Option.None.map { it + 3 }
    // So we do this:
    val none: Option<Int> = Option.None
    println(none.map { it + 3 })
    // => None

    println(findPost(1).map(::getPostTitle))
    println(findPost(1) map ::getPostTitle)
    // => prints the title "Functors are fun"

    // { a: Int -> a + 2 } map { a: Int -> a + 3 }
    // => ???
    val foo = { a: Int -> a + 2 } map { a: Int -> a + 3 }
    println(foo(10))
    // => 15
}

fun sumThree(n: Int) = n + 3

data class Post(val id: Int, val title: String, val body: String)

fun findPost(id: Int) = Option.Some(Post(id, "Functors are fun", "Learn how to use a functor now!"))

fun getPostTitle(post: Post) = post.title

inline infix fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = map(f)

typealias IntFunction = (Int) -> Int

infix fun IntFunction.map(g: IntFunction): IntFunction {
    return { x -> this(g(x)) }
}