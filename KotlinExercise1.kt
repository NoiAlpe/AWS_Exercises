package KotlinExercises

import java.util.Scanner

fun main (){
    var phrase : String

    val reader = Scanner(System.`in`)
    println("Input word or phrase: ")
    phrase = reader.nextLine()

    println("Phrase: $phrase")

    if (phrase.equals(phrase.reversed(), ignoreCase = true))
        println("The phrase \"$phrase\" is a palindrome")
    else
        println("The phrase \"$phrase\" is not a palindrome")

}