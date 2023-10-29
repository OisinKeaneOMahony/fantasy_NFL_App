package org.setu.fantasyfootball.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger{}

fun main(args: Array<String>) {
    logger.info {"Launching Fantasy NFL App"}
    println("Fantasy NFL App")

    var input: Int

    do{
        input = menu()
        when(input){
            1 -> println("You Chose To Add Team")
            2 -> println("You Chose To Update Teams")
            3 -> println("You Chose To List All Teams")
            -1 -> println("Exiting App")
            else -> println("Invalid Option Try Again")
        }
        println()
    } while (input != -1)
    logger.info{"Shutting down Fantasy NFL App"}
}

fun menu() : Int{

    var option: Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Team")
    println(" 2. Update Team")
    println(" 3. List All Teams")
    println("-1. Exit")
    println()
    print("Enter an Integer : ")
    input = readln()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}