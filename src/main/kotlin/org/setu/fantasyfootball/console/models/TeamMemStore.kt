package org.setu.fantasyfootball.console.models

import mu.KotlinLogging
import org.setu.fantasyfootball.console.models.TeamModel
import org.setu.fantasyfootball.console.models.TeamStore

private val logger = KotlinLogging.logger{}
var lastId = 0L

private fun getId(): Long{
    return lastId ++
}

class TeamMemStore : TeamStore {
    val teams = ArrayList<TeamModel>()

    override fun findAll(): List<TeamModel> {
        return teams
    }

    override fun findOne(id: Long) : TeamModel? {
        return teams.find { p -> p.id == id }
    }

    override fun create(team: TeamModel) {
        team.id = getId()
        teams.add(team)
        logAll()
    }

    override fun update(team: TeamModel){
        var foundTeam = findOne(team.id!!)
        if (foundTeam != null){
            foundTeam.title = team.title
            foundTeam.description = team.description
        }
    }

    override fun delete(team: TeamModel){
        teams.removeIf { it.id == team.id }
    }

    internal fun logAll(){
        teams.forEach { logger.info("${it}") }
    }
}