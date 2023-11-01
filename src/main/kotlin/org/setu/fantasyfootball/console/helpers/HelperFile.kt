package org.setu.fantasyfootball.console.helpers

import mu.KotlinLogging
import java.io.*
import kotlin.math.log

val logger = KotlinLogging.logger {}

fun write(fileName: String, data: String){
    val file = File(fileName)
    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    }catch (e: Exception){
        logger.error { "Can Not Write To File: " + e.toString() }
    }
}

fun read(fileName: String): String{
    return try {
        File(fileName).readText()
    }catch (e: FileNotFoundException){
        logger.error { "Can Not Find File: ${e.message}" }
        ""
    }catch (e: IOException){
        logger.error { "Can Not Read File: ${e.message}" }
        ""
    }
}

fun exists(fileName: String): Boolean{
    val file = File(fileName)
    return file.exists()
}