



final class Function$u002Eworksheet$_ {
def args = Function$u002Eworksheet_sc.args$
def scriptPath = """src/main/scala/Function.worksheet.sc"""
/*<script>*/
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
/*</script>*/ /*<generated>*/
/*</generated>*/
}

object Function$u002Eworksheet_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new Function$u002Eworksheet$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export Function$u002Eworksheet_sc.script as Function$u002Eworksheet

