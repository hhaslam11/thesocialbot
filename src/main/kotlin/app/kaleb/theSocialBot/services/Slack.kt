package app.kaleb.theSocialBot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

private const val SLACK_URL = "https://slack.com/api/chat.postMessage"

@Service
class Slack @Autowired constructor (
    restTemplateBuilder: RestTemplateBuilder,
    @Value("\${slack.token}") private val slackToken: String?,
    @Value("\${slack.channel}") private val slackChannel: String?
) {
    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    private val headers: HttpHeaders
        get() {
            val headers = HttpHeaders()
            headers.set("Authorization", String.format("Bearer %s", slackToken))
            return headers
        }

    fun sendMessage(message: String) {
        val request: HttpEntity<SlackRequest> = HttpEntity<SlackRequest>(
            SlackRequest(slackChannel, message),
            headers
        )

        restTemplate.postForLocation(
            SLACK_URL,
            request,
            String
        )
    }

}

data class SlackRequest (
    val channel: String?,
    val text: String
)