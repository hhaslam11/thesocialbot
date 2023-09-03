package app.kaleb.theSocialBot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class MessagingService @Autowired constructor(val resourceLoader: ResourceLoader) {

    fun getMessages(): List<String> {
        val questions: MutableList<String> = mutableListOf()

        val resource = resourceLoader.getResource("https://raw.githubusercontent.com/hhaslam11/thesocialbot/messages.txt")
        val inputStream = resource.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            questions.add(line!!)
        }

        reader.close()
        return questions
    }

}