package app.kaleb.theSocialBot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TheSocialBotApplication

fun main(args: Array<String>) {
	runApplication<TheSocialBotApplication>(*args)
}
