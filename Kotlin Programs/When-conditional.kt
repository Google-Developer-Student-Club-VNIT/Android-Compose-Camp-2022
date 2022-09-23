import java.util.Scanner;

fun loops(name: String){
    var i: Int = 2
    println("Output \n")
    when(i){
        1 -> println("\n${name} and " + i)
        2,9,10 -> println("\n${i} and " + name)
        in 3..8 -> println("\n"+name)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter your name")
    var name = scanner.nextLine()
    loops(name)
}