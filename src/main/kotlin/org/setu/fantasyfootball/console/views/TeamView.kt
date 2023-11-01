package org.setu.fantasyfootball.console.views

import org.setu.fantasyfootball.console.models.TeamJsonStore
import org.setu.fantasyfootball.console.models.TeamModel

class TeamView {

    fun menu() : Int{
        var option: Int
        var input: String?

        println("Main Menu")
        println(" 1. Add Team")
        println(" 2. Update Team")
        println(" 3. List All Teams")
        println(" 4. Search Teams")
        println(" 5. Delete Team")
        println(" 6. Player Options")
        println("-1. Exit")
        println()
        println("Enter Option: ")
        input = readln()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listTeams(teams: TeamJsonStore){
        println("List All Teams")
        println()
        teams.logAll()
        println()
    }

    fun showTeam(team : TeamModel){
        if(team != null)
            println("Team Details [$team]")
        else
            println("Team Not Found...")
    }

    fun addTeamData(team : TeamModel) : Boolean{
        println()
        print("Enter A Title: ")
        team.title = readln()!!
        print("Enter A Description: ")
        team.description = readln()!!

        return team.title.isNotEmpty() && team.description.isNotEmpty()
    }

    fun updateTeamData(team : TeamModel) : Boolean{
        val tempTitle: String?
        val tempDescription: String?

        if (team != null){
            print("Enter A New Title For [ " + team.title + " ] : ")
            tempTitle = readln()!!
            print("Enter A New Description For [ " + team.description + " ] : ")
            tempDescription = readln()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()){
                team.title = tempTitle
                team.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long{
        var strId: String?
        var searchId: Long
        print("Enter ID To Search/Update : ")
        strId = readln()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}