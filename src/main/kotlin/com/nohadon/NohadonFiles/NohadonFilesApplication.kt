package com.nohadon.NohadonFiles

import com.nohadon.NohadonFiles.utils.NFBUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NohadonFilesApplication (
){
    companion object {
        val log : Logger = LoggerFactory.getLogger(NohadonFilesApplication::class.java)

    }

}

fun main(args: Array<String>) {
    NohadonFilesApplication.log.info("Starting Application...")
    NohadonFilesApplication.log.info(NFBUtils.printLogo())
    runApplication<NohadonFilesApplication>(*args)
}