package KotlinExercises

import java.util.*

fun main(){
    var phrase : String

    val reader = Scanner(System.`in`)
    println("Input word or phrase: ")
    phrase = reader.nextLine()

    while (phrase.isNotEmpty()) {
        var length = phrase.length
        print("Character: \"${phrase[0]}\" Count : ")
        phrase = phrase.replace(phrase[0].toString(), "",ignoreCase = true)
        var count = length - phrase.length
        println(count)
    }

}