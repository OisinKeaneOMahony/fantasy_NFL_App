package org.setu.fantasyfootball.console.models

import mu.KotlinLogging
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*
import org.setu.fantasyfootball.console.helpers.*

private val logger = KotlinLogging.logger{}
val JSON_FILE = "teams.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<TeamModel>>() {}.type

fun generateRandomId(): Long{
    return Random().nextLong()
}

class TeamJsonStore : TeamStore{
    var teams = mutableListOf<TeamModel>()

    init {
        if (exists(JSON_FILE)){
            deserialize()
        }
    }

    override fun create(team: TeamModel) {
        team.id = generateRandomId()
        teams.add(team)
        logAll()
        serialize()
    }

    override fun findAll(): MutableList<TeamModel> {
        return teams
    }

    override fun findOne(id: Long): TeamModel? {
        return teams.find { it.id == id }
    }

    override fun update(team: TeamModel) {
        var foundTeam = findOne(team.id)
        if (foundTeam != null){
            foundTeam.title = team.title
            foundTeam.description = team.description
        }
        serialize()
    }

    override fun delete(team: TeamModel) {
        teams.remove(team)
        serialize()
    }

    internal fun logAll(){
        teams.forEach{ logger.info { "${it}" } }
    }

    private fun serialize(){
        val jsonString = gsonBuilder.toJson(teams, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize(){
        val jsonString = read(JSON_FILE)
        teams =Gson().fromJson(jsonString, listType)
    }
}