package org.setu.fantasyfootball.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger{}

class PlayerMemStore : PlayerStore {

    val players = ArrayList<PlayerModel>()

    override fun findAll(): List<PlayerModel> {
        return players
    }

    override fun findByPosition(position: String): ArrayList<PlayerModel> {
        val foundPlayers: ArrayList<PlayerModel> = arrayListOf()
        players.forEach{
            if(it.position == position)
                foundPlayers.add(it)
        }
        return foundPlayers
    }

    override fun findByNumber(number: Int): ArrayList<PlayerModel>{
        val foundPlayers: ArrayList<PlayerModel> = arrayListOf()
        players.forEach{
            if(it.number == number)
                foundPlayers.add(it)
        }
        return foundPlayers
    }

    override fun create(){
        players.add(PlayerModel(1, "Tom Brady", "Quarterback", 12))
        players.add(PlayerModel(2,"Patrick Mahomes", "Quarterback", 15))
        players.add(PlayerModel(3,"Derrick Henry", "Running Back", 22))
        players.add(PlayerModel(4,"Travis Kelce", "Tight End", 87))
        players.add(PlayerModel(5,"DeAndre Hopkins", "Wide Receiver", 10))
        players.add(PlayerModel(6,"Aaron Donald", "Defensive Tackle", 99))
        players.add(PlayerModel(7,"Jalen Ramsey", "Cornerback", 20))
        players.add(PlayerModel(8,"George Kittle", "Tight End", 85))
        players.add(PlayerModel(9,"Myles Garrett", "Defensive End", 95))
        players.add(PlayerModel(10,"Russell Wilson", "Quarterback", 3))
    }

    internal fun logAll(){
        players.forEach { logger.info("${it.name}, ${it.position}, ${it.number}") }
    }

    override fun add(player: PlayerModel){
        players.add(player)
    }
}