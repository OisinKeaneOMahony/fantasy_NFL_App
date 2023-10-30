package org.setu.fantasyfootball.console.main

import mu.KotlinLogging
import org.setu.fantasyfootball.console.models.TeamModel

private val logger = KotlinLogging.logger{}
val teams = ArrayList<TeamModel>()

fun main(args: Array<String>) {
    logger.info {"Launching Fantasy NFL App"}
    println("Fantasy NFL App")

    var input: Int

    do{
        input = menu()
        when(input){
            1 -> addTeam()
            2 -> updateTeam()
            3 -> listTeams()
            4 -> searchTeam()
            -99 -> dummyData()
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
    println(" 4. Search For A Team")
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

fun addTeam(){
    var aTeam = TeamModel()
    println("Add Team")
    println()
    print("Enter a Title: ")
    aTeam.title = readln()!!
    print("Enter a Description: ")
    aTeam.description = readln()!!

    if (aTeam.title.isNotEmpty() && aTeam.description.isNotEmpty()){
        aTeam.id = teams.size.toLong()
        teams.add(aTeam.copy())
        logger.info("Team Added: [$aTeam]")
    }
    else
        logger.info("Team Not Added")
}

fun updateTeam(){
    println("Update Team")
    println()
    listTeams()
    var searchId = getId()
    val aTeam = search(searchId)
    var tempTitle: String?
    var tempDescription: String?

    if(aTeam != null){
        print("Enter A New Title For ["+ aTeam.title +"] : ")
        tempTitle = readln()!!
        print("Enter A New Description For ["+ aTeam.description +"] : ")
        tempDescription = readln()!!

        if(!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()){
            aTeam.title = tempTitle
            aTeam.description =tempDescription
            println(
                "You updated [ "+ aTeam.title +" ] for title "+
                "and [ "+ aTeam.description +" ] for description")
            logger.info("Team Updated: [ $aTeam ] ")
        }
        else
            logger.info("Team Not Updated")
    }
    else println("Team Not Updated...")
}

fun listTeams(){
    println("List of All Teams")
    println()
    teams.forEach{ logger.info("${it}") }
    println()
}

fun searchTeam(){
    var searchId = getId()
    val aTeam = search(searchId)

    if(aTeam != null)
        println("Team Details [ $aTeam ] ")
    else
        println("Team Not Found Try Again...")
}

fun getId(): Long{
    var strId : String?
    var searchId : Long?
    print("Enter ID to Search/Update: ")
    strId = readln()!!
    searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : TeamModel? {
    var foundTeam : TeamModel? = teams.find { p -> p.id == id }
    return foundTeam
}

fun dummyData(){
    teams.add(TeamModel(1, "Stealers", "Favorite team"))
    teams.add(TeamModel(2, "New York Giants", "Some big lads on that team"))
    teams.add(TeamModel(3, "The Jets", "Them lads can run"))
}