package org.setu.fantasyfootball.console.main

import mu.KotlinLogging
import org.setu.fantasyfootball.console.models.TeamModel
import org.setu.fantasyfootball.console.controllers.Controller

val controller = Controller()
private val logger = KotlinLogging.logger{}
val teams = ArrayList<TeamModel>()

fun main(args: Array<String>) {
    logger.info {"Launching Fantasy NFL App"}
    println("Fantasy NFL App")
    controller.start()
}