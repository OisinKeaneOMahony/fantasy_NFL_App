package org.setu.fantasyfootball.console.views

import org.setu.fantasyfootball.console.main.controller
import org.setu.fantasyfootball.console.models.PlayerMemStore
import org.setu.fantasyfootball.console.models.PlayerModel

class PlayerView {
    fun menu2() : Int{
        var option: Int
        var input: String?

        println("Main Menu")
        println(" 1. List All Players")
        println(" 2. Add Player")
        println(" 3. Search Players")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readln()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun playerMenu(){
        var input: Int

        do{
            input = menu2()
            when (input) {
                1 -> listPlayers(controller.players)
                2 -> addPlayer()
                3 -> searchPlayers(controller.players)
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }

        }while (input != -1)
    }
    fun listPlayers(players : PlayerMemStore){
        println("List All Players")
        println()
        players.logAll()

    }

    fun addPlayer(): PlayerModel{
        println("Adding A New Player")
        val newPlayer = PlayerModel()

        println("Enter A Player Name: ")
        newPlayer.name = readln()!!

        println("Enter A Player Position: ")
        newPlayer.position = readln()!!

        println("Enter A Player Number: ")
        newPlayer.number = readln()!!.toInt()

        return newPlayer
    }

    fun searchPlayers(players : PlayerMemStore): Int {
        val choice : Int

        println("Search For Player Menu")
        println()
        println("1. Search By Position")
        println("2. Search By Number")
        val input: String? = readln()!!
        choice = if (input!!.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return choice
    }
}