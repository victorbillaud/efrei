import scala.annotation.tailrec

def factorial(n: Int): BigInt = {
    if (n == 0) 1
    else n * factorial(n - 1)
}

// println(factorial(0))
// println(factorial(1))
// println(factorial(2))
// println(factorial(8))
// println(factorial(1000))
// println(factorial(25000))

def factorialTailRec(n: Int): BigInt = {

    @tailrec
    def factorialHelper(i: Int, accumulator: BigInt): BigInt = {
        if (i == 0) accumulator
        else factorialHelper(i - 1, i * accumulator)
    }

    factorialHelper(i = n, accumulator = 1)
}

// println(factorialTailRec(0))
// println(factorialTailRec(1))
// println(factorialTailRec(2))
// println(factorialTailRec(8))
// println(factorialTailRec(1000))
// println(factorialTailRec(25000))

def fibonacci(n: Int): BigInt = {

    @tailrec
    def fibonacciHelper(i: Int, accumulator: BigInt, previous: BigInt): BigInt = {
        if (i == 0) previous
        else fibonacciHelper(i - 1, accumulator + previous, accumulator)
    }

    fibonacciHelper(i = n, accumulator = 1, previous = 0)
}

println(fibonacci(0))
println(fibonacci(1))
println(fibonacci(2))
println(fibonacci(8))
println(fibonacci(500))
println(fibonacci(25000))