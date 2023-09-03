package app.kaleb.theSocialBot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Service
class Scheduler @Autowired constructor(
    var messagingService: MessagingService,
    var slack: Slack
) {
    var lastSent: LocalDate? = null
    var sendHistory: MutableList<String> = mutableListOf()

    // three hours
    @Scheduled(fixedRate = 3 * 60 * 60 * 1000)
    fun runMessageJob() {

        // Check that it passes a random threshold. Increase number to increase frequency
        if (Math.random() > .25) {
            println("🟡 Skipping, random threshold")
            return
        }

        if (checkIfWeekend()) {
            println("🟡 Skipping, weekend")
            return
        }

        if (checkIfOutsideWorkHours()) {
            println("🟡 Skipping, outside work hours")
            return
        }

        if (checkIfSentRecently()) {
            println("🟡 Skipping, already sent in the last 2 days")
            return
        }

        println("🟢 Passed checks, sending message")
        lastSent = LocalDate.now()

        val messages = messagingService.getMessages()
        var messageToSend: String

        // Check message hasn't already been sent
        do {
            messageToSend = messages.random()
        } while (sendHistory.contains(hashMessage(messageToSend)))

        sendHistory.add(hashMessage(messageToSend))
        slack.sendMessage(messageToSend)
    }

    private fun hashMessage(message: String): String {
        return message.hashCode().toString()
    }

    private fun checkIfWeekend(): Boolean {
        val currentDate = LocalDate.now()
        val dayOfWeek = currentDate.dayOfWeek
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
    }

    private fun checkIfOutsideWorkHours(): Boolean {
        val currentTime = LocalTime.now()
        val startTime = LocalTime.of(9, 0) // 9 AM
        val endTime = LocalTime.of(17, 0) // 5 PM
        return (!currentTime.isAfter(startTime) || !currentTime.isBefore(endTime))
    }

    private fun checkIfSentRecently(): Boolean {
        val currentDate = LocalDate.now()
        return (lastSent != null && lastSent!!.isAfter(currentDate.minusDays(2)))
    }
}