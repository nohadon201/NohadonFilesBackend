package com.nohadon.NohadonFiles

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NohadonFilesApplication{
 companion object {

 }
}
fun main(args: Array<String>) {
	val log : Logger = LoggerFactory.getLogger(NohadonFilesApplication::class.java)
	
	runApplication<NohadonFilesApplication>(*args)
}
