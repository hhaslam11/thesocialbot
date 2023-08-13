package app.kaleb.theSocialBot.services

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class Scheduler {

    @Scheduled(fixedRate = 5000)
    fun runMessageJob() {
        println("Message job running...")
    }

}