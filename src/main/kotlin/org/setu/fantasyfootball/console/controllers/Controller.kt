package org.setu.fantasyfootball.console.controllers

import mu.KotlinLogging
import org.setu.fantasyfootball.console.models.TeamModel
import org.setu.fantasyfootball.console.models.PlayerMemStore
import org.setu.fantasyfootball.console.views.TeamView
import org.setu.fantasyfootball.console.views.PlayerView
import org.setu.fantasyfootball.console.models.TeamJsonStore

class Controller {
    val logger = KotlinLogging.logger{}
    val teams = TeamJsonStore()
    val players = PlayerMemStore()
    val teamView = TeamView()
    val playerView = PlayerView()

    init {
        logger.info { "Lanching Fantasy NFL App" }
        println("Fantasy NFL")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input){
                1 -> addTeam()
                2 -> updateTeam()
                3 -> listTeams()
                4 -> searchTeams()
                5 -> deleteTeam()
                6 ->{
                    players.create()
                    playerMenu()
                }
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option Try Again...")
            }
        }while (input != -1)
        logger.info { "Shutting Down Fantasy NFL App" }
    }

    private fun playerMenu(){
        var input: Int
        do{
            input = menu2()
            when (input) {
                1 -> listPlayers()
                2 -> addPlayer()
                3 -> searchPlayers()
                -1 -> println("Exiting App")
                else -> println("Invalid Option Try Again...")
            }
        }while (input != -1)
    }

    fun menu() : Int { return teamView.menu() }
    fun menu2() : Int { return playerView.menu2() }

    fun addTeam(){
        val aTeam = TeamModel()

        if (teamView.addTeamData(aTeam))
            teams.create(aTeam)
        else
            logger.info("Team NOT Added")
    }

    fun listTeams(){
        teamView.listTeams(teams)
    }

    fun listPlayers(){
        playerView.listPlayers(players)
    }

    fun deleteTeam(){
        listTeams()
        print("Enter Id to Delete")
        var input = readln()!!

        try {
            val index = input.toLong()
            val teamToDelete = teams.teams.find { it.id == index }
            if (teamToDelete != null) {
                teams.delete(teamToDelete)
            } else {
                println("ID not found.")
            }
        } catch (e: NumberFormatException) {
            println("Please enter a valid ID.")
        }
    }

    fun updateTeam(){
        teamView.listTeams(teams)
        val searchId = teamView.getId()
        val aTeam = search(searchId)

        if(aTeam != null){
            if (teamView.updateTeamData(aTeam)){
                teams.update(aTeam)
                teamView.showTeam(aTeam)
                logger.info("Team Updated: [$aTeam] ")
            }
            else
                logger.info("Team NOT Updated")
        }
        else
            println("Team NOT Updated...")
    }

    fun addPlayer() {
        var newPlayer = playerView.addPlayer()
        players.add(newPlayer)
    }

    fun searchPlayers(){
        var input: Int

        do{
            input = playerView.searchPlayers(players)
            when (input) {
                1 -> searchByPosition()
                2 -> searchByNum()
                -1 -> println("Exiting App")
                else -> println("Invalid Option Try Again...")
            }
        }while (input != -1)
    }

    private fun searchByPosition(){
        println("Enter A Players Position: ")
        val position = readln()!!
        val foundPlayers = players.findByPosition(position)

        println("Players In The Position, $position")
        foundPlayers.forEach{
            println(it.name)
            println(it.position)
            println(it.number)
            println()
        }
    }

    private fun searchByNum(){
        println("Enter Player Number: ")
        val number = readln()!!
        val foundPlayers = players.findByNumber(number.toInt())

        println("Players With Number, $number")
        foundPlayers.forEach{
            println(it.name)
            println(it.position)
            println(it.number)
            println()
        }
    }

    fun searchTeams(){
        val aTeam = search(teamView.getId())!!
        teamView.showTeam(aTeam)
    }

    fun search(id: Long) : TeamModel? {
        val foundTeam = teams.findOne(id)
        return foundTeam
    }

    fun dummyData(){
        teams.create(TeamModel(title = "Stealers", description = "Favorite Team"))
        teams.create(TeamModel(title = "New York Giants", description = "Some big lads on that team"))
        teams.create(TeamModel(title = "The Jets", description = "Them lads can run"))
    }
}