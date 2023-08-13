package app.kaleb.theSocialBot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class Scheduler @Autowired constructor(
    var messagingService: MessagingService
) {
    @Scheduled(fixedRate = 5000)
    fun runMessageJob() {

        // TODO Skip if Weekend

        // TODO Skip if not between 9-5

        // TODO Skip if sent in the past 48 hours

        val messages = messagingService.getMessages()
        println("Message job running...")
        println(messages.random())
    }

}