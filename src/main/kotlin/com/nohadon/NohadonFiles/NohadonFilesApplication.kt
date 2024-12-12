package com.nohadon.NohadonFiles

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NohadonFilesApplication (
){}
fun main(args: Array<String>) { runApplication<NohadonFilesApplication>(*args) }